package youngjun.readme.domain.exception;

public class PermissionDenyException extends RuntimeException {
    public PermissionDenyException() {
        super();
    }

    public PermissionDenyException(String message) {
        super(message);
    }

    public PermissionDenyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionDenyException(Throwable cause) {
        super(cause);
    }

    protected PermissionDenyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
