package it.cast.web.controller;

import it.cast.domain.Customer;
import it.cast.service.impl.BusinessService;
import it.cast.utils.WebUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try{
			
			request.setCharacterEncoding("utf-8");
			//获取表单
			Customer customre = WebUtils.request2Bean(request, Customer.class);
			
			BusinessService service = new BusinessService();
			service.updateCustomer(customre);
			
			request.setAttribute("message", "修改成功！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "修改失败！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

}
