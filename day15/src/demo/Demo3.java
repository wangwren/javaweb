package demo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import jdbcutils.JdbcUtils;

//批处理语句
public class Demo3 {

	/*
	 create table testbatch(
	 	id int primary key,
	 	name varchar(20)
	 );
	 * */
	
	
	//使用statement进行批处理
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
			st.addBatch(sql3);   //将所有的sql语句添加到批处理命令中
			
			int[] i = st.executeBatch();  //执行批处理命令
			st.clearBatch();  //清除批处理命令
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
		
	}
	
	@Test
	//使用PreparedStatement处理批数据，适合处理语句相同但参数不同的数据
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
					
					//当缓存中有100条数据时就批量执行sql语句
					
					stmt.executeBatch();
					stmt.clearBatch();   //每次批量执行后，一定要清除缓存中的数据，否则容易溢出
				}
			}
			
			stmt.executeBatch();   //例如当前例子，循环后，还有四条语句未执行，所以最后也要执行一遍
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, stmt, rs);
		}
		
		Long endtime = System.currentTimeMillis();
		System.out.println("程序花费时间：" + (endtime - starttime)/1000 + "秒");
	}
}
