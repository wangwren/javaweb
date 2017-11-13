package vvr.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//得到上传文件的保存路径
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		
		try{
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//解决上传文件名的中文编码,设置request不好使
			upload.setHeaderEncoding("UTF-8");
			
			if(!upload.isMultipartContent(request)){
				//如果表单不是，按照普通传统方式获取表单
				return;
			}
			
			List<FileItem> list = upload.parseRequest(request);
			
			for(FileItem item : list){
				if(item.isFormField()){
					//如果FileItem封装的是普通输入项
					String name = item.getFieldName();
					//解决上传数据的中文乱码
					String value = item.getString("UTF-8");
					//同样可以解决上传数据的中文乱码value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				}else{
					//如果FileItem封装的是文件上传
					
					//获取文件名称
					String fileName = item.getName();	////不同的浏览器提交的文件是不一样  c:\a\b\1.txt   1.txt
					
					//对fileName做字符串截取，获取文件名
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					
					//获取文件内容
					InputStream in = item.getInputStream();
					//得到文件保存名称
					String saveFileName = this.makeFileName(fileName);
					
					//得到文件的保存路径
					String realSavePath = this.makePath(fileName, savePath);
					
					FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFileName);
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len = in.read(buffer))> 0){
						out.write(buffer, 0, len);
					}
					out.close();
					in.close();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//给文件起别名，以防上传重复文件
	public String makeFileName(String filename){  //2.jpg
		return UUID.randomUUID().toString() + "_" + filename; //使用UUID算出随机字符串之后加上filename，保证文件最后的格式正确
	}
	
	//得到文件的保存路径,使上传的文件在随机路径,这个算法很重要
	public String makePath(String filename,String savePath){
		
		int hashcode = filename.hashCode();	//得到上传文件的路径地址
		int dir1 = hashcode&0xf;  //0--15	//使用路径的二进制与十六进制的 f 进行与运算，得到文件的后四位数，范围在0-15
		int dir2 = (hashcode&0xf0)>>4;  //0-15	////使用路径的二进制与十六进制的 f 进行与运算，再向右移四位，得到文件的后四位数，范围在0-15
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		File file = new File(dir);
		
		//如果文件夹不存在，那就创建这个文件夹
		if(!file.exists()){
			file.mkdirs();	//注意调用mkdirs()方法，不要调用mkdir()方法，因为需要创建两级目录
		}
		return dir;
	}

}
