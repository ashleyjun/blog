package com.dlj.utils;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Count {
	public static void main(String[] args) {
		System.out.println(Count.count("t_user"));
	}

	public static int count(String t_user) {
		int i = 0;
		Connection con = (Connection) DBUtil.getConnection();
		try {
			Statement stmt = (Statement) con.createStatement();
			ResultSet rset = stmt.executeQuery("select count(*)as totalCount from " + t_user);
			if (rset.next()) {
				i = rset.getInt("totalCount");
			}
		} catch (Exception e) {
//			System.err.println("你确定你没有输错表明么？？？");
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
		}
//		System.out.println(i);
		return i;
	}

}
