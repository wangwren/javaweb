package day12_upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			
			//1、得到解析器工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			//2、得到解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//3、判断上传表单的类型
			if(!upload.isMultipartContent(request)){
				//如果上传的表单类型不是 multipart/data-form  则正常获取表单
				return;
			}
			
			//4、调用解析器上传数据
			List<FileItem> list = upload.parseRequest(request);  //得到上传数据的 fileitem 
			
			//5、遍历list ，得到每一项fileitem ，即每一个表单
			for(FileItem item : list ){
				
				if(item.isFormField()){
					//如果得到的是普通输入项
					String name = item.getFieldName();  //得到输入项的名称
					String value = item.getString();   //得到输入项的值
					System.out.println(name + " = " + value);
				}else{
					//得到上传输入项
					String fileName = item.getName();  //得到上传文件名
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);   //得到真正的上传文件名
					
					InputStream in = item.getInputStream();  //得到上传数据
					
					String realPath = this.getServletContext().getRealPath("/WEB-INF/upload");
					FileOutputStream out = new FileOutputStream(realPath + "\\" + fileName);   //向upload目录保存上传的数据
					
					//字节流输出模板
					int len = 0;
					byte[] buffer = new byte[1024];
					while((len = in.read(buffer)) > 0){
						out.write(buffer, 0, len);
					}
					
					in.close();
					out.close();
				}
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
			
		   
	}

}
