package vvr.service;

import java.util.List;

import vvr.dao.PrivilegeDao;
import vvr.domain.Privilege;

//对web层提供所有权限相关功能
public class SecurityService {

	PrivilegeDao pdao = new PrivilegeDao();
	
	
	//对web层提供添加权限的功能
	public void add(Privilege p){
		pdao.add(p);
	}
	
	//对web层提供查找权限的功能
	public Privilege find(String id){
		return pdao.find(id);
	}
	
	//对web层提供查看所有权限的功能
	public List<Privilege> getAll(){
		return pdao.getAll();
	}
}
