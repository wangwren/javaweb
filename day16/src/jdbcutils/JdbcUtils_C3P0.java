package jdbcutils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils_C3P0 {
	
	private static ComboPooledDataSource ds = null;	//数据源
	
	static{
		try{
			
			/*ds = new ComboPooledDataSource();
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/day16?useSSL=false");
			
			ds.setUser("root");
			ds.setPassword("root");
			
			ds.setInitialPoolSize(10);
			ds.setMinPoolSize(5);
			ds.setMaxPoolSize(20);*/
			
			//使用xml配置文件
			//使用xml文件好处，可以配置多个，如果new ComboPooledDataSource("mysql");中的参数不指定，那么将使用默认的。这里的参数值是配置文件中的name值
			ds = new ComboPooledDataSource("mysql");
			
			
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	

	public static Connection getConnection() throws SQLException{
		
		return ds.getConnection();
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
