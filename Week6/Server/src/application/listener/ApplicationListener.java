package application.listener;

import application.configuration.ApplicationConfiguration;
import application.configuration.ConfigurationType;
import application.exception.connection.ConnectionCloseException;
import application.exception.multitenancy.TenantConnectionManagerInitializationException;
import application.multitenancy.TenantContext;
import application.multitenancy.TenantContextRegistry;
import application.request.RequestAttributes;
import application.request.RequestCycle;
import application.service.TenantIdentifierService;
import application.service.multitenancy.connection.ConnectionPerSessionTenantConnectionManager;
import application.service.multitenancy.connection.ConnectionPoolTenantConnectionManager;
import application.service.multitenancy.connection.SingletonTenantConnectionManager;
import application.service.multitenancy.connection.TenantConnectionManager;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.SQLException;

@WebListener()
public class ApplicationListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

    private ConfigurationType configurationType;
    private static final String CONNECTION_TYPE = "connection_type";
    private static final String REQUEST_TENANT_ID = "tenant_id";

    public ApplicationListener() {
        String configurationTypeString = ApplicationConfiguration.getConfigurationValue(CONNECTION_TYPE);
        configurationType = ConfigurationType.getConfigurationType(configurationTypeString);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        RequestCycle.setServletRequest((HttpServletRequest) sre.getServletRequest());

        TenantContext tenantContext = identifyOrBuildTenantContextForRequest(sre.getServletRequest());
        configureTenantConnectionManagerFor(tenantContext);
        switch (configurationType) {
            case CONNECTION_PER_SESSION:
                setTenantContextAtSessionLevel(sre, tenantContext);
                break;
            case SINGLETON_CONNECTION:
                setTenantContextAtServletContextLevel(sre, tenantContext);
                break;
            case CONNECTION_POOL:
                setTenantContextAtRequestLevel(sre, tenantContext);
                break;
            default:
                throw new TenantConnectionManagerInitializationException("Could not determine connection configuration type.");
        }
        TenantContextRegistry.setTenantContext(tenantContext);
    }


    private void setTenantContextAtServletContextLevel(ServletRequestEvent servletRequestEvent, TenantContext tenantContext) {
        ServletContext servletContext = servletRequestEvent.getServletContext();
        HttpServletRequest servletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        if (servletContext.getAttribute((String) servletRequest.getAttribute(REQUEST_TENANT_ID)) == null) {
            servletContext.setAttribute((String) servletRequest.getAttribute(REQUEST_TENANT_ID), tenantContext);
        }
    }

    private void setTenantContextAtSessionLevel(ServletRequestEvent sre, TenantContext tenantContext) {
        HttpSession session = ((HttpServletRequest) sre.getServletRequest()).getSession();
        if (session.getAttribute(RequestAttributes.TENANT_CONTEXT) == null) {
            session.setAttribute(RequestAttributes.TENANT_CONTEXT, tenantContext);
        }
    }

    private void setTenantContextAtRequestLevel(ServletRequestEvent servletRequestEvent, TenantContext tenantContext) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        if (request.getAttribute(RequestAttributes.TENANT_CONTEXT) == null) {
            request.setAttribute(RequestAttributes.TENANT_CONTEXT, tenantContext);
        }
    }

    private TenantContext identifyOrBuildTenantContextForRequest(ServletRequest servletRequest) {
        String tenantId = TenantIdentifierService.getInstance().getTenantIdFor((HttpServletRequest) servletRequest);
        servletRequest.setAttribute(REQUEST_TENANT_ID, tenantId);

        TenantContext tenantContext;
        switch (configurationType) {
            case CONNECTION_PER_SESSION:
                HttpSession session = ((HttpServletRequest) servletRequest).getSession();
                tenantContext = (TenantContext) session.getAttribute(RequestAttributes.TENANT_CONTEXT);
                if (tenantContext != null) {
                    return tenantContext;
                }
                break;
            case SINGLETON_CONNECTION:
                ServletContext context = servletRequest.getServletContext();
                tenantContext = (TenantContext) context.getAttribute((String) servletRequest.getAttribute(REQUEST_TENANT_ID));
                if (tenantContext != null) {
                    return tenantContext;
                }
                break;
            case CONNECTION_POOL:
                tenantContext = (TenantContext) servletRequest.getAttribute(RequestAttributes.TENANT_CONTEXT);
                if (tenantContext != null) {
                    return tenantContext;
                }
                break;
            default:
                throw new TenantConnectionManagerInitializationException("Could not determine connection configuration type.");
        }
        return TenantIdentifierService.getInstance()
                .buildTenantContextFor((HttpServletRequest) servletRequest);
    }

    private void configureTenantConnectionManagerFor(TenantContext tenantContext) {
        TenantConnectionManager tenantConnectionManager;
        switch (configurationType) {
            case CONNECTION_POOL:
                tenantConnectionManager = ConnectionPoolTenantConnectionManager.getInstance();
                break;
            case SINGLETON_CONNECTION:
                tenantConnectionManager = SingletonTenantConnectionManager.getInstance();
                break;
            case CONNECTION_PER_SESSION:
                tenantConnectionManager = ConnectionPerSessionTenantConnectionManager.getInstance();
                break;
            default:
                throw new TenantConnectionManagerInitializationException("Could not find an implementation for " + TenantConnectionManager.class.getSimpleName());
        }
        tenantContext.setConnectionManager(tenantConnectionManager);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        if (configurationType == ConfigurationType.CONNECTION_POOL) {
            HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
            TenantContext tenantContext = (TenantContext) request.getAttribute(RequestAttributes.TENANT_CONTEXT);
            tryAndCloseConnectionOn(tenantContext);
        }
        RequestCycle.setServletRequest(null);
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (configurationType == ConfigurationType.CONNECTION_PER_SESSION) {
            TenantContext tenantContext = (TenantContext) se.getSession().getAttribute(RequestAttributes.TENANT_CONTEXT);
            tryAndCloseConnectionOn(tenantContext);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (configurationType == ConfigurationType.SINGLETON_CONNECTION) {
            TenantContext tenantContext = (TenantContext) sce.getServletContext().getAttribute(RequestAttributes.TENANT_CONTEXT);
            tryAndCloseConnectionOn(tenantContext);
        }
    }

    private void tryAndCloseConnectionOn(TenantContext tenantContext) {
        try {
            if (tenantContext != null) {
                tenantContext.getConnectionManager().getConnection().close();
            }
        } catch (SQLException | NamingException e) {
            throw new ConnectionCloseException("Could not close connection for session.", e);
        }
    }


}
