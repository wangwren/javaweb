package it.cast.web.controller;

import it.cast.domain.Customer;
import it.cast.service.impl.BusinessService;
import it.cast.utils.WebUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		try{
			
			Customer c = WebUtils.request2Bean(request, Customer.class);
			c.setId(WebUtils.makeID());
			
			BusinessService service = new BusinessService();
			service.addCustomer(c);
			
			request.setAttribute("message", "添加成功");
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "添加失败");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
