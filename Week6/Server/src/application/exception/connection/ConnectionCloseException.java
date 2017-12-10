package application.exception.connection;

/**
 * Created by SilviuG on 20.11.2017.
 */
public class ConnectionCloseException extends RuntimeException {
    public ConnectionCloseException() {
        super();
    }

    public ConnectionCloseException(String message) {
        super(message);
    }

    public ConnectionCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionCloseException(Throwable cause) {
        super(cause);
    }

    protected ConnectionCloseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
