package vvr.domain;

import java.util.HashSet;
import java.util.Set;

/*
 * ��ɫ��
 * create table role
		(
			id varchar(40) primary key,
			name varchar(100) not null unique,
			description varchar(255)
		);
 */

//��ɫ��������Ҫ����set���Լ�סȨ�޶��󣬵��ǲ���Ҫ��ס�û�����
public class Role {

	private String id;
	private String name;
	private String description;
	
	//����set��������סȨ�޶���
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
