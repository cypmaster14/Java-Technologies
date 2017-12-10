package application.multitenancy;

/**
 * Created by SilviuG on 19.11.2017.
 */
public class TenantContextRegistry {
    private static ThreadLocal<TenantContext> tenantContextThreadLocal;

    static {
        tenantContextThreadLocal = new ThreadLocal<>();
    }

    public static TenantContext getTenantContext() {
        return tenantContextThreadLocal.get();
    }

    public static void setTenantContext(TenantContext tenantContext) {
        tenantContextThreadLocal.set(tenantContext);
    }
}
