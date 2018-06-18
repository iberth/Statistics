package com.ifworks.statistics.transactions;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ifworks.statistics.exception.StatisticsException;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService service;

	@RequestMapping(value = "/transactions", consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<String> create(@Valid @RequestBody Transaction transaction) throws StatisticsException {

		service.saveTransaction(transaction);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/statistics", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Statistic> getStatistics() throws StatisticsException {
		return new ResponseEntity<>(service.getStatistics(), HttpStatus.OK);
	}
}