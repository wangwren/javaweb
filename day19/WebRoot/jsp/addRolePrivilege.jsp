<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addRolePrivilege.jsp' starting page</title>

  </head>
  
  <body style="text-align: center;">
    <table align="center" border="1" width="60%">
    	<tr>
    		<td>角色名称：</td>
    		<td>${role.name}</td>
    	</tr>
    	
    	<tr>
    		<td>当前权限</td>
    		<td>
    			<c:forEach var="rp" items="${rolePrivileges }">
    				${rp.name}<br/>
    			</c:forEach>
    		</td>
    	</tr>
    	
    	<tr>
    		<td>系统权限</td>
    		<td>
    			<form action="${pageContext.request.contextPath }/addRolePrivilegeServlet" method="post">
    				<input type="hidden" name="role_id" value="${role.id }">
    				<c:forEach var="p" items="${privileges}">
						<input type="checkbox" name="privilege_id" value="${p.id}">${p.name}<br/>
    				</c:forEach>
    				<input type="submit" value="授权">
    			</form>
    		</td>
    	</tr>
    </table>
  </body>
</html>
