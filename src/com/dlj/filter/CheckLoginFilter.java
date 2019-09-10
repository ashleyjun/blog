package com.dlj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
//@WebFilter(value = "/*", filterName = "aa")
public class CheckLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CheckLoginFilter() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("CheckLoginFilter.destroy......");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String uri = httpServletRequest.getRequestURI();
		System.out.println("CheckLoginFilter...." + uri);

		if (StringUtils.equals(uri, "/blog/signin.jsp")) {
			chain.doFilter(request, response);
			return;
		}

		if (StringUtils.equals(uri, "/blog/SigninServlet")) {
			chain.doFilter(request, response);
			return;
		}

		if (StringUtils.contains(uri, "/static/")) {
			chain.doFilter(request, response);
			return;
		}
		if (StringUtils.equals(uri, "/blog/signup.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		if (StringUtils.equals(uri, "/blog/SignupServlet")) {
			chain.doFilter(request, response);
			return;
		}

		String username = (String) httpServletRequest.getSession().getAttribute("username");
		if (StringUtils.isEmpty(username)) {
			httpServletResponse.sendRedirect("/blog/signin.jsp");
			return;
		}
		// pass the request along the filter chain
		System.out.println("用户已登录，用户名：" + username + ",URI=" + uri);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("CheckLoginFilter.init......");
	}

}
