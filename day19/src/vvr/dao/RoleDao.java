package vvr.dao;


import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import vvr.domain.Privilege;
import vvr.domain.Role;

public class RoleDao {
	
	//增加角色
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
	
	//查看指定角色
	public Role find(String id){
		
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from role where id=?";
			return (Role) runner.query(sql, id, new BeanHandler(Role.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//获取指定角色的权限*
	public List<Privilege> getRolePrivileges(String role_id){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			//查询中间表，获取到指定role对应的权限，再查privilege表中对应
			String sql = "select p.* from role_privilege rp,privilege p where role_id=? and rp.privilege_id=p.id";
			return (List<Privilege>) runner.query(sql, role_id, new BeanListHandler(Privilege.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//获取所有角色
	public List<Role> getAllRoles(){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from role";
			return runner.query(sql, new BeanListHandler(Role.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//为某个角色增加授权
	public void updateRolePrivilege(Role r,List<Privilege> privileges){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			//先删除角色拥有的权限
			String sql = "delete from role_privilege where role_id=?";
			runner.update(sql, r.getId());
			//给角色授于新的权限
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
