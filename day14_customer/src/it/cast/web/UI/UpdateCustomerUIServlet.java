package it.cast.web.UI;

import it.cast.domain.Customer;
import it.cast.service.impl.BusinessService;
import it.cast.utils.Globals;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCustomerUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("preferences", Globals.preferences);
		request.setAttribute("types", Globals.types);

		String id = request.getParameter("id");
		BusinessService service = new BusinessService();
		Customer customer = service.findCustomer(id);
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("/WEB-INF/jsp/updatecustomer.jsp").forward(request, response);
	}

}
