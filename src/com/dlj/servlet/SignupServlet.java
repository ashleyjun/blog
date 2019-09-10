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

import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signup.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		boolean error = false;
		String errorMsg = "";
		if (StringUtils.isEmpty(email)) {
			error = true;
			errorMsg = "邮箱不允许为空";
		} else if (StringUtils.isEmpty(password)) {
			error = true;
			errorMsg = "密码不允许为空";
		} else if (!StringUtils.equals(password, confirmPassword)) {
			error = true;
			errorMsg = "两次输入的密码不一致";
		}

//		if (password == null) {
//			error = true;
//			errorMsg = "密码不允许为空";
//		} else {
//			if (!password.equals(confirmPassword)) {
//				error = true;
//				errorMsg = "两次输入的密码不一致";
//			}
//		}

		if (error) {
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
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
			String sql = "insert into t_user(email, phone_number, username, nickname, password, create_time, update_time) values('"
					+ email + "', '" + phoneNumber + "', '" + username + "', '" + nickname + "', '" + password
					+ "', now(), now())";
			System.out.println(sql);
			// 在statement中使用字符串拼接的方式，这种方式存在诸多问题
			boolean success = statement.execute(sql);
			System.out.println("执行结果：" + success);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			errorMsg = e.getMessage();
		} finally {
			DBUtil.close(connection, statement, null);
		}

		request.setAttribute("errorMsg", errorMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
		dispatcher.forward(request, response);
	}

}
