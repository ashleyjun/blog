package com.dlj.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String phoneNumber = request.getParameter("phonenumber");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		boolean isFlag = false;
		String errorMsg = " ";
		if (StringUtils.isEmpty(username)) {
			isFlag = true;
			errorMsg = "用户名不允许为空";
		} else if (StringUtils.isEmpty(phoneNumber)) {
			isFlag = true;
			errorMsg = "手机号不允许为空";
		} else if (StringUtils.isEmpty(password)) {
			isFlag = true;
			errorMsg = "密码不允许为空";
		} else if (!StringUtils.equals(password, confirmPassword)) {
			isFlag = true;
			errorMsg = "两次输入的密码不一致";
		}

		if (isFlag) {
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Connection connection = null;
		Statement statement = null;
		try {
			connection = (Connection) DBUtil.getConnection();
			statement = (Statement) connection.createStatement();
			// 准备sql语句
			// 注意： 字符串要用单引号'
			String sql = "insert into t_register(username, phone_number,password, create_time, update_time) values('"
					+ username + "', '" + phoneNumber + "','" + password + "', now(), now())";
			System.out.println(sql);
			// 在statement中使用字符串拼接的方式，这种方式存在诸多问题
			boolean success = statement.execute(sql);
			System.out.println("执行结果：" + success);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		request.setAttribute("username", username);
		RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
		dispatcher.forward(request, response);
	}

}
