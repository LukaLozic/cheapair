package com.llit.exception;

public class NoFlightsException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoFlightsException(String message) {
        super(message);
    }
}
