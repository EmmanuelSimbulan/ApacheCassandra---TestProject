package com.svi.cassandra.main;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

// DTO means Data Transfer Object
@Table(name = "yman_training", caseSensitiveTable = false)
public class UsersDTO {
	
	@PartitionKey(0)
	@Column(name = "user_id")
	private UUID user_id;
	
	@ClusteringColumn(0)
	@Column(name = "created_at")
	private Date created_at;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "username")
	private String username;

	public UUID getUser_id() {
		return user_id;
	}

	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UsersDTO [user_id=" + user_id + ", created_at=" + created_at + ", email=" + email + ", username="
				+ username + "]";
	}
	
	
	
	

}
