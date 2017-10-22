package vvr.demo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.sql.BLOB;
import vvr.utils.JdbcUtils;

//使用Oracle数据库处理大数据
public class Demo1 {
	
/**
	
	create table testblob
	(
		id int primary key,
		image blob
	);
	
	 */
	
	public static void main(String[] args) {
		//add();
		find();
	}
	
	
	public static void add(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false); 		//开启事务，Oracle数据库想要存入大数据需要开启事务
			
			String sql = "insert into testblob values(?,empty_blob())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.executeUpdate();
			
			sql = "select image from testblob where id=? for update";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			if(rs.next()){
				BLOB blob = (BLOB) rs.getBlob("image");  		//这里必须要导入Oracle的BLOB包，才能对大数据进行读写操作
				OutputStream out = blob.getBinaryOutputStream();//获取到out，写入数据库
				
				//读取图片
				InputStream in = Demo1.class.getClassLoader().getResourceAsStream("1.jpg");
				
				//写入数据库
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = in.read(buffer)) > 0){
					out.write(buffer, 0, len);
				}
				
				in.close();
				out.close();
			}
			conn.commit();
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//JdbcUtils.release(conn, pstmt, rs);
		}
	}
	
	
	public static void find(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "select image from testblob where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			if(rs.next()){
				BLOB blob = (BLOB) rs.getBlob("image");
				InputStream in = blob.getBinaryStream();
				FileOutputStream out = new FileOutputStream("e:\\1.jpg");
				
				byte buffer[] = new byte[1024];
				int len = 0;
				while((len=in.read(buffer))>0){
					out.write(buffer,0,len);
				}
				in.close();
				out.close();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			//JdbcUtils.release(conn, pstmt, rs);
		}
		
	}

}
