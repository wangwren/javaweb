package vvr.dao;


import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import vvr.domain.Privilege;
import vvr.domain.Role;

public class RoleDao {
	
	//���ӽ�ɫ
	public void add(Role r){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into role(id,name,description) values(?,?,?)";
			Object[] params = {r.getId(),r.getName(),r.getDescription()};
			runner.update(sql, params);
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//�鿴ָ����ɫ
	public Role find(String id){
		
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from role where id=?";
			return (Role) runner.query(sql, id, new BeanHandler(Role.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//��ȡָ����ɫ��Ȩ��*
	public List<Privilege> getRolePrivileges(String role_id){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			//��ѯ�м����ȡ��ָ��role��Ӧ��Ȩ�ޣ��ٲ�privilege���ж�Ӧ
			String sql = "select p.* from role_privilege rp,privilege p where role_id=? and rp.privilege_id=p.id";
			return (List<Privilege>) runner.query(sql, role_id, new BeanListHandler(Privilege.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//��ȡ���н�ɫ
	public List<Role> getAllRoles(){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from role";
			return runner.query(sql, new BeanListHandler(Role.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//Ϊĳ����ɫ������Ȩ
	public void updateRolePrivilege(Role r,List<Privilege> privileges){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			//��ɾ����ɫӵ�е�Ȩ��
			String sql = "delete from role_privilege where role_id=?";
			runner.update(sql, r.getId());
			//����ɫ�����µ�Ȩ��
			for(Privilege p : privileges){
				sql = "insert into role_privilege(role_id,privilege_id) values(?,?)";
				Object params[] = {r.getId(),p.getId()};
				runner.update(sql, params);
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
