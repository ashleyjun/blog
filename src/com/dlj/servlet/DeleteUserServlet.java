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
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long id = Long.valueOf(request.getParameter("id"));
		// 准备sql语句
		String sql = "delete from t_user where id = " + id; 
		String errorMsg = "";
		Connection connection = null;
		Statement statement=null;
		try {
			 connection = (Connection) DBUtil.getConnection();
			 statement = (Statement) connection.createStatement();
			
			System.out.println(sql);
			// 在statement中使用字符串拼接的方式，这种方式存在诸多问题
			boolean success = statement.execute(sql);
			System.out.println("执行结果：" + success);
			
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/showUsers");
//			dispatcher.forward(request, response);
			
			response.sendRedirect("/blog/showUsers");
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			errorMsg = e.getMessage();
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

		request.setAttribute("errorMsg", errorMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
