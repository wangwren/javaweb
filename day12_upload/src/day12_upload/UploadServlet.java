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
			
			//1���õ�����������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			//2���õ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//3���ж��ϴ���������
			if(!upload.isMultipartContent(request)){
				//����ϴ��ı����Ͳ��� multipart/data-form  ��������ȡ��
				return;
			}
			
			//4�����ý������ϴ�����
			List<FileItem> list = upload.parseRequest(request);  //�õ��ϴ����ݵ� fileitem 
			
			//5������list ���õ�ÿһ��fileitem ����ÿһ����
			for(FileItem item : list ){
				
				if(item.isFormField()){
					//����õ�������ͨ������
					String name = item.getFieldName();  //�õ������������
					String value = item.getString();   //�õ��������ֵ
					System.out.println(name + " = " + value);
				}else{
					//�õ��ϴ�������
					String fileName = item.getName();  //�õ��ϴ��ļ���
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);   //�õ��������ϴ��ļ���
					
					InputStream in = item.getInputStream();  //�õ��ϴ�����
					
					String realPath = this.getServletContext().getRealPath("/WEB-INF/upload");
					FileOutputStream out = new FileOutputStream(realPath + "\\" + fileName);   //��uploadĿ¼�����ϴ�������
					
					//�ֽ������ģ��
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
