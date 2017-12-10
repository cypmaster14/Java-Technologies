package application.multitenancy;

import application.service.multitenancy.connection.TenantConnectionManager;

public class TenantContext {

    private Tenant tenant;
    private TenantConnectionManager connectionManager;

    public TenantContext(TenantConnectionManager tenantConnectionManager) {
        this.connectionManager = tenantConnectionManager;
    }

    public TenantContext(Tenant tenant) {
        this.tenant = tenant;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setConnectionManager(TenantConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public TenantConnectionManager getConnectionManager() {
        return this.connectionManager;
    }

}
