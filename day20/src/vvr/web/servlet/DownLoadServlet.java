package vvr.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

public class DownLoadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		String filename = request.getParameter("fileName");  //23239283-92489-阿凡达.avi
		filename = new String(filename.getBytes("iso8859-1"),"UTF-8");
		String path = makePath(filename,this.getServletContext().getRealPath("/WEB-INF/upload"));
		
		File file = new File(path + "\\" + filename);
		if(!file.exists()){
			request.setAttribute("message", "您要下载的资源已被删除！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		String realname = filename.substring(filename.indexOf("_")+1);
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		
		FileInputStream in = new FileInputStream(path + "\\" + filename);
		OutputStream out = response.getOutputStream();
		byte buffer[] = new byte[1024];
		int len = 0;
		while((len=in.read(buffer))>0){
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}
	
	
	public String makePath(String filename,String savePath){
		
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return dir;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
