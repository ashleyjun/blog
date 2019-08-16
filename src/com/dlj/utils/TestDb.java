package com.dlj.utils;

import com.mysql.jdbc.Connection;

public class TestDb {
	public static void main(String[] args) {
		Connection conn = (Connection) DBUtil.getConnection();
		if (null != conn) {
			System.out.println("连接成功！");
		} else {
			System.out.println("连接失败！");
		}
	}
}
