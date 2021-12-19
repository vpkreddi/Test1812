package com.colco.demo.domain.exceptions;

public class InvalidMrepIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3780289083337272916L;
	public InvalidMrepIdException(String message) {
		super(message);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
