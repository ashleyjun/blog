<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册</title>
</head>
<body>
<form  action="/blog/SignupServlet" method=post>
<!-- 
 -->
邮箱：<input name="email" type="text" placeholder="填写邮箱"/><br/>
手机号码：<input name="phoneNumber" type="text"/><br/>
登录名称：<input name="username" type= "text"/><br/>
显示昵称：<input name="nickname" type= "text"/><br/>
密码：<input name="password" type= "password"/><br/>
确认密码：<input name="confirmPassword" type="password"/><br/>
<input type="submit"/>
</form>
</body>
</html>