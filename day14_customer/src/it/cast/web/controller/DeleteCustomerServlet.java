package it.cast.web.controller;

import it.cast.service.impl.BusinessService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			
			String id = request.getParameter("id");
			BusinessService service = new BusinessService();
			service.deleteCustomer(id);
			request.setAttribute("message", "É¾³ý³É¹¦£¡£¡£¡");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "É¾³ýÊ§°Ü£¡£¡£¡");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

}
