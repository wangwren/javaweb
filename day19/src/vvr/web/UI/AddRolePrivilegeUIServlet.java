package vvr.web.UI;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vvr.domain.Privilege;
import vvr.domain.Role;
import vvr.service.SecurityService;

//为添加用户权限提供UI界面的Servlet
public class AddRolePrivilegeUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SecurityService service = new SecurityService();
		
		//得到角色信息
		String role_id = request.getParameter("role_id");
		Role role = service.findRole(role_id);
		request.setAttribute("role", role);
		
		//得到角色当前拥有的权限
		List<Privilege> rolePrivileges = service.getRolePrivileges(role_id);
		request.setAttribute("rolePrivileges", rolePrivileges);
		
		//得到系统的所有权限
		List<Privilege> privileges = service.getAllPrivilege();
		request.setAttribute("privileges", privileges);
		
		request.getRequestDispatcher("/jsp/addRolePrivilege.jsp").forward(request, response);
	}

}
