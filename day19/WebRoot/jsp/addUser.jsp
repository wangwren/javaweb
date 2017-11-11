<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addUser.jsp' starting page</title>

  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/AddUserServlet" method="post">
    	<table align="center" width="60%">
    		<tr>
    			<td align="center">用户名称:</td>
    			<td align="center"><input type="text" name="username"></td>
    		</tr>
    		<tr>
    			<td align="center">用户密码：</td>
    			<td align="center">
    				<input type="text" name="password">
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
