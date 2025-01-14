package com.svi.cassy.ex.main;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(name = "orders", caseSensitiveTable = false)
public class OrdersDTO {

	@PartitionKey(0)
	@Column(name = "customer_id")
	private UUID customer_id;

	@ClusteringColumn(0)
	@Column(name = "order_date")
	private Date order_date;

	@Column(name = "order_id")
	private UUID order_id;

	@Column(name = "total_amount")
	private BigDecimal total_amount;

	public UUID getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(UUID customer_id) {
		this.customer_id = customer_id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public UUID getOrder_id() {
		return order_id;
	}

	public void setOrder_id(UUID order_id) {
		this.order_id = order_id;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	@Override
	public String toString() {
		return "OrdersDTO [customer_id=" + customer_id + ", order_date=" + order_date + ", order_id=" + order_id
				+ ", total_amount=" + total_amount + "]";
	}

	

}
