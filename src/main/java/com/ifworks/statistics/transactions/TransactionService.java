package com.ifworks.statistics.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {
	
	private static final long THRESHOLD_FOR_VALID_TIMESTAMP = 60*1000; 

	@Autowired
	private TransactionRepository repository;

	public void saveTransaction(Transaction transaction) throws TransactionExpiredException {

		if (!isValidTimestamp(transaction.getTimestamp())) {
			throw new TransactionExpiredException("Transaction is older than 60 seconds.");
		}

		repository.save(transaction);
	}

	public Statistic getStatistics() {
		return repository.getStatistics(getTimestmapThreshold());
	}

	private boolean isValidTimestamp(Long timestamp) {
		return timestamp >= getTimestmapThreshold();
	}
	
	private long getTimestmapThreshold() {
		return System.currentTimeMillis() - THRESHOLD_FOR_VALID_TIMESTAMP;
	}
}
