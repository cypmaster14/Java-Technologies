package application.service.multitenancy.connection;

import application.multitenancy.TenantContext;
import application.multitenancy.TenantContextRegistry;
import application.service.ServiceLocator;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTenantConnectionManager implements TenantConnectionManager {
    private static TenantConnectionManager INSTANCE;

    public static TenantConnectionManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPoolTenantConnectionManager();
        }
        return INSTANCE;
    }

    private ConnectionPoolTenantConnectionManager() {
    }

    @Override
    public Connection getConnection() throws NamingException, SQLException {
        TenantContext tenantContext = TenantContextRegistry.getTenantContext();
        DataSource dataSource = ServiceLocator.getInstance().getDataSource(tenantContext.getTenant().getDataSourceLabel());
        return dataSource.getConnection();
    }
}
