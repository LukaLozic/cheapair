package com.cheapair.exceptions;

public class FlightsServiceException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlightsServiceException(String message, Exception e) {
        super(message);
    }
}
