package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import jdbcutils.JdbcUtils;

//������������ݣ����磺ͼƬ����Ƶ��
public class Demo2 {
	
	/*
	 create table testblob(
	 	id int primary key auto_increment,
	 	image blob
	 );
	 * */

	@Test
	public void add(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "insert into testblob(image) values(?)";
			stmt = conn.prepareStatement(sql);
			
			String path = Demo2.class.getClassLoader().getResource("1.jpg").getPath();
			/*
			 ���ڶ��������ݣ����ܹ���ʹ���ļ���д�ˣ���Ҫʹ��stmt.setBinaryStream()����
			 �����ĵڶ���������Ҫһ��inputstream�������������������ݵĴ�С�����Ի�����Ҫʹ��file����ȡ
			 * */
			stmt.setBinaryStream(1, new FileInputStream(path), (int)new File(path).length());
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
			String sql = "select image from testblob where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			rs = stmt.executeQuery();
			if(rs.next()){
				InputStream in = rs.getBinaryStream("image");
				FileOutputStream out = new FileOutputStream("e:\\1.jpg");
				
				byte[] b = new byte[1024];
				int len = 0;
				while((len = in.read(b)) > 0){
					out.write(b, 0, len);
				}
				out.close();
				in.close();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
	}
}
