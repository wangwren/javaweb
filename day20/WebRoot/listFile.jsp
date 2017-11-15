<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'listFile.jsp' starting page</title>

  </head>
  
  <body>
    <h1>文件下载</h1>
    <c:forEach var="me" items="${map}">
    	
    	<!-- 如果下载的文件名中包含中文，通过链接是get提交，需要转码，所以使用url标签 -->
    
    	<c:url value="/DownLoadServlet" var="downurl">
    		<c:param name="fileName">${me.key}</c:param>
    	</c:url>
    	
    	${me.value} <a href="${downurl}">下载</a><br/>
    	
    </c:forEach>
  </body>
</html>
