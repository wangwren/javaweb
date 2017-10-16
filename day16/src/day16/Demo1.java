package day16;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcutils.JdbcUtils;
import jdbcutils.JdbcUtils_C3P0;
import jdbcutils.JdbcUtils_DBCP;

//事务
public class Demo1 {

	/**
	 * 模似转帐
	  
create table account(
	id int primary key auto_increment,
	name varchar(40),
	money float
)character set utf8 collate utf8_general_ci;

insert into account(name,money) values('aaa',1000);
insert into account(name,money) values('bbb',1000);
insert into account(name,money) values('ccc',1000);
	 
	 */
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			
			conn = JdbcUtils_C3P0.getConnection();
			conn.setAutoCommit(false);     //开启事务     start transaction
			
			String sql1 = "update account set money=money-100 where name='aaa'"; 
			stmt = conn.prepareStatement(sql1);
			stmt.executeUpdate();
			
			//int i = 1/0;		//特意设置一个异常，使程序出错，观察数据库中的数据是否有变化
			
			String sql2 = "update account set money=money+100 where name='bbb'";
			stmt = conn.prepareStatement(sql2);
			stmt.executeUpdate();
			
			
			conn.commit();   //事务提交，事务提交了，两个sql语句才会生效，否则当语句结束，没有提交，数据回回滚至初始状态\
			
			System.out.println("成功");
			
			
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			JdbcUtils_C3P0.release(conn, stmt, rs);
		}

	}

}
