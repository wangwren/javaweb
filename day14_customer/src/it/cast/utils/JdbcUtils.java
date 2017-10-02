package it.cast.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	static{
		try{
			
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			String driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			Class.forName(driver);
			
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
		
	}

	public static Connection getConnection() throws SQLException{
		
		return DriverManager.getConnection(url, username, password);
	}
	
	
	public static void release(Connection conn,Statement stmt,ResultSet rs){
		
		if(rs != null){
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(stmt != null){
			try{
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			stmt = null;
		}
		
		if(conn != null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
