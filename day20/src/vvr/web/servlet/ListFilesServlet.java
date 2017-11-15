package vvr.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListFilesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String filePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		Map map = new HashMap();
		listFile(new File(filePath), map);
		
		request.setAttribute("map", map);
		request.getRequestDispatcher("listFile.jsp").forward(request, response);
		
		//在真实的开发中，文件的上传和下载是依赖数据库的，数据库中保存上传文件的时间、描述及其他信息，但是不能保存文件
		//在下载时就可以依靠数据库中的信息来获取文件的基本信息，但是文件依然是保存在服务器硬盘上，真实开发不需要做递归，就是查询数据库
	}
	
	//遍历出服务器中的所有上传文件
	public void listFile(File file,Map map){
		
		//如果该目录下不是文件，即有可能是文件夹
		if(!file.isFile()){
			//遍历该目录下的所有东西
			File[] files = file.listFiles();
			
			for(File f : files){
				//递归操作，继续做该方法，直到找出该文件夹下的文件
				listFile(f,map);
			}
		}else{
			//如果是文件，就将文件名保存在map中，之后展示会在jsp页面
			//map中的key保存的是文件的UUID算法的名，value保存的是文件的真实名称
			map.put(file.getName(), file.getName().substring(file.getName().indexOf("_") + 1));
		}
	}

}
