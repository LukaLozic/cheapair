package com.cheapair.exceptions;

public class NoFlightsException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoFlightsException(String message) {
        super(message);
    }
}
