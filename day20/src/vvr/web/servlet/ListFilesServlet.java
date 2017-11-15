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
		
		//����ʵ�Ŀ����У��ļ����ϴ����������������ݿ�ģ����ݿ��б����ϴ��ļ���ʱ�䡢������������Ϣ�����ǲ��ܱ����ļ�
		//������ʱ�Ϳ����������ݿ��е���Ϣ����ȡ�ļ��Ļ�����Ϣ�������ļ���Ȼ�Ǳ����ڷ�����Ӳ���ϣ���ʵ��������Ҫ���ݹ飬���ǲ�ѯ���ݿ�
	}
	
	//�������������е������ϴ��ļ�
	public void listFile(File file,Map map){
		
		//�����Ŀ¼�²����ļ������п������ļ���
		if(!file.isFile()){
			//������Ŀ¼�µ����ж���
			File[] files = file.listFiles();
			
			for(File f : files){
				//�ݹ�������������÷�����ֱ���ҳ����ļ����µ��ļ�
				listFile(f,map);
			}
		}else{
			//������ļ����ͽ��ļ���������map�У�֮��չʾ����jspҳ��
			//map�е�key��������ļ���UUID�㷨������value��������ļ�����ʵ����
			map.put(file.getName(), file.getName().substring(file.getName().indexOf("_") + 1));
		}
	}

}
