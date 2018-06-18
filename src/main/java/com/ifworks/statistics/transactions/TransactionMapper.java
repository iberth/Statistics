package com.ifworks.statistics.transactions;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransactionMapper {

	@Options(useGeneratedKeys = true)
	@Insert("INSERT INTO transactions (amount,timestamp)" + " VALUES (#{amount},#{timestamp})")
	void save(Transaction transaction);

	@Select("SELECT SUM(amount) AS sum_value"
			+ ", AVG(amount) AS avg_value"
			+ ", MAX(amount) AS max_value"
			+ ", MIN(amount) AS min_value"
			+ ", count(*) AS count_value"
			+ " FROM transactions"
			+ " WHERE timestamp >= #{timestampThreshold}")
	@Results({ 
		@Result(property = "sum", column = "sum_value"),
		@Result(property = "avg", column = "avg_value"),
		@Result(property = "max", column = "max_value"),
		@Result(property = "min", column = "min_value"),
		@Result(property = "count", column = "count_value") })
	Statistic getStatistics(long timestampThreshold);

}
