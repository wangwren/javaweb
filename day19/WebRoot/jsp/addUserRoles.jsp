<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addUserRoles.jsp' starting page</title>

  </head>
  
  <body style="text-align: center;">
    <table align="center" border="1" width="60%">
    	<tr>
    		<td>用户名称：</td>
    		<td>${user.username}</td>
    	</tr>
    	
    	<tr>
    		<td>当前角色</td>
    		<td>
    			<c:forEach var="ur" items="${userRoles}">
    				${ur.name}<br/>
    			</c:forEach>
    		</td>
    	</tr>
    	
    	<tr>
    		<td>系统角色</td>
    		<td>
    			<form action="${pageContext.request.contextPath }/AddUserRoleServlet" method="post">
    				<input type="hidden" name="user_id" value="${user.id }">
    				<c:forEach var="r" items="${roles}">
						<input type="checkbox" name="role_id" value="${r.id}">${r.name}<br/>
    				</c:forEach>
    				<input type="submit" value="授予角色">
    			</form>
    		</td>
    	</tr>
    </table>
  </body>
</html>
