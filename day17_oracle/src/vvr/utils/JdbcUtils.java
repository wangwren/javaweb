package vvr.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class JdbcUtils {
	
	private static DataSource ds = null;
	
	static{
		try{
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			ds = factory.createDataSource(prop);
		}catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}

}
