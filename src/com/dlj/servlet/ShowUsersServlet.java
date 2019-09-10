package com.dlj.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MailcapCommandMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.dlj.entity.User;
import com.dlj.utils.Count;
import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class showUsersServlet
 */
@WebServlet("/showUsers")
public class ShowUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowUsersServlet() {
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

//		request.setAttribute("username", username);
//		request.getSession().setAttribute("key", "Hello world");
		int pageSize = 5;
		String pString = request.getParameter("pageNum");
		int pageNum = 1;
		if (!StringUtils.isBlank(pString)) {
			pageNum = Integer.valueOf(pString);
		}
		request.setAttribute("pageNum", pageNum);
		
		int offset = pageSize * (pageNum - 1);

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResultSet totalCountResultSet = null;
		try {
			connection = (Connection) DBUtil.getConnection();
			statement = (Statement) connection.createStatement();
			// 准备sql语句
			// 注意： 字符串要用单引号'
			String sql = "SELECT * FROM t_user order by id desc limit " + offset + "," + pageSize;
			System.out.println(sql);
			// 在statement中使用字符串拼接的方式，这种方式存在诸多问题
			resultSet = statement.executeQuery(sql);
			List<User> userList = new ArrayList<User>();
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
				userList.add(user);
			}
			request.setAttribute("userList", userList);

			int totalCount = 0;
			totalCountResultSet = statement.executeQuery("select count(*) as totalCount from t_user ");
			if (totalCountResultSet.next()) {
				totalCount = totalCountResultSet.getInt("totalCount");
			}
			request.setAttribute("totalCount", totalCount);

			Map<String, String> userMap = new HashMap<String, String>();
			for (int i = 0; i < userList.size(); i++) {
				User user = userList.get(i);
				userMap.put(user.getUsername(), user.getEmail());
			}

			System.out.println(userMap.get("aa"));

			request.setAttribute("userMap", userMap);
//			request.setAttribute("userMap", null);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/userList.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			DBUtil.close(totalCountResultSet);
			DBUtil.close(connection, statement, resultSet);

		}
		request.setAttribute("errorMsg", "系统异常");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
		dispatcher.forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
