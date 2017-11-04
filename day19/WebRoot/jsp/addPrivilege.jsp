<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addPrivilege.jsp' starting page</title>

  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/AddPrivilegeServlet" method="post">
    	<table align="center" width="60%">
    		<tr>
    			<td align="center">权限名称:</td>
    			<td align="center"><input type="text" name="name"></td>
    		</tr>
    		<tr>
    			<td align="center">权限描述：</td>
    			<td align="center">
    				<textarea rows="8" cols="30" name="description"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<td align="right"><input type="submit" value="提交"></td>
    			<td align="left"><input type="reset" value="重置"></td>
    		</tr>

    	</table>
    </form>
  </body>
</html>
