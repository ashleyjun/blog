<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#section {
    width:400px;
    height:280px;
    text-align:center;
	background-color:#C0D9D9;
	 
}
#section1 {
    width:450px;
    height:350px;
    text-align:center;
	background-color:#C0D9D9;
	float:left; 
	border-color:#b3baf7 ;
    border-width: 2px;
    border-style: solid;  	 
}

#footer {
    text-align:center;
	padding:60px;
    position:fixed;
    bottom:0;
    folat:left; 	 
}
</style>
<meta charset="UTF-8">
<title>注册页面</title>
</head>

<body style="background:url(static/images/7.jpg);background-size:100% 100%;background-attachment:fixed" > 

<div id="header">
<img src="static/images/logo_school.png"style="position:absolute;left:200px;top:20px">
<img src="static/images/logo_jw.png"style="position:absolute;left:450px;top:20px">
</div>

<div id="section"style="position:absolute;left:200px;top:120px" >
<img src="static/images/3.jpg" style="width:400px;height:320px;border-color: #b3baf7;border-width: 2px;border-style:solid;"/>
</div>

<div id="section1" style="position:absolute;left:600px;top:100px">   
<div style="position: relative; width: 300px; height: 89px;border-"> 
    <img src="static/images/2.jpg" style="width:450px;height:120px">
    <font style="position: absolute; bottom: 0; left:180px;top:40px;"size="5" >用户注册</font>    
</div> 
<p></p>

<div style="width:450px;
    height:50px;
    text-align:center;
	float:left; ">


</div>
<form action = "/blog/RegisterServlet" method = post>
用&nbsp;&nbsp;户&nbsp;&nbsp;名：
<input name="username" type="text"placeholder="请设置用户名" /><br>
<p></p>
手&nbsp;&nbsp;机&nbsp;&nbsp;号：
<input name="phonenumber" type="text" placeholder="可用于登陆和找回密码"/><br>
<p></p>
密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
<input name="password" type="password"placeholder="请设置登陆密码"/><br>
<p></p>
确认密码：
 <input name="confirmPassword" type="password"placeholder="请再次确认密码"/><br>
<!-- <p><input type="checkbox" required="required">我已阅读注册手册
</p>-->
<p></p>
<input type="submit"  /><br><!-- id= "button" value="注册" -->
<!--  <input type="submit" id= "button" value="取消" >-->
</form>

<div id="footer" style="left:400px"><font size="2" color="blue">©1999-2017</font><a href="http://www.zfsoft.com/index.html">
<img  border="0" src="static/images/logo.png"style="width:70px;height:15px"><font size="2" color="blue">正方软件股份有限公司 </font></a></div>



</div>

</body>
</html>