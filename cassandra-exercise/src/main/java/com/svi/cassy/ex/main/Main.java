package com.svi.cassy.ex.main;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

public class Main {

	public static void main(String[] args) {
		SocketOptions socketOptions;
		Cluster cluster;

		Session session;
		MappingManager manager;
				
		String CASSANDRA_IP = "18.209.183.205";
		String KEYSPACE = "trainee_keyspace";
		
		socketOptions = new SocketOptions().setReadTimeoutMillis(1000000000);
		cluster = Cluster.builder().addContactPoint(CASSANDRA_IP)
				.withSocketOptions(socketOptions).build();
		
		session = cluster.connect(KEYSPACE);
		manager = new MappingManager(session);
		
		CassandraAccessor accessor = manager.createAccessor(CassandraAccessor.class);
		
		//EXAMPLE 1 *****************************************************
		String query = "SELECT * from anacay_training;";
		
		ResultSet results = session.execute(query);
		
		List<Row> resultList = results.all();
		
		resultList.forEach(result -> {
			
			ColumnDefinitions colDef = result.getColumnDefinitions();
			
			
			String columnName = "";
			
			for(int i=0; i < colDef.size() ; i++) {
				columnName = colDef.getName(i);
				Object value = result.getObject(columnName);
				System.out.print(value.toString() + "\t");
			}
			
			System.out.println();
		});
		
		//EXAMPLE 2****************************************************
		
		Result<OrdersDTO> ordersQueryResult = accessor.getOrders1(UUID.fromString("90d0a452-9c17-4a9a-a497-a55dddb64aff"));
		
		if(ordersQueryResult.getAvailableWithoutFetching() > 0) {
			List<OrdersDTO> list = ordersQueryResult.all();
			
			list.forEach(ordr -> {
				System.out.printf(ordr.getCustomer_id().toString());
			});
		}
		
		
		
		//EXAMPLE 3*********************************************
		
		
		OrdersDTO newOrder = new OrdersDTO();
		
		newOrder.setCustomer_id(UUID.randomUUID());
		newOrder.setOrder_date(new Date());
		newOrder.setOrder_id(UUID.randomUUID());
		newOrder.setTotal_amount(new BigDecimal(1000.999));
		
		
		Mapper<OrdersDTO> ordersMapper = manager.mapper(OrdersDTO.class);
		
		ordersMapper.save(newOrder);
		
		session.close();
		cluster.close();
		
	}

}
