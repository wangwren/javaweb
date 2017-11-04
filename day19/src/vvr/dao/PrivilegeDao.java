package vvr.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import vvr.domain.Privilege;

public class PrivilegeDao {

	//����Ȩ��
	public void add(Privilege p){
		try{
			
			QueryRunner query = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into privilege(id,name.description) values(?,?,?)";
			Object[] param = {p.getId(),p.getName(),p.getDescription()};
			query.update(sql, param);
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//�鿴ָ��Ȩ��
	public Privilege find(String id){
		try{
			
			QueryRunner query = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from privilege where id=?";
			return query.query(sql, new BeanHandler(Privilege.class), id);
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	//�鿴ȫ��Ȩ��
	public List<Privilege> getAll(){
		try{
			
			QueryRunner query = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from privilege";
			return query.query(sql, new BeanListHandler(Privilege.class));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
}
