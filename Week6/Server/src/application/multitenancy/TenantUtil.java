package application.multitenancy;

import java.util.HashMap;
import java.util.Map;

public class TenantUtil {
    private static Map<String, Tenant> tenants;

    static {
        tenants = new HashMap<>();
        tenants.put("tenant1", new Tenant("tenant1", "postgres2", "jdbc/postgresql_java2", "java2"));
        tenants.put("tenant2", new Tenant("tenant2", "postgres", "jdbc/postgresql_java", "java"));
    }

    public static Tenant tenantOf(String tenantId) {
        return tenants.get(tenantId);
    }
}
