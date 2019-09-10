<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册</title>
<script type="text/javascript" src="static/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function() {
		console.log(document.getElementById("username").value);
		console.log($("#username").val());

	});

	function checkUsername() {
		var username = $("#username").val();
		console.log(username.length);
		if (username.length == 0) {
			//alert("用户名不允许为空");
			$("#msg").text("用户名不允许为空");
			return;
		}
		if (username.length < 6 || username.length > 12) {
			//alert("用户名长度必须大于6且小于12");
			$("#msg").text("用户名长度必须大于6且小于12");
			return;
		}
		console.log("1");
		//http://localhost:8080/blog/checkUsername?username=ffffff
		$.ajax({
			  url: "/blog/checkUsername",
			  data: {
				  username: username
			  },
			  dataType: "json",
			  type: "GET",
			  //async: false,
			  success: function( data ) {
					console.log("2");
			    	//console.log(data);
			    	//console.log(data.msg);
			    	//console.log(data.success);
			    	if (data.success){
			    		console.log(data.msg);
			    		$("#msg").text(data.msg);
			    	} else {
			    		//alert(data.msg);
			    		$("#msg").text(data.msg);
			    	}
			  },
			  error:function(data){
				  console.log(data);
			  }
		});
		da
		console.log("3");

	}
</script>
<script type="text/javascript">
	$(function() {
		//console.log("Hello world!!!");
		console.log(document.getElementById("password").value);
		console.log($("#password").val());

	});

	function checkPassword() {
		var password = $("#password").val();
		console.log(password.length);
		if (password.length == 0) {
			alert("密码不允许为空");
			//$("#msg").text("密码不允许为空");
			return;
		}
		if (password.length!=6) {
			alert("请输入六位数密码！");
			//$("#msg").text("请输入六位数密码！");
			return;
		}
		
		//http://localhost:8080/blog/checkUsername?username=ffffff
		$.ajax({
			  url: "/blog/checkPassword",
			  data: {
				  password: password
			  },
			  dataType: "json",
			  type: "GET",
			  //async: false,
			  success: function( data ) {
					
			    	//console.log(data);
			    	//console.log(data.msg);
			    	//console.log(data.success);
			    	if (data.success){
			    		console.log(data.msg);
			    		alert(data.msg);
			    		//$("#msg").text(data.msg);
			    	} else {
			    		alert(data.msg);
			    		//$("#msg").text(data.msg);
			    	}
			  },
			  error:function(data){
				  alert(data);
			  }
		});
		
		

	}
	
	function click(){
		console.log("click....");
	}
</script>
</head>
<body>
	<form action="/blog/SignupServlet" method=post>
		邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input name="email"
			type="text" placeholder="填写邮箱" /><br />
		<p></p>
		手机号码：<input name="phoneNumber" type="text" /><br />
		<p></p>
		登录名称：<input name="username" onblur="checkUsername()" id="username"
			type="text" /><label id="msg" style="color: red;" onclick="click()"></label><br />
		<p></p>
		显示昵称：<input name="nickname" type="text" /><br />
		<p></p>
		密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input
			name="password"onblur="checkPassword()" id="password" type="password" /><br />
		<p></p>
		确认密码：<input name="confirmPassword" type="password" /><br />
		<p>
		<input type="submit" />
		</p>
		
	</form>
</body>
</html>