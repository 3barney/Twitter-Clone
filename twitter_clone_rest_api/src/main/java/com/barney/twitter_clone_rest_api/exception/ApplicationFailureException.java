package com.barney.twitter_clone_rest_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationFailureException extends RuntimeException {
    public ApplicationFailureException(String message) {
        super(message);
    }

    public ApplicationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
