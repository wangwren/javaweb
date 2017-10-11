package day16;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import jdbcutils.JdbcUtils;

//��������ع���
public class Demo2 {

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
		Savepoint sp = null;
		
		try{
			
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false);     //��������     start transaction
			
			String sql1 = "update account set money=money-100 where name='aaa'"; 
			stmt = conn.prepareStatement(sql1);
			stmt.executeUpdate();
			
			sp = conn.setSavepoint();	//��������ع��㣬������������⣬���ݿ�ػع����⣬��һ��sql���ᱻִ��
			
			String sql2 = "update account set money=money+100 where name='bbb'";
			stmt = conn.prepareStatement(sql2);
			stmt.executeUpdate();
			
			
			int i = 1/0;		//��������һ���쳣��ʹ��������۲����ݿ��е������Ƿ��б仯
			
			String sql3 = "update account set money=money+100 where name='ccc'";
			stmt = conn.prepareStatement(sql3);
			stmt.executeUpdate();
			
			conn.commit();   //�����ύ�������ύ�ˣ�����sql���Ż���Ч��������������û���ύ�����ݻػع�����ʼ״̬
			
			
		}catch(Exception e){
			
			try {
				conn.rollback(sp);   //�ع���sp��λ��
				conn.commit();		//�����ύ�������һ�����Ҳ���ᱻ���ݿ�ִ��
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			JdbcUtils.release(conn, stmt, rs);
		}

	}

}
