package com.dlj.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class CheckUsernameServlet
 */
@WebServlet("/checkUsername")
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String sql = "select * from t_user where username = ?";
		Connection connection = null;
//		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) DBUtil.getConnection();
//			statement = (Statement) connection.createStatement();
//			String sql = "select * from t_user where username = '"+username+"' and password = '"+password+"'";
			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement = connection.prepareStatement();
			preparedStatement.setString(1, username);
//			preparedStatement.setString(2, password);
			// 准备sql语句
			// 注意： 字符串要用单引号'

//			System.out.println(sql);
			// 在statement中使用字符串拼接的方式，这种方式存在诸多问题
			resultSet =  preparedStatement.executeQuery();
			System.out.println("sql=" + preparedStatement.toString());
			resultSet.last();
			int rowCount = resultSet.getRow();
			if (rowCount <= 0) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("success", true);
				resultMap.put("msg", "用户名可用");
				System.out.println(JSON.toJSONString(resultMap));
				response.getWriter().append(JSON.toJSONString(resultMap));
				return;
			}

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success", false);
			resultMap.put("msg", "用户名已被占用");
			System.out.println(JSON.toJSONString(resultMap));
			response.getWriter().append(JSON.toJSONString(resultMap));
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success", false);
			resultMap.put("msg", "系统异常！" + e.getMessage());
			System.out.println(JSON.toJSONString(resultMap));
			response.getWriter().append(JSON.toJSONString(resultMap));
			return;
		} finally {
			DBUtil.close(connection, preparedStatement, resultSet);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
