package vvr.web.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vvr.domain.Role;
import vvr.service.SecurityService;

public class ListRoleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			
			SecurityService service = new SecurityService();
			List<Role> roles = service.getAllRoles();
			request.setAttribute("roles", roles);
			request.getRequestDispatcher("/jsp/listRole.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "≤È—Ø ß∞‹£°£°£°");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

}
