<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    

  </head>
  
  <body>
    当前在线人数${num}
    <%
    	application.setAttribute("name", "xxx");
    	application.setAttribute("name", "yyy");
    	application.removeAttribute("name");
    	
    	
    	session.setAttribute("name", "xxx");
    	session.setAttribute("name", "yyy");
    	session.removeAttribute("name");
    	request.setAttribute("name", "yyyy");
     %>
  </body>
</html>
