package it.cast.web.UI;

import it.cast.utils.Globals;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCustomerUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("preferences", Globals.preferences);
		request.setAttribute("types", Globals.types);

		request.getRequestDispatcher("/WEB-INF/jsp/addcustomer1.jsp").forward(request, response);
	}

}
