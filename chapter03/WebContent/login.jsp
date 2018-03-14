<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
</head>
<body>
	<center>
	${requestScope.msg }<br/>
		<form action="login.action" method="post">
			<table>
				<tr>
					<td><label style="text-algin:right;">用户名</label></td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td><label style="text-algin:right;">密码</label></td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="登陆"></td>
				</tr>
			</table>	
		</form>
	</center>
</body>
</html>