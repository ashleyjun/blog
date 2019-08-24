<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有用户信息</title>
</head>
<body>
<!--  Welcome, ${sessionScope.key}<br/>
${userMap['xxxx']}
<br/>-->

<table>
	<tr>
		<th>id</th>
		<th>邮箱</th>
		<th>电话号码</th>
		<th>用户名</th>
		<th>显示昵称</th>
		<th>密码</th>
		<th>创建时间</th>
		<th>更新时间</th>
		<th>操作</th>
	</tr>
	<!-- -jstl表达式 -->
<c:forEach items="${userList}" var="u" varStatus="status">
  <tr >
    <td>${u.id}
    </td>
    <td>${u.email}
    </td>
    <td>${u.phoneNumber}
    </td>
    <td>${u.username}
    </td>
    <td>${u.nickname}
    </td>
    <td>${u.password}
    </td>
     
    <td> <fmt:formatDate value="${u.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
    </td>
    <td><fmt:formatDate value="${u.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
    </td>
    <td>
    	<a href="/blog/deleteUser?id=${u.id}">删除用户</a>
    	<a href="/blog/showUpdateUser?id=${u.id}">更新用户</a>
    </td>
  </tr>
</c:forEach>
</table>
</body>
</html>