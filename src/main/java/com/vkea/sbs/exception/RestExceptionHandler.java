package com.vkea.sbs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    RestError handleIllegalArgumentException(IllegalArgumentException ex) {
        RestError apiError =
                new RestError("1009", ex.getLocalizedMessage(), "0099");
        return apiError;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    RestError handleGeneralException(Exception ex) {
        RestError restError =
                new RestError("1100", ex.getLocalizedMessage(), "Unknown exception");
        return restError;
    }

}
//BUG: can't return APIError to the client, because the getter & setter are not implemented in the class