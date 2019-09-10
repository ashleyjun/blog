package com.dlj.test;

import java.sql.PreparedStatement;
import java.util.UUID;

import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UuidTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into t_uuid(uuid, create_time, update_time) values (?, now(), now())";
		try {
			connection = (Connection) DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			for (int i = 0; i < 1000000; i++) {
				String uuid = UUID.randomUUID().toString();
				System.out.println(uuid);
				preparedStatement.setString(1, uuid);
				
				boolean success = preparedStatement.execute();
				
				System.out.println("执行结果：" + success);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//errorMsg = e.getMessage();
		} finally {
			DBUtil.close(connection, preparedStatement, null);
		}
	}

}
