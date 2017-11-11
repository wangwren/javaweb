package vvr.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import vvr.domain.Role;
import vvr.domain.User;

public class UserDao {

	//添加用户
	public void add(User user){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user(id,username,password) values(?,?,?)";
			Object[] params = {user.getId(),user.getUsername(),user.getPassword()};
			runner.update(sql, params);
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//查找用户
	public User find(String id){
		
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where id=?";
			return runner.query(sql, id, new BeanHandler(User.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	//查找用户，用户登录
	public User find(String userid,String password){
		
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			Object[] params = {userid,password};
			return runner.query(sql, params, new BeanHandler(User.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	//查询所有用户
	public List<User> getAll(){
		
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user";
			return runner.query(sql, new BeanListHandler(User.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	//获取指定用户的角色
	public List<Role> getUserRoles(String user_id){
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select r.* from user_role ur,role r where ur.user_id=? and ur.role_id=r.id";
			return runner.query(sql, user_id, new BeanListHandler(Role.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	//更改用户角色
	public void updateUserRoles(User user,List<Role> roles){
		
		try{
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			//删除中间表中的用户角色
			String sql = "delete from user_role where user_id=?";
			runner.update(sql, user.getId());
			//更新用户的角色
			for(int i = 0;i < roles.size();i++){
				Role role = roles.get(i);
				sql = "insert into user_role(user_id,role_id) values(?,?)";
				Object[] params = {user.getId(),role.getId()};
				runner.update(sql, params);
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
