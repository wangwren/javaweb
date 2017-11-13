<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'upload2.jsp' starting page</title>
    
    <script type="text/javascript">
    	function addinput(){
    		var div = document.getElementById("file");
    		
    		var input = document.createElement("input");
    		input.type="file";
    		input.name="filename";
    		
    		var del = document.createElement("input");
    		del.type="button";
    		del.value="删除";
    		del.onclick = function d(){
    			this.parentNode.parentNode.removeChild(this.parentNode);
    		}
    		
    		
    		var innerdiv = document.createElement("div");
    		
    		
    		innerdiv.appendChild(input);
    		innerdiv.appendChild(del);
    		
    		div.appendChild(innerdiv);
    	}
    </script>
    
  </head>
  
  <body>
    
    
    <form action="" enctype="mutlipart/form-data"></form>
    <table>
    	<tr>
    		<td>上传用户：</td>
    		<td><input type="text" name="username"></td>
    	</tr>
		<tr>    	
    		<td>上传文件：</td>
    		<td>
    			<input type="button" value="添加上传文件" onclick="addinput()">
     		</td>
     	</tr>
     	<tr>
     		<td></td>
    		<td>
    			<div id="file">
    			
    			</div>
     		</td>
     	</tr>
     	
    </table>
    
    
  </body>
</html>
