package it.cast.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.cast.domain.Customer;
import it.cast.exception.DaoException;
import it.cast.utils.JdbcUtils;

public class CustomerDaoImpl {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	public void add(Customer customer){
		
		try {
			
			conn = JdbcUtils.getConnection();
			String sql = "insert into customer(id,name,gender,birthday,cellphone,email,preference,type,description) values(?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, customer.getId());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getGender());
			stmt.setDate(4,  new java.sql.Date(customer.getBirthday().getTime()));
			stmt.setString(5, customer.getCellphone());
			stmt.setString(6, customer.getEmail());
			stmt.setString(7, customer.getPreference());
			stmt.setString(8, customer.getType());
			stmt.setString(9, customer.getDescription());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		finally{
			JdbcUtils.release(conn, stmt, rs);
		}
	}
	
	public void delete(String id){
		
		try{
			
			conn = JdbcUtils.getConnection();
			String sql = "delete from customer where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
			
		}catch(Exception e){
			
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
		
	}
	
	public void update(Customer c){
		
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=? where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getGender());
			stmt.setDate(3, new java.sql.Date(c.getBirthday().getTime()));
			stmt.setString(4, c.getCellphone());
			stmt.setString(5, c.getEmail());
			stmt.setString(6, c.getPreference());
			stmt.setString(7, c.getType());
			stmt.setString(8, c.getDescription());
			stmt.setString(9, c.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
	}
	
	public Customer find(String id){
		
		try{
			
			conn = JdbcUtils.getConnection();
			String sql = "select * from customer where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()){
				Customer c = new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setDescription(rs.getString("description"));
				c.setEmail(rs.getString("email"));
				c.setGender(rs.getString("gender"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));
				c.setId(rs.getString("id"));
				return c;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			JdbcUtils.release(conn, stmt, rs);
		}
		return null;
	}
	
	//获取所有用户的数据
	public List<Customer> getAll(){
		
		try {	
			
			conn = JdbcUtils.getConnection();
			String sql = "select * from customer";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<Customer> list = new ArrayList<>();
			
			while(rs.next()){
				Customer c = new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setDescription(rs.getString("description"));
				c.setEmail(rs.getString("email"));
				c.setGender(rs.getString("gender"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));
				
				list.add(c);
			}
			
			return list.size() > 0 ? list : null;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		finally{
			JdbcUtils.release(conn, stmt, rs);
		}
		
	}
	
	//获取分页数据,方法的两个参数代表想看的 起始数据（起始数据从0开始计算，如果页面显示的10条数据，第二页则从10开始计算） 和当前页面显示的总数据个数
	public List<Customer> getPageData(int startindex,int pagesize){
		
		try {
			
			conn = JdbcUtils.getConnection();
			String sql = "select * from customer limit ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, startindex);
			stmt.setInt(2, pagesize);
			rs = stmt.executeQuery();
			
			List<Customer> list = new ArrayList<>();
			
			while(rs.next()){
				Customer c = new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setDescription(rs.getString("description"));
				c.setEmail(rs.getString("email"));
				c.setGender(rs.getString("gender"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));
				
				list.add(c);
			}
			return list.size() > 0 ? list : null;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		finally{
			JdbcUtils.release(conn, stmt, rs);
		}
		
	}
	
	
	//得到记录总数
	public int getTotalrecord(){
		
		try {
			
			conn = JdbcUtils.getConnection();
			String sql = "select count(*) from customer";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
			return 0;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		finally{
			JdbcUtils.release(conn, stmt, rs);
		}
		
	}

}
