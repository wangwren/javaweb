package vvr.domain;

import java.util.HashSet;
import java.util.Set;

/*
 * 角色表
 * create table role
		(
			id varchar(40) primary key,
			name varchar(100) not null unique,
			description varchar(255)
		);
 */

//角色对象中需要设置set属性记住权限对象，但是不需要记住用户对象
public class Role {

	private String id;
	private String name;
	private String description;
	
	//设置set属性来记住权限对象
	private Set<Privilege> privilieges = new HashSet<Privilege>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Privilege> getPrivilieges() {
		return privilieges;
	}

	public void setPrivilieges(Set<Privilege> privilieges) {
		this.privilieges = privilieges;
	}

	
	
	
}
