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
		
		//�õ��û���Ϣ
		String user_id = request.getParameter("user_id");
		User user = service.findUser(user_id);
		request.setAttribute("user", user);
		
		//�õ��û���ǰӵ�еĽ�ɫ
		List<Role> userRoles = service.getUserRoles(user_id);
		request.setAttribute("userRoles", userRoles);
		
		//�õ�ϵͳ�����н�ɫ
		List<Role> roles = service.getAllRoles();
		request.setAttribute("roles", roles);
		
		request.getRequestDispatcher("/jsp/addUserRoles.jsp").forward(request, response);
	}

}
