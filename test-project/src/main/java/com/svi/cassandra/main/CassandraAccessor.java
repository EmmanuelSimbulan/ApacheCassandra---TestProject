package com.svi.cassandra.main;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface CassandraAccessor {

	@Query("SELECT * FROM yman_training WHERE user_id = :user_id")
	Result<UsersDTO> getUsers1(@Param("user_id") UUID userId);
	
	@Query("SELECT * FROM yman_training WHERE user_id = :user_id AND created_at = :created_at")
	Result<UsersDTO> getUsers2(@Param("user_id") UUID userUuid, @Param("created_at") Date created_at);
}
