package com.ifworks.statistics.exception;

public class StatisticsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8489405343893285209L;

	public StatisticsException(String message) {
		super(message);
	}

	public StatisticsException(Exception e) {
		super(e.getMessage(), e);
	}

	public StatisticsException(String message, Exception e) {
		super(message, e);
	}

}