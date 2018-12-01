package com.ps.server.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You have provided illegal argument")
public class InvalidRequiredArgumentException extends Exception {
    public InvalidRequiredArgumentException() {
    }

    public InvalidRequiredArgumentException(String message) {
        super(message);
    }
}
