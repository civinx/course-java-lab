package exception;

public class BusinessException extends RuntimeException {
    private String errorCode;

    public BusinessException() {}

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
