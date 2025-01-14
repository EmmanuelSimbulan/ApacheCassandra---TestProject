package com.svi.cassy.ex.main;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface CassandraAccessor {

	@Query("SELECT * FROM orders WHERE customer_id = :customer_id")
	Result<OrdersDTO> getOrders1(@Param("customer_id") UUID customerId);

	@Query("SELECT * FROM orders WHERE customer_id = :customer_id AND order_date = :order_date")
	Result<OrdersDTO> getOrders2(@Param("customer_id") UUID customerId, @Param("order_date") Date order_date);
}
