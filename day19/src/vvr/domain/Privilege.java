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
	
	public Privilege(){
		
	}
	
	public Privilege(String name){
		this.name = name;
	}
	
	
	
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privilege other = (Privilege) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	
}
