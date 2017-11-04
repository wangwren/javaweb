package vvr.domain;

/*
 * 权限表
 * create table privilege
		(
			id varchar(40) primary key,
			name varchar(100) not null unique,
			description varchar(255)
		);
 */


//权限对象，权限对象与角色对象是多对多的关系，但是显示权限时，没必要将角色显示出来
//所以在权限对象中不需要设置set属性来记住角色对象
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
