<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传和下载</title>
</head>
<body>
	<s:form action="fileupload" method="post" enctype="multipart/form-data">
		<s:file name="file" label="upload"/>
		<s:submit value="上传"/>
		<s:reset value="重置"/>
	</s:form>
</body>
</html>