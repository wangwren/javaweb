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

public class JdbcUtils_DBCP {
	
	private static DataSource ds = null;	//数据源
	
	static{
		try{
			
			InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");	//读取DBCP的配置文件
			Properties prop = new Properties();
			prop.load(in);
			
			BasicDataSourceFactory factory = new BasicDataSourceFactory();		//创建工厂
			ds = factory.createDataSource(prop);
			
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
