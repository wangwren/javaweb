<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/itcast" prefix="itcast"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查询全部客户</title>
    
  </head>
  
  <body style="text-align: center;">
  <c:choose>
  	<c:when test="${!empty(page.list) }">
  	<table align="center" width="80%" frame="border">
  		<tr>
  			<td>客户姓名</td>
  			<td>性别</td>
  			<td>生日</td>
  			<td>电话</td>
  			<td>电子邮箱</td>
  			<td>爱好</td>
  			<td>客户类型</td>
  			<td>备注</td>
  			<td>操作</td>
  		</tr>
  		
  		<c:forEach var="c" items="${page.list }">
  			<tr>
  				<td>${c.name}</td>
  				<td>${c.gender }</td>
  				<td>${c.birthday }</td>
  				<td>${c.cellphone }</td>
  				<td>${c.email }</td>
  				<td>${c.preference }</td>
  				<td>${c.type }</td>
  				<!-- 完成功能：当字符串数据超过十个时显示省略号 -->
  				<!-- 由于sun公司没有非常好的el函数实现，实现起来太麻烦，所以自己写一个el函数来实现 -->
  				<td>${itcast:subString(c.description,10)}</td>
  				<td>
  					<a href="${pageContext.request.contextPath }/UpdateCustomerUIServlet?id=${c.id}">修改</a>
  					<a href="javascript:dodelete('${c.id }')">删除</a>
  				</td>
  			</tr>
  		</c:forEach>
  	</table>
  	
  	<%@include file="/public/page.jsp" %>
  	
  	</c:when>
  	
  	
  	<c:otherwise>
  		对不起，系统没有任何客户信息!!!
  	</c:otherwise>
    </c:choose>
  </body>
  <script type="text/javascript">
  	function dodelete(id){
  		
  		var b = window.confirm("您确定要删除该用户信息吗？");
  		if(b){
  			window.location.href = "${pageContext.request.contextPath }/DeleteCustomerServlet?id=" + id;
  		}
  		
  	}
  </script>
</html>
