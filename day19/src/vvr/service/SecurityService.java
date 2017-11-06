package vvr.service;

import java.util.ArrayList;
import java.util.List;

import vvr.dao.PrivilegeDao;
import vvr.dao.RoleDao;
import vvr.domain.Privilege;
import vvr.domain.Role;

//对web层提供所有权限相关功能
public class SecurityService {

	PrivilegeDao pdao = new PrivilegeDao();
	RoleDao rdao = new RoleDao();
	
	
	//对web层提供添加权限的功能
	public void addPrivilege(Privilege p){
		pdao.add(p);
	}
	
	//对web层提供查找权限的功能
	public Privilege findPrivilege(String id){
		return pdao.find(id);
	}
	
	//对web层提供查看所有权限的功能
	public List<Privilege> getAllPrivilege(){
		return pdao.getAll();
	}
	
	//对web层提供添加角色功能
	public void addRole(Role r){
		rdao.add(r);
	}
	
	//对web层提供查找指定角色的功能
	public Role findRole(String id){
		return rdao.find(id);
	}
	
	//对web层提供查找指定角色权限的功能
	public List<Privilege> getRolePrivileges(String role_id){
		return rdao.getRolePrivileges(role_id);
	}
	
	//获取所有角色
	public List<Role> getAllRoles(){
		return rdao.getAllRoles();
	}
	
	//为某个角色授权
	public void updateRolePrivilege(String role_id,String[] privilege_id){
		
		Role role = rdao.find(role_id);
		
		List<Privilege> list = new ArrayList<Privilege>();
		
		for(String p_id : privilege_id){
			Privilege privilege = pdao.find(p_id);
			list.add(privilege);
		}
		
		rdao.updateRolePrivilege(role, list);
	}
}
