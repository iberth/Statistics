package com.ifworks.statistics.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ifworks.statistics.transactions.TransactionExpiredException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(StatisticsException.class)
	public ResponseEntity<Object> handleStatisticsException(StatisticsException e) {
		LOGGER.info("Error with status: {}, message: {}", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TransactionExpiredException.class)
	public ResponseEntity<Object> handleTransactionException(TransactionExpiredException e) {
		LOGGER.info("Error with status: {}, message: {}", HttpStatus.NO_CONTENT, e.getMessage());

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
