package demo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import jdbcutils.JdbcUtils;

/*
 * 
 	create database day15;
 	use day15;
 	create table testclob(
 		id int primary key auto_increment,
 		remuse text
 	);
 * 
 * */


//处理文本
public class Demo1 {
	
	@Test
	public void add(){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			
			String path = Demo1.class.getClassLoader().getResource("1.txt").getPath();  //获取到指定文件的路径
			File file = new File(path);  //获取到指定文件，这里使用file方便获取到文件大小
			
			conn = JdbcUtils.getConnection();
			String sql = "insert into testclob(remuse) values(?)";
			stmt = conn.prepareStatement(sql);
			/*
			 * 第一个参数：对应表中的列
			 * 第二个参数：必须传入一个reader类型
			 * 第三个参数：文件的大小，在file中有length()方法，可以获取到该文件的大小,虽然选中了Long类型的参数，但是还是要转成int类型，否则编译不通过，具体原因不清楚
			 * 
			 * 设置文本数据至数据库中需要使用stmt.setCharacterStream()方法
			 * */
			stmt.setCharacterStream(1, new FileReader(file), (int)file.length());
			int i = stmt.executeUpdate();
			if(i > 0){
				System.out.println("插入成功！！！");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
		
	}
	
	@Test
	public void read(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "select remuse from testclob where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				Reader reader = rs.getCharacterStream("remuse");  //得到数据库中的大文本数据使用rs.getCharacterStream()方法
				FileWriter out = new FileWriter("e:\\1.txt");  //将得到的结果写入到e盘1.txt文件中
			
				//字符流输出模板
				char[] buffer = new char[1024];
				int len = 0;
				while((len=reader.read(buffer)) > 0){
					out.write(buffer, 0, len);
				}
				
				out.close();
				reader.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
	}
}
