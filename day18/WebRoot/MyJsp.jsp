<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'MyJsp.jsp' starting page</title>

  </head>
  
  <body>
    <c:url value="/CharsetEncodingServlet" scope="request" var="CharsetEncodingServlet">
    	<c:param name="username" value="中国"/>
    </c:url>
    
    <a href="${CharsetEncodingServlet}">点点</a>
    <a href="/day18/CharsetEncodingServlet?password=哈哈哈">哈哈</a>
  </body>
</html>
