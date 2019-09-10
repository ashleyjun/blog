package com.dlj.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean error = false;
		String errorMsg = " ";
		if (StringUtils.isEmpty(username)) {
			error = true;
			errorMsg = "用户名不允许为空";
		} else if (StringUtils.isEmpty(password)) {
			error = true;
			errorMsg = "密码不允许为空";
		}
		if (error) {
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		Connection connection = null;
//		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) DBUtil.getConnection();
//			statement = (Statement) connection.createStatement();
//			String sql = "select * from t_user where username = '"+username+"' and password = '"+password+"'";
			preparedStatement = connection.prepareStatement("select * from t_user where username = ? and password = ?");
//			preparedStatement = connection.prepareStatement();
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			// 准备sql语句
			// 注意： 字符串要用单引号'

//			System.out.println(sql);
			// 在statement中使用字符串拼接的方式，这种方式存在诸多问题
			resultSet =  preparedStatement.executeQuery();
			System.out.println("sql=" + preparedStatement.toString());//打印SQL
			resultSet.last();
			int rowCount = resultSet.getRow();
			if (rowCount <= 0) {
				request.setAttribute("errorMsg", "用户名或密码错误");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			request.getSession().setAttribute("username", username);
			request.setAttribute("msg", "登陆成功");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/success.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(connection, preparedStatement, resultSet);
		}
		
		request.setAttribute("errorMsg", "系统异常");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
		dispatcher.forward(request, response);
	}

}
