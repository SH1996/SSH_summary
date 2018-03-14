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
	输出字符串：
	<s:property value="'www.itcast.cn'"/><br/>
	忽略HTML代码：
	<s:property value="'<h3>www.itcast.cn</h3>'" escape="true"/><br/>
	不忽略HTML代码：
	<s:property value="'<h3>www.itcast.cn</h3>'" escape="false"/><br/>
	输出默认值：
	<s:property value="" default="true"/>
	<s:a href="http://www.baidu.com">BaiDu</s:a>
	<s:include value="iteratorTags.jsp"></s:include>
	
</body>
</html>