package com.ifworks.statistics.transactions;

import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class TransactionRepository {

    private static Map<Long, Double> transactionsCache = new ConcurrentHashMap<Long, Double>();
    
    public void save(Transaction transaction) {
    	transactionsCache.put(transaction.getTimestamp(), transaction.getAmount());
    }
    
    public Statistic getStatistics(long timestampThreshold) {
    	
    	Statistic statistic = new Statistic();
    	
    	Map<Long, Double> transactions = transactionsCache.entrySet().stream()
    			.filter(transaction -> transaction.getKey() >= timestampThreshold )
    			.collect(Collectors.toMap(transaction -> transaction.getKey(), transaction -> transaction.getValue()));
    	
    	DoubleSummaryStatistics stats = transactions.values().stream()
    			.collect(Collectors.summarizingDouble(Double::doubleValue));
    	
    	statistic.setCount(transactions.size());
    	statistic.setSum(stats.getSum());
    	statistic.setAvg(stats.getAverage());
    	statistic.setMax(stats.getMax() == Double.NEGATIVE_INFINITY ? 0 : stats.getMax());
    	statistic.setMin(stats.getMin() == Double.POSITIVE_INFINITY ? 0 : stats.getMin());
    	
    	return statistic;
    }

}
