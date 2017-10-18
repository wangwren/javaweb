package vvr.demo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import vvr.domain.User;
import vvr.utils.JDBCUtils;

/*
  create database day17; 
  use day17;
	
	 create table user(
	 id int primary key auto_increment, 
	 name varchar(40),
	 password varchar(40),
	 email varchar(60),
	 birthday date 
	 );
	 
	 
	 insert into user(name,password,email,birthday)values('zs','123456','zs@sina.com','1980-09-09'); 
	 insert into user(name,password,email,birthday) values('lisi','123456','lisi@sina.com','1980-09-09'); 
	 insert into user(name,password,email,birthday) values('wangwu','123456','wangwu@sina.com','1980-09-09'); 
 
 * */

public class Demo1 {
	
	//使用DBUtils进行CURD操作
	
	@Test
	public void add() throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user(name,password,email,birthday) values(?,?,?,?)";
		
		//对于参数的最后一个值是date类型，可以输入字符串类型		
		//Object[] params = {"aaa","123","11@qq,com","1999-09-09"};   这条语句也能执行
		Object[] params = {"aaa","123","11@qq.com",new Date()};
		runner.update(sql, params);
	}
	
	@Test
	public void delete() throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from user where id=?";
		runner.update(sql, 1);
	}
	
	@Test
	public void update() throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set name=? where id=?";
		Object[] params = {"ddd",5};
		runner.update(sql, params);
	}
	
	@Test
	public void find() throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where id=?";
		User user = (User) runner.query(sql, 3,new BeanHandler(User.class));
		System.out.println(user.getBirthday());
	}
	
	@Test
	public void findAll() throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user";
		List list = runner.query(sql, new BeanListHandler<User>(User.class));
		System.out.println(list.size());
	}
	
	@Test
	//使用DBUtils进行批处理
	public void batchTest() throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user(name,password,email,birthday) values(?,?,?,?)";
		//批处理使用二维数组保存sql语句，二维数组的第二括号用来保存参数值
		Object[][] params = new Object[10][];
		
		for(int i = 0; i < 10; i++){
			params[i] = new Object[]{"s" + i,"123","11@qq,com","1999-09-09"};
		}
		runner.batch(sql, params);
	}
	
	// 用dbutils完成大数据（不建议用）
		/***************************************************************************
		 create table testclob
		 (
		 	id int primary key auto_increment,
		 	resume text
		 );
		 * @throws SQLException 
		 * @throws IOException 
		 **************************************************************************/
		
		@Test
		public void testclob() throws SQLException, IOException{
			QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "insert into testclob(resume) values(?)";  //clob
			
			
			//替换参数
			/*String path = Demo1.class.getClassLoader().getResource("1.txt").getPath();
			Object params[] = {new FileReader(path)};
			*/
			
			String path = Demo1.class.getClassLoader().getResource("1.txt").getPath();
			FileReader in = new FileReader(path);
			char[] buffer = new char[(int) new File(path).length()];	//参数中传的是文件的大小，字符流只能用File.length()来获取
			in.read(buffer);
			SerialClob clob = new SerialClob(buffer);
			Object params[] = {clob};
			runner.update(sql, params);
		}

}
