package application.service.multitenancy.connection;

import application.multitenancy.Tenant;
import application.multitenancy.TenantContext;
import application.multitenancy.TenantContextRegistry;
import application.request.RequestCycle;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by SilviuG on 19.11.2017.
 */
public class ConnectionPerSessionTenantConnectionManager implements TenantConnectionManager {

    private static TenantConnectionManager INSTANCE;
    private static Map<HttpSession, DataSource> DATA_SOURCE_MAP;

    public static TenantConnectionManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPerSessionTenantConnectionManager();
        }
        return INSTANCE;
    }

    private ConnectionPerSessionTenantConnectionManager() {
        DATA_SOURCE_MAP = new ConcurrentHashMap<>();
    }

    @Override
    public Connection getConnection() throws NamingException, SQLException {
        TenantContext tenantContext = TenantContextRegistry.getTenantContext();
        Tenant tenant = tenantContext.getTenant();
        if (!DATA_SOURCE_MAP.containsKey(RequestCycle.getServletRequest().getSession())) {
            DATA_SOURCE_MAP.put(RequestCycle.getServletRequest().getSession(), initializeDataSourceFor(tenant));
        }
        return DATA_SOURCE_MAP.get(RequestCycle.getServletRequest().getSession()).getConnection();
    }

    private DataSource initializeDataSourceFor(Tenant tenant) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/" + tenant.getSchema());
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setMaxTotal(1);
        dataSource.setMaxOpenPreparedStatements(10);
        return dataSource;
    }
}
