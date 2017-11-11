package vvr.service;

import java.util.ArrayList;
import java.util.List;

import vvr.dao.PrivilegeDao;
import vvr.dao.RoleDao;
import vvr.dao.UserDao;
import vvr.domain.Privilege;
import vvr.domain.Role;
import vvr.domain.User;

//对web层提供所有权限相关功能
public class SecurityService {

	private PrivilegeDao pdao = new PrivilegeDao();
	private RoleDao rdao = new RoleDao();
	private UserDao udao =  new UserDao();
	
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
	
	//添加用户信息
	public void addUser(User user){
		udao.add(user);
	}
	
	//查找用户
	public User findUser(String id){
		return udao.find(id);
	}
	
	//获取所有用户
	public List<User> getAllUser(){
		return udao.getAll();
	}
	
	//查找指定用户角色的功能
	public List<Role> getUserRoles(String user_id){
		return udao.getUserRoles(user_id);
	}
	
	//为某个用户授予角色
	public void updateUserRoles(String user_id,String[] roles_id){
		
		User user = udao.find(user_id);
		List<Role> roles = new ArrayList<Role>();
		for(String rid : roles_id){
			Role role = rdao.find(rid);
			roles.add(role);
		}
		
		udao.updateUserRoles(user, roles);
	}
}
