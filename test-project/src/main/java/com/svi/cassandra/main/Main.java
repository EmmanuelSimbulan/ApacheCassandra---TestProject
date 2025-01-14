package com.svi.cassandra.main;


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
		
		// EXAMPLE 1: (To view the contents of a specific table)
//		String query = "SELECT * from yman_training;";
//		
//		ResultSet results = session.execute(query);
//		
//		List<Row> resultList = results.all();
//		
//		resultList.forEach(result -> {
//			
//			ColumnDefinitions colDef = result.getColumnDefinitions();
//			
//			String columnName = "";
//			
//			for(int i = 0; i < colDef.size(); i++) {
//				columnName = colDef.getName(i);
//				Object value = result.getObject(columnName);
//				System.out.print(value.toString() + "\t");
//			}
//			
//			System.out.println();
//			
//		});
//		
		
		// EXAMPLE 2 (Using Cassandra Access and Data Transfer Objects)
		
		Result<UsersDTO> usersQueryResult = accessor.getUsers1(UUID.fromString("df95bc1a-1b3a-427d-b65d-30307a4a321c"));
		
		if (usersQueryResult.getAvailableWithoutFetching() > 0) {
			List<UsersDTO> list = usersQueryResult.all();
			
			list.forEach(usr -> {
				System.out.printf(usr.getUser_id().toString());
				System.out.printf(usr.toString());

			});
		}
		
		// EXAMPLE 3 (Used to create new row contents)
		
		UsersDTO newUser = new UsersDTO();
		
		newUser.setUser_id(UUID.randomUUID());
		newUser.setCreated_at(new Date());
		newUser.setUsername("taylor_swift");
		newUser.setEmail("ts@icloud.com");
		
		Mapper<UsersDTO> usersMapper = manager.mapper(UsersDTO.class);
		usersMapper.save(newUser);
		
		session.close();
		cluster.close();
		
		
	}

}
