<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form action="/blog/updateUser" method="post">
<table>

	<tr>
		<td>邮箱</td>
		<td>
		<input name="email" value="${user.email}" type="text"/>
		<input name="id" value="${user.id}" type="hidden"/>
		</td>
	</tr>
	<tr>
		<td>电话号码</td>
		<td><input name="phoneNumber" value="${user.phoneNumber}"  type="text"/></td>
	</tr>
	<tr>
		<td>用户名</td>
		<td><input name="username" value="${user.username}" readonly  type="text"/></td>
	</tr>
	<tr>
		<td>昵称</td>
		<td><input name="nickname" value="${user.nickname}"  type="text"/></td>
	</tr>
	<tr>
		<td>密码</td>
		<td><input name="password" value="${user.password}"  type="password"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit"/>
		</td>
		
	</tr>
</table>
</form>
</body>
</html>