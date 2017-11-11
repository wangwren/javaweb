package vvr.web.UI;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vvr.domain.Privilege;
import vvr.domain.Role;
import vvr.domain.User;
import vvr.service.SecurityService;

public class AddUserRoleUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SecurityService service = new SecurityService();
		
		//得到用户信息
		String user_id = request.getParameter("user_id");
		User user = service.findUser(user_id);
		request.setAttribute("user", user);
		
		//得到用户当前拥有的角色
		List<Role> userRoles = service.getUserRoles(user_id);
		request.setAttribute("userRoles", userRoles);
		
		//得到系统的所有角色
		List<Role> roles = service.getAllRoles();
		request.setAttribute("roles", roles);
		
		request.getRequestDispatcher("/jsp/addUserRoles.jsp").forward(request, response);
	}

}
