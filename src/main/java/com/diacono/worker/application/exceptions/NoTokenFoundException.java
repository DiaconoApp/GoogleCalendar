package com.diacono.worker.application.exceptions;

public class NoTokenFoundException extends RuntimeException {
    public NoTokenFoundException(String message) {
        super(message);
    }
}
