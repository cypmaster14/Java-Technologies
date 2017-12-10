package application.exception.multitenancy;


public class TenantException extends RuntimeException {
    public TenantException() {
        super();
    }

    public TenantException(String message) {
        super(message);
    }

    public TenantException(String message, Throwable cause) {
        super(message, cause);
    }

    public TenantException(Throwable cause) {
        super(cause);
    }

    protected TenantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
