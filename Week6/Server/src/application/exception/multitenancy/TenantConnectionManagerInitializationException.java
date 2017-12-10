package application.exception.multitenancy;

/**
 * Created by SilviuG on 20.11.2017.
 */
public class TenantConnectionManagerInitializationException extends RuntimeException {
    public TenantConnectionManagerInitializationException() {
    }

    public TenantConnectionManagerInitializationException(String message) {
        super(message);
    }

    public TenantConnectionManagerInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TenantConnectionManagerInitializationException(Throwable cause) {
        super(cause);
    }

    public TenantConnectionManagerInitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
