package day16;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcutils.JdbcUtils;
import jdbcutils.JdbcUtils_C3P0;
import jdbcutils.JdbcUtils_DBCP;

//����
public class Demo1 {

	/**
	 * ģ��ת��
	  
create table account(
	id int primary key auto_increment,
	name varchar(40),
	money float
)character set utf8 collate utf8_general_ci;

insert into account(name,money) values('aaa',1000);
insert into account(name,money) values('bbb',1000);
insert into account(name,money) values('ccc',1000);
	 
	 */
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			
			conn = JdbcUtils_C3P0.getConnection();
			conn.setAutoCommit(false);     //��������     start transaction
			
			String sql1 = "update account set money=money-100 where name='aaa'"; 
			stmt = conn.prepareStatement(sql1);
			stmt.executeUpdate();
			
			//int i = 1/0;		//��������һ���쳣��ʹ��������۲����ݿ��е������Ƿ��б仯
			
			String sql2 = "update account set money=money+100 where name='bbb'";
			stmt = conn.prepareStatement(sql2);
			stmt.executeUpdate();
			
			
			conn.commit();   //�����ύ�������ύ�ˣ�����sql���Ż���Ч��������������û���ύ�����ݻػع�����ʼ״̬\
			
			System.out.println("�ɹ�");
			
			
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			JdbcUtils_C3P0.release(conn, stmt, rs);
		}

	}

}
