package application.multitenancy;

/**
 * Created by SilviuG on 16.11.2017.
 */
public class Tenant {
    private String tenantId;
    private String tenantName;

    private String dataSourceLabel;
    private String schema;

    public Tenant(String tenantId, String tenantName, String dataSourceLabel, String schema) {
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.dataSourceLabel = dataSourceLabel;
        this.schema = schema;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getDataSourceLabel() {
        return dataSourceLabel;
    }

    public void setDataSourceLabel(String dataSourceLabel) {
        this.dataSourceLabel = dataSourceLabel;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
