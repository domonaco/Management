package it.monaco.medical.service.model.exceptions;

import it.monaco.medical.service.model.enums.ErrorCode;
import it.monaco.medical.service.model.enums.ErrorType;

public class LegacyException extends  RuntimeException {

    protected ErrorType erroType;
    protected ErrorCode errorCode;
    private String message;

    public LegacyException(String message)
    {
        this.errorCode = ErrorCode.LEG_999_UNEXPECTED_ERROR;
        this.erroType = ErrorType.UNEXPECTED;
        this.message = message;

    }

    public LegacyException (String message, ErrorType errorType)
    {
        this.errorCode = ErrorCode.LEG_999_UNEXPECTED_ERROR;
        this.erroType = errorType;
        this.message = message;
    }

    public LegacyException(String message, ErrorType errorType, ErrorCode errorCode)
    {
        this.message = message;
        this.erroType = errorType;
        this.errorCode = errorCode;
    }


    public ErrorType getErroType() {
        return erroType;
    }

    public void setErroType(ErrorType erroType) {
        this.erroType = erroType;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
