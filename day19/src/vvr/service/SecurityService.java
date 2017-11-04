package vvr.service;

import java.util.List;

import vvr.dao.PrivilegeDao;
import vvr.domain.Privilege;

//��web���ṩ����Ȩ����ع���
public class SecurityService {

	PrivilegeDao pdao = new PrivilegeDao();
	
	
	//��web���ṩ���Ȩ�޵Ĺ���
	public void add(Privilege p){
		pdao.add(p);
	}
	
	//��web���ṩ����Ȩ�޵Ĺ���
	public Privilege find(String id){
		return pdao.find(id);
	}
	
	//��web���ṩ�鿴����Ȩ�޵Ĺ���
	public List<Privilege> getAll(){
		return pdao.getAll();
	}
}
