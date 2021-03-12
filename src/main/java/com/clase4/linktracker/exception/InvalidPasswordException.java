package com.clase4.linktracker.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends LinkTrackerBaseException{
    public InvalidPasswordException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
