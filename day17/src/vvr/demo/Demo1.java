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
	
	//ʹ��DBUtils����CURD����
	
	@Test
	public void add() throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user(name,password,email,birthday) values(?,?,?,?)";
		
		//���ڲ��������һ��ֵ��date���ͣ����������ַ�������		
		//Object[] params = {"aaa","123","11@qq,com","1999-09-09"};   �������Ҳ��ִ��
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
	//ʹ��DBUtils����������
	public void batchTest() throws SQLException{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user(name,password,email,birthday) values(?,?,?,?)";
		//������ʹ�ö�ά���鱣��sql��䣬��ά����ĵڶ����������������ֵ
		Object[][] params = new Object[10][];
		
		for(int i = 0; i < 10; i++){
			params[i] = new Object[]{"s" + i,"123","11@qq,com","1999-09-09"};
		}
		runner.batch(sql, params);
	}
	
	// ��dbutils��ɴ����ݣ��������ã�
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
			
			
			//�滻����
			/*String path = Demo1.class.getClassLoader().getResource("1.txt").getPath();
			Object params[] = {new FileReader(path)};
			*/
			
			String path = Demo1.class.getClassLoader().getResource("1.txt").getPath();
			FileReader in = new FileReader(path);
			char[] buffer = new char[(int) new File(path).length()];	//�����д������ļ��Ĵ�С���ַ���ֻ����File.length()����ȡ
			in.read(buffer);
			SerialClob clob = new SerialClob(buffer);
			Object params[] = {clob};
			runner.update(sql, params);
		}

}
