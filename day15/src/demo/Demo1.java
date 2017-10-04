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


//�����ı�
public class Demo1 {
	
	@Test
	public void add(){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			
			String path = Demo1.class.getClassLoader().getResource("1.txt").getPath();  //��ȡ��ָ���ļ���·��
			File file = new File(path);  //��ȡ��ָ���ļ�������ʹ��file�����ȡ���ļ���С
			
			conn = JdbcUtils.getConnection();
			String sql = "insert into testclob(remuse) values(?)";
			stmt = conn.prepareStatement(sql);
			/*
			 * ��һ����������Ӧ���е���
			 * �ڶ������������봫��һ��reader����
			 * �������������ļ��Ĵ�С����file����length()���������Ի�ȡ�����ļ��Ĵ�С,��Ȼѡ����Long���͵Ĳ��������ǻ���Ҫת��int���ͣ�������벻ͨ��������ԭ�����
			 * 
			 * �����ı����������ݿ�����Ҫʹ��stmt.setCharacterStream()����
			 * */
			stmt.setCharacterStream(1, new FileReader(file), (int)file.length());
			int i = stmt.executeUpdate();
			if(i > 0){
				System.out.println("����ɹ�������");
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
				
				Reader reader = rs.getCharacterStream("remuse");  //�õ����ݿ��еĴ��ı�����ʹ��rs.getCharacterStream()����
				FileWriter out = new FileWriter("e:\\1.txt");  //���õ��Ľ��д�뵽e��1.txt�ļ���
			
				//�ַ������ģ��
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
