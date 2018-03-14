<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
</head>
<body>
		静态属性值为：
		<s:property value="@cn.itcast.chapter05.ognl.TestOgnl02@staticValue"/>
		<br/>调用静态方法结果查看控制器
		<s:property value="@cn.itcast.chapter05.ognl.TestOgnl02@testMethod()"/>
</body>
</html>