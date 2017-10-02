<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


当前第[${page.pagenum}]页&nbsp;&nbsp;&nbsp;
  	<c:if test="${page.pagenum>1 }">
  	<!-- 为了使分页能够在不同的页面使用，在page中再定义一个属性，用来记录跳转的servlet -->
  	<a href="${pageContext.request.contextPath }/ListCustomerServlet?pagenum=${page.pagenum-1}">上一页</a>
  	&nbsp;&nbsp;&nbsp;
  	</c:if>
  	<c:forEach var="pagenum" begin="${page.startpage }" end="${page.endpage}">
  		
  		<!-- 一下显示十页 -->
  		[<a href="${page.url }?pagenum=${pagenum}">${pagenum }</a>]
  		
  	</c:forEach>
  	<c:if test="${page.pagenum<page.totalPage }">
  	&nbsp;&nbsp;&nbsp;
  	<a href="${page.url }?pagenum=${page.pagenum+1}">下一页</a>
  	</c:if>
  	
  	&nbsp;&nbsp;&nbsp;
  	共${page.totalPage }页
  	
  	<input type="text" id="pagenum" style="width: 30px"/>
  	<input type="button" value=" GO " onclick="goWhich()"/>
  	<script type="text/javascript">
  		function goWhich(){
  			var pagenum = document.getElementById("pagenum").value;
  			
  			if(pagenum == null || pagenum == ""){
  				alert("请输入页码!!!");
  				return;
  			}
  			if(!pagenum.match("\\d+")){
  				alert("请输入数字!!!");
  				document.getElementById("pagenum").value = "";
  				return;
  			}
  			if(pagenum<1 || pagenum>${page.totalPage}){
  				alert("无效页码!!!");
  				document.getElementById("pagenum").value = "";
  				return;
  			}
  			
  			window.location.href = "${page.url }?pagenum=" + pagenum;
  		}
  	</script>