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

		//�õ��ϴ��ļ��ı���·��
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		
		try{
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//����ϴ��ļ��������ı���,����request����ʹ
			upload.setHeaderEncoding("UTF-8");
			
			if(!upload.isMultipartContent(request)){
				//��������ǣ�������ͨ��ͳ��ʽ��ȡ��
				return;
			}
			
			List<FileItem> list = upload.parseRequest(request);
			
			for(FileItem item : list){
				if(item.isFormField()){
					//���FileItem��װ������ͨ������
					String name = item.getFieldName();
					//����ϴ����ݵ���������
					String value = item.getString("UTF-8");
					//ͬ�����Խ���ϴ����ݵ���������value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				}else{
					//���FileItem��װ�����ļ��ϴ�
					
					//��ȡ�ļ�����
					String fileName = item.getName();	////��ͬ��������ύ���ļ��ǲ�һ��  c:\a\b\1.txt   1.txt
					
					//��fileName���ַ�����ȡ����ȡ�ļ���
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					
					//��ȡ�ļ�����
					InputStream in = item.getInputStream();
					//�õ��ļ���������
					String saveFileName = this.makeFileName(fileName);
					
					//�õ��ļ��ı���·��
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
	
	//���ļ���������Է��ϴ��ظ��ļ�
	public String makeFileName(String filename){  //2.jpg
		return UUID.randomUUID().toString() + "_" + filename; //ʹ��UUID�������ַ���֮�����filename����֤�ļ����ĸ�ʽ��ȷ
	}
	
	//�õ��ļ��ı���·��,ʹ�ϴ����ļ������·��,����㷨����Ҫ
	public String makePath(String filename,String savePath){
		
		int hashcode = filename.hashCode();	//�õ��ϴ��ļ���·����ַ
		int dir1 = hashcode&0xf;  //0--15	//ʹ��·���Ķ�������ʮ�����Ƶ� f ���������㣬�õ��ļ��ĺ���λ������Χ��0-15
		int dir2 = (hashcode&0xf0)>>4;  //0-15	////ʹ��·���Ķ�������ʮ�����Ƶ� f ���������㣬����������λ���õ��ļ��ĺ���λ������Χ��0-15
		
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		File file = new File(dir);
		
		//����ļ��в����ڣ��Ǿʹ�������ļ���
		if(!file.exists()){
			file.mkdirs();	//ע�����mkdirs()��������Ҫ����mkdir()��������Ϊ��Ҫ��������Ŀ¼
		}
		return dir;
	}

}
