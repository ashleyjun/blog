package com.dlj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil { 
	/**
	 * 取得数据库的连接
	 * 
	 * @return 一个数据库的连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 初始化驱动类com.mysql.jdbc.Driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8", "root", "");
			// 该类就在
			// mysql-connector-java-5.0.8-bin.jar中,如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
