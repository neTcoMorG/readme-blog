package youngjun.readme.domain.exception;

public class MalformedParamException extends RuntimeException {
    public MalformedParamException() {
        super();
    }

    public MalformedParamException(String message) {
        super(message);
    }

    public MalformedParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedParamException(Throwable cause) {
        super(cause);
    }

    protected MalformedParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
