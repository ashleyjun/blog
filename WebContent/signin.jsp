<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登陆</title>
</head>
<body>
	<form action="/blog/SigninServlet" method="post">
		登录名称：<input name="username" type="text" /><br /><p></p>
		 密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input name="password" type="password" /><br />
	   <p><input type="submit" /></p>
	</form>
</body>
</html>