<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加客戶</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/customer1.js"></script>
  </head>
  
  <body style="text-align: center;" onload="init()">
  <h1>添加客户</h1>
  <form action="${pageContext.request.contextPath }/AddCustomerServlet" method="post" onsubmit="return dosubmit()" id="customer">
    <table width="50%" border="1" align="center">
    
    	<tr>
    		<td>客戶姓名</td>
    		<td><input type="text" name="name"></td>
    	</tr>
    	
    	<tr>
    		<td>性別</td>
    		<td>
    			<input type="radio" name="gender" value="男" checked="checked">男
    			<input type="radio" name="gender" value="女">女
    		</td>
    	</tr>
    	
    	<tr>
    		<td>出生日期</td>
    		<td>
    			<!-- 设置name用于提交表单，生日这个表单在JavaScript中设置提交，此处不需要提交 -->
    			<select id="year">
    				<option value="1900">1900</option>
    			</select>年
    			
    			<select id="month">
    				<option value="01">01</option>
    			</select>月
    			
    			<select id="day">
    				<option value="01">01</option>
    			</select>日
    		</td>
    	</tr>
    	
    	<tr>
    		<td>手机</td>
    		<td><input type="text" name="cellphone"></td>
    	</tr>
    	
    	<tr>
    		<td>邮箱</td>
    		<td><input type="text" name="email"></td>
    	</tr>
    	
    	<tr>
    		<!-- 在utils包中的Globals设置了爱好 -->
    		<td>爱好</td>
    		<td>
    			<c:forEach var="pre" items="${preferences }">
    				<input type="checkbox" name="pre" value="${pre}">${pre}
    			</c:forEach>
    		</td>
    	</tr>
    	
    	<tr>
    		<td>客户类型</td>
    		<td>
    			<c:forEach var="type" items="${types }">
    				<input type="radio" name="type" value="${type}">${type}
    			</c:forEach>
    		</td>
    	</tr>
    	
    	<tr>
    		<td>客户备注</td>
    		<td><textarea rows="5" cols="50" name="description"></textarea></td>
    	</tr>
    	
    	<tr>
    		<td><input type="reset" value="清空"></td>
    		<td><input type="submit" value="提交"></td>
    	</tr>
    </table>
   </form>
  </body>
</html>
