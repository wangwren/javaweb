package vvr.domain;

/*
 * Ȩ�ޱ�
 * create table privilege
		(
			id varchar(40) primary key,
			name varchar(100) not null unique,
			description varchar(255)
		);
 */


//Ȩ�޶���Ȩ�޶������ɫ�����Ƕ�Զ�Ĺ�ϵ��������ʾȨ��ʱ��û��Ҫ����ɫ��ʾ����
//������Ȩ�޶����в���Ҫ����set��������ס��ɫ����
public class Privilege {

	private String id;
	private String name;
	private String description;
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
	
	
}
