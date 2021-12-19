package com.colco.demo.domain.exceptions;

public class InvalidDrugIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4438580093824414321L;
	public InvalidDrugIdException(String message) {
		super(message);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
