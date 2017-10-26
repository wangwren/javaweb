<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'test.jsp' starting page</title>

  </head>
  
  <body>
  	<form action="${pageContext.request.contextPath}/DirtyServlet" method="post">
    	内容：<textarea rows="8" cols="20" name="message"></textarea><br/>
    	<input type="submit" value="提交">
    </form>
  </body>
</html>
