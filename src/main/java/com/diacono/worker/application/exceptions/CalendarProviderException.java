package com.diacono.worker.application.exceptions;

public class CalendarProviderException extends RuntimeException {
    public CalendarProviderException(String message) {
        super(message);
    }

    public CalendarProviderException(String message, Throwable cause) {
        super(message, cause);
    }

}
