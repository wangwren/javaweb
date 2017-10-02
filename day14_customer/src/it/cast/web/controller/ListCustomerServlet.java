package it.cast.web.controller;

import it.cast.domain.Page;
import it.cast.service.impl.BusinessService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			
			String pagenum = request.getParameter("pagenum");
			BusinessService service = new BusinessService();
			
//			List list = service.getAllCustomer();
//			request.setAttribute("list", list);
			
			String servletName = this.getServletName();
			
			Page page = service.getPageData(pagenum,request.getContextPath() + "/" + servletName);   //����servlet�����ƴ���page
			request.setAttribute("page", page);
			
			request.getRequestDispatcher("WEB-INF/jsp/listCustomer.jsp").forward(request, response);
			
			
		}catch(Exception e){
			request.setAttribute("message", "�Բ��𣬲�ѯʧ��");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

}
