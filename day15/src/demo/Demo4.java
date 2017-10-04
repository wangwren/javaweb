package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jdbcutils.JdbcUtils;


//������ݿ��Զ�����������
public class Demo4 {
	
	
	/*
	 create table test1(
	 	id int primary key auto_increment,
	 	name varchar(20)
	 );
	 * */

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "insert into test1(name) values(?)";
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  //�ڶ���������дҲ���ԣ�Ĭ�ϼ�Ϊ�ò���
			stmt.setString(1, "aaa");
			stmt.executeUpdate();    //�����ִ����ɺ󣬲��ܻ�ȡ��
			
			rs = stmt.getGeneratedKeys();   //��ȡ��ǰ��������
			if(rs.next()){
				System.out.println(rs.getInt(1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
	}

}
