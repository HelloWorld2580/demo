package com.dhcc.ms.exception;

public class CommonException extends RuntimeException {
    private String errorCode;
    
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     */
    private static final long serialVersionUID = -1545789609133694294L;
    public CommonException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public CommonException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CommonException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CommonException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }
}
