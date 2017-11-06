<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'listRole.jsp' starting page</title>

  </head>
  
  <body style="text-align: center;">
  	<table frame="below" width="60%" align="center">
  		<td align="right">
  			<a href="${pageContext.request.contextPath }/jsp/addRole.jsp">添加角色</a>
  		</td>
  	</table>
    <table frame="below" width="60%" align="center">
    	<tr>
    		<td align="center">角色名称</td>
    		<td align="center">描述</td>
    		<td colspan="3" align="center">操作</td>
    	</tr>
    	<c:forEach var="r" items="${ roles}">
    		<tr>
    			<td align="center">${r.name}</td>
    			<td align="center">${r.description}</td>
    			<td align="center"><a href="${pageContext.request.contextPath }/AddRolePrivilegeUIServlet?role_id=${r.id}">授权</a></td>
    			<td align="center"><a href="#">修改</a></td>
    			<td align="center"><a href="#">删除</a></td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
