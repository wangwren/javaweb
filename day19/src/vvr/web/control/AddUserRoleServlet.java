package vvr.web.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vvr.service.SecurityService;

public class AddUserRoleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			SecurityService service = new SecurityService();
			
			String user_id = request.getParameter("user_id");
			String[] role_id = request.getParameterValues("role_id");
			
			service.updateUserRoles(user_id, role_id);
			request.setAttribute("message", "授权成功！！！");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "授权失败！！！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
