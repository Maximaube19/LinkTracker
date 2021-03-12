package com.clase4.linktracker.exception;

import org.springframework.http.HttpStatus;

public class LinkNotFoundException extends LinkTrackerBaseException{
    public LinkNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
