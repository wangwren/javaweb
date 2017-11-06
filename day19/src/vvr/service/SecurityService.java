package vvr.service;

import java.util.ArrayList;
import java.util.List;

import vvr.dao.PrivilegeDao;
import vvr.dao.RoleDao;
import vvr.domain.Privilege;
import vvr.domain.Role;

//��web���ṩ����Ȩ����ع���
public class SecurityService {

	PrivilegeDao pdao = new PrivilegeDao();
	RoleDao rdao = new RoleDao();
	
	
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
		
		for(String p_id : privilege_id){
			Privilege privilege = pdao.find(p_id);
			list.add(privilege);
		}
		
		rdao.updateRolePrivilege(role, list);
	}
}
