package com.vkea.sbs.exception;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestError {

    private String code;
    private String message;
    private List<String> errors;

    public RestError(String code, String message, List<String> errors) {
        super();
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public RestError(String code, String message, String error) {
        super();
        this.code = code;
        this.message = message;
        errors = Arrays.asList(error);
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}