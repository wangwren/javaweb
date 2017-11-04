package vvr.domain;

import java.util.HashSet;
import java.util.Set;

/*
 *用户表
 * create table user
		(
			id varchar(40) primary key,
			username varchar(100) not null unique,
			password varchar(100) not null
		);
 */

public class User {

	private String id;
	private String username;
	private String password;
	
	//设置set属性记住角色对象
	private Set<Role> roles = new HashSet<Role>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
