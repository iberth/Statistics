package com.ifworks.statistics.transactions;

import com.ifworks.statistics.exception.StatisticsException;

public class TransactionExpiredException extends StatisticsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873346691684401091L;

	public TransactionExpiredException(String message) {
		super(message);
	}

	public TransactionExpiredException(Exception e) {
		super(e.getMessage(), e);
	}

	public TransactionExpiredException(String message, Exception e) {
		super(message, e);
	}

}