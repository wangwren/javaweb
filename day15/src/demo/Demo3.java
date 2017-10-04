package demo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import jdbcutils.JdbcUtils;

//���������
public class Demo3 {

	/*
	 create table testbatch(
	 	id int primary key,
	 	name varchar(20)
	 );
	 * */
	
	
	//ʹ��statement����������
	@Test
	public void testBatch1(){
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			
			String sql1 = "insert into testbatch values(1,'aaa')";
			String sql2 = "insert into testbatch values(2,'bbb')";
			String sql3 = "delete from testbatch where id=1";
			
			st = conn.createStatement();
			
			st.addBatch(sql1);
			st.addBatch(sql2);
			st.addBatch(sql3);   //�����е�sql�����ӵ�������������
			
			int[] i = st.executeBatch();  //ִ������������
			st.clearBatch();  //�������������
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
		
	}
	
	@Test
	//ʹ��PreparedStatement���������ݣ��ʺϴ��������ͬ��������ͬ������
	public void testBatch2(){
		
		Long starttime = System.currentTimeMillis();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			
			conn = JdbcUtils.getConnection();
			String sql = "insert into testbatch values(?,?)";
			stmt = conn.prepareStatement(sql);
			
			for(int i = 1;i < 1005;i++){
				stmt.setInt(1, i);
				stmt.setString(2, "aaa" + i);
				
				stmt.addBatch();
				
				if(i % 100 == 0){
					
					//����������100������ʱ������ִ��sql���
					
					stmt.executeBatch();
					stmt.clearBatch();   //ÿ������ִ�к�һ��Ҫ��������е����ݣ������������
				}
			}
			
			stmt.executeBatch();   //���統ǰ���ӣ�ѭ���󣬻����������δִ�У��������ҲҪִ��һ��
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
		
		Long endtime = System.currentTimeMillis();
		System.out.println("���򻨷�ʱ�䣺" + (endtime - starttime)/1000 + "��");
	}
}
