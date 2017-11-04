<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'listPrivilege.jsp' starting page</title>

  </head>
  
  <body style="text-align: center;">
  	<table frame="below" width="60%" align="center">
  		<td align="right">
  			<a href="${pageContext.request.contextPath }/jsp/addPrivilege.jsp">添加权限</a>
  		</td>
  	</table>
    <table frame="below" width="60%" align="center">
    	<tr>
    		<td align="center">权限名称</td>
    		<td align="center">描述</td>
    		<td colspan="2" align="center">操作</td>
    	</tr>
    	<c:forEach var="p" items="${ privileges}">
    		<tr>
    			<td align="center">${p.name}</td>
    			<td align="center">${p.description}</td>
    			<td align="center"><a href="#">修改</a></td>
    			<td align="center"><a href="#">删除</a></td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
