package vvr.web.control;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vvr.domain.Privilege;
import vvr.service.SecurityService;

public class ListPrivilegeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			
			SecurityService service = new SecurityService();
			List<Privilege> privileges = service.getAll();
			request.setAttribute("privileges", privileges);
			request.getRequestDispatcher("/jsp/listPrivilege.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "≤È—Ø ß∞‹£°£°£°");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

}
