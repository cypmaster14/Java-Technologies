package application.service;

import application.exception.multitenancy.TenantException;
import application.multitenancy.Tenant;
import application.multitenancy.TenantContext;
import application.multitenancy.TenantUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SilviuG on 19.11.2017.
 */
public class TenantIdentifierService {
    private static TenantIdentifierService INSTANCE;

    public static TenantIdentifierService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TenantIdentifierService();
        }
        return INSTANCE;
    }

    private TenantIdentifierService() {
    }

    public TenantContext buildTenantContextFor(HttpServletRequest servletRequest) {
        String urlTenantId = parseTenantId(servletRequest.getServerName());
        Tenant tenant = TenantUtil.tenantOf(urlTenantId);
        if (tenant == null)
            throw new TenantException("Unknown tenant: " + urlTenantId);

        return new TenantContext(tenant);
    }

    public String getTenantIdFor(HttpServletRequest request) {
        String urlTenantId = parseTenantId(request.getServerName());
        Tenant tenant = TenantUtil.tenantOf(urlTenantId);
        if (tenant != null) {
            return tenant.getTenantId();
        }
        throw new TenantException("Unknown tenant: " + urlTenantId);
    }

    /**
     * Parse tenant identifier from the request's URL.
     */
    private String parseTenantId(String serverName) {
        String[] serverNameParams = serverName.split("\\.");
        if (serverNameParams.length < 3)
            return "tenant1";
        return serverNameParams[0];
    }


}
