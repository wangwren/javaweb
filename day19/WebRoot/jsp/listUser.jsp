<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'listUser.jsp' starting page</title>

  </head>
  
  <body style="text-align: center;">
  	<table frame="below" width="60%" align="center">
  		<td align="right">
  			<a href="${pageContext.request.contextPath }/jsp/addUser.jsp">添加用户</a>
  		</td>
  	</table>
    <table frame="below" width="60%" align="center">
    	<tr>
    		<td align="center">用户名称</td>
    		<td align="center">用户密码</td>
    		<td colspan="3" align="center">操作</td>
    	</tr>
    	<c:forEach var="u" items="${ users}">
    		<tr>
    			<td align="center">${u.username}</td>
    			<td align="center">${u.password}</td>
    			<td align="center"><a href="${pageContext.request.contextPath }/AddUserRoleUIServlet?user_id=${u.id}">授予角色</a></td>
    			<td align="center"><a href="#">修改</a></td>
    			<td align="center"><a href="#">删除</a></td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
