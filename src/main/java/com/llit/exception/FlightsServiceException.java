package com.llit.exception;

public class FlightsServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public FlightsServiceException(String message, Exception e) {
        super(message, e); // Passing the cause 'e'
    }
}
