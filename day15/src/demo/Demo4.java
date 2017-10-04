package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jdbcutils.JdbcUtils;


//获得数据库自动增长的主键
public class Demo4 {
	
	
	/*
	 create table test1(
	 	id int primary key auto_increment,
	 	name varchar(20)
	 );
	 * */

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "insert into test1(name) values(?)";
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  //第二个参数不写也可以，默认即为该参数
			stmt.setString(1, "aaa");
			stmt.executeUpdate();    //当语句执行完成后，才能获取到
			
			rs = stmt.getGeneratedKeys();   //获取当前语句的主键
			if(rs.next()){
				System.out.println(rs.getInt(1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
	}

}
