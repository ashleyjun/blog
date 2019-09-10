package com.dlj.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dlj.utils.DBUtil;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class CheckPasswordServlet
 */
@WebServlet("/checkPassword")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPasswordServlet() {
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
		String password = request.getParameter("password");
		String sql = "select * from t_user where password = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);

			resultSet =  preparedStatement.executeQuery();
		
			int rowCount = resultSet.getRow();
			if (rowCount <= 0) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("success", true);
				resultMap.put("msg", "合法密码");
				
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
//			response.getWriter().append(JSON.toJSONString(resultMap));
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
