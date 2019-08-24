package com.dlj.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlj.entity.User;
import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class ShowUpdateUserServlet
 */
@WebServlet("/showUpdateUser")
public class ShowUpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long id = Long.valueOf(request.getParameter("id"));
		String sql = "select * from t_user where id = " + id;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = (Connection) DBUtil.getConnection();
			statement = (Statement) connection.createStatement();
		
		
			System.out.println(sql);
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPhoneNumber(resultSet.getString("phone_number"));
				user.setUsername(resultSet.getString("username"));
				user.setNickname(resultSet.getString("nickname"));
				user.setPassword(resultSet.getString("password"));
				user.setCreateTime(resultSet.getTimestamp("create_time"));
				user.setUpdateTime(resultSet.getTimestamp("update_time"));
				request.setAttribute("user", user);
			}
			
		
//			request.setAttribute("userMap", null);

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("showUpdateUser.jsp");
			dispatcher.forward(request, response);
			return;
		
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
		request.setAttribute("errorMsg", "系统异常");
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
