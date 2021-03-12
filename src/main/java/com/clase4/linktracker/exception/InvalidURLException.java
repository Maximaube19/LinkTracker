package com.clase4.linktracker.exception;

import org.springframework.http.HttpStatus;

public class InvalidURLException extends LinkTrackerBaseException{
    public InvalidURLException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
