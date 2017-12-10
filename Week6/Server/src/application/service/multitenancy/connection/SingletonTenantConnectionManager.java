package application.service.multitenancy.connection;

import application.multitenancy.Tenant;
import application.multitenancy.TenantContextRegistry;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SingletonTenantConnectionManager implements TenantConnectionManager {

    private static TenantConnectionManager INSTANCE;
    private static Map<Tenant, DataSource> DATA_SOURCE_MAP;

    public static TenantConnectionManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonTenantConnectionManager();
        }
        return INSTANCE;
    }

    private SingletonTenantConnectionManager() {
        DATA_SOURCE_MAP = new ConcurrentHashMap<>();
    }

    @Override
    public Connection getConnection() throws NamingException, SQLException {
        Tenant tenant = TenantContextRegistry.getTenantContext().getTenant();
        if (!DATA_SOURCE_MAP.containsKey(tenant)) {
            DATA_SOURCE_MAP.put(tenant, initializeDataSourceFor(tenant));
        }
        return DATA_SOURCE_MAP.get(tenant).getConnection();
    }

    private DataSource initializeDataSourceFor(Tenant tenant) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/" + tenant.getSchema());
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setMaxTotal(1);
        dataSource.setMaxOpenPreparedStatements(1);
        return dataSource;
    }
}
