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
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
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
			
			/*
			//设置服务器的缓存大小，默认为10KB
			factory.setSizeThreshold(10);
			*/
			
			//设置一个临时目录，当上传的文件超过服务器缓存的最大值时，将文件暂时保存到临时目录中
			factory.setRepository(new File(this.getServletContext().getRealPath("/WEB-INF/temp")));
			
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			/*
			//限制上传文件的大小，该方法中接收一个 Long 类型值，代表字节，1024 为 1KB
			upload.setFileSizeMax(1024);	//如果超出大小会抛出FileUploadBase.FileSizeLimitExceededException异常
			
			//上传文件的总容量，即上传多个文件时，几个文件的大小加起来不能超过多少
			upload.setSizeMax(1024*10);
			*/
			
			//监听文件上传的速度
			upload.setProgressListener(new ProgressListener() {
				
				@Override
				public void update(long pBytesRead, long pContentLength,  int arg2) {
					//pBytesRead代表当前处理		pContentLength代表文件大小			arg2代表哪个文件
					System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
					
				}
			});
			
			
			
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
					
					//判断fileName是否为空
					if(fileName == null || fileName.trim().equals("")){
						continue;	//如果为空就跳出当前循环执行下一次循环
					}
					
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
					//保存至临时文件时需要删除，该语句必须在关流之后写
					item.delete();
				}
			}
			
		}catch (FileUploadBase.FileSizeLimitExceededException e) {
			// 如果文件超出指定大小
			e.printStackTrace();
			request.setAttribute("message", "对不起，您上传的文件超过指定大小！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}
		catch (Exception e) {
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
