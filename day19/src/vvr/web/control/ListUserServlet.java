package vvr.web.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vvr.domain.Role;
import vvr.domain.User;
import vvr.service.SecurityService;

public class ListUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			
			SecurityService service = new SecurityService();
			List<User> users = service.getAllUser();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/jsp/listUser.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "≤È—Ø ß∞‹£°£°£°");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

}
