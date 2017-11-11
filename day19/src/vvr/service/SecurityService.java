package vvr.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vvr.dao.PrivilegeDao;
import vvr.dao.RoleDao;
import vvr.dao.UserDao;
import vvr.domain.Privilege;
import vvr.domain.Role;
import vvr.domain.User;

//��web���ṩ����Ȩ����ع���
public class SecurityService {

	private PrivilegeDao pdao = new PrivilegeDao();
	private RoleDao rdao = new RoleDao();
	private UserDao udao =  new UserDao();
	
	//��web���ṩ���Ȩ�޵Ĺ���
	public void addPrivilege(Privilege p){
		pdao.add(p);
	}
	
	//��web���ṩ����Ȩ�޵Ĺ���
	public Privilege findPrivilege(String id){
		return pdao.find(id);
	}
	
	//��web���ṩ�鿴����Ȩ�޵Ĺ���
	public List<Privilege> getAllPrivilege(){
		return pdao.getAll();
	}
	
	//��web���ṩ��ӽ�ɫ����
	public void addRole(Role r){
		rdao.add(r);
	}
	
	//��web���ṩ����ָ����ɫ�Ĺ���
	public Role findRole(String id){
		return rdao.find(id);
	}
	
	//��web���ṩ����ָ����ɫȨ�޵Ĺ���
	public List<Privilege> getRolePrivileges(String role_id){
		return rdao.getRolePrivileges(role_id);
	}
	
	//��ȡ���н�ɫ
	public List<Role> getAllRoles(){
		return rdao.getAllRoles();
	}
	
	//Ϊĳ����ɫ��Ȩ
	public void updateRolePrivilege(String role_id,String[] privilege_id){
		
		Role role = rdao.find(role_id);
		
		List<Privilege> list = new ArrayList<Privilege>();
		for(int i = 0;privilege_id != null && i < privilege_id.length;i++){
			
			Privilege privilege = pdao.find(privilege_id[i]);
			list.add(privilege);
		}
		
		rdao.updateRolePrivilege(role, list);
	}
	
	//����û���Ϣ
	public void addUser(User user){
		udao.add(user);
	}
	
	//�����û�
	public User findUser(String id){
		return udao.find(id);
	}
	
	//��ȡ�����û�
	public List<User> getAllUser(){
		return udao.getAll();
	}
	
	//����ָ���û���ɫ�Ĺ���
	public List<Role> getUserRoles(String user_id){
		return udao.getUserRoles(user_id);
	}
	
	//Ϊĳ���û������ɫ
	public void updateUserRoles(String user_id,String[] roles_id){
		
		User user = udao.find(user_id);
		List<Role> roles = new ArrayList<Role>();
		for(int i = 0;roles_id != null && i < roles_id.length;i++){
			
			Role role = rdao.find(roles_id[i]);
			roles.add(role);
		}
		
		udao.updateUserRoles(user, roles);
	}

	public Set getUserAllPrivilege(String user_id) {
		
		Set allPrivilege = new HashSet();
		
		//�õ��û�ӵ�еĽ�ɫ
		List<Role> user_roles = udao.getUserRoles(user_id);
		
		//�õ���ɫӵ�е�Ȩ��
		for(Role r : user_roles){
			List role_privilege = rdao.getRolePrivileges(r.getId());
			allPrivilege.addAll(role_privilege); //addAll()�����������һ������
		}
		
		return allPrivilege;
		
	}

	public User login(String username, String password) {
		return udao.find(username, password);
		
	}
}
