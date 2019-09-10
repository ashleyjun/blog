package com.dlj.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class updateUser
 */
@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUser() {
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
		long id = Long.valueOf(request.getParameter("id"));
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");

		// 准备sql语句
		// 注意： 字符串要用单引号'
		String sql = "update t_user set email = '" + email + "', phone_number = '" + phoneNumber + "', username = '"
				+ username + "', nickname = '" + nickname + "'," + " password = '" + password
				+ "', update_time = now() where id = " + id;

		String errorMsg = "";
		Connection connection = null;
		Statement statement = null;
		try {
			connection = (Connection) DBUtil.getConnection();
			statement = (Statement) connection.createStatement();

			System.out.println(sql);
			// 在statement中使用字符串拼接的方式，这种方式存在诸多问题
			boolean success = statement.execute(sql);
			System.out.println("执行结果：" + success);

			response.sendRedirect("/blog/showUsers");
			return;
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

//		doGet(request, response);
	}

}
