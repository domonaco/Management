package it.monaco.medical.service.controllers.dtos;

import it.monaco.medical.service.model.enums.ErrorCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import it.monaco.medical.service.model.enums.ErrorCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ErrorDTO implements Serializable {

    private ErrorCode errorCode;
    private String message;
    private List<String> errors;

    public ErrorDTO(ErrorCode errorCode, String message, List<String> errors) {

        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public ErrorDTO(ErrorCode errorCode,String message, String error) {
        this.errorCode = errorCode;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
