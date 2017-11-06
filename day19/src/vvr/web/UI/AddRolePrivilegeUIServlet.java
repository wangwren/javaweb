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

//Ϊ����û�Ȩ���ṩUI�����Servlet
public class AddRolePrivilegeUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SecurityService service = new SecurityService();
		
		//�õ���ɫ��Ϣ
		String role_id = request.getParameter("role_id");
		Role role = service.findRole(role_id);
		request.setAttribute("role", role);
		
		//�õ���ɫ��ǰӵ�е�Ȩ��
		List<Privilege> rolePrivileges = service.getRolePrivileges(role_id);
		request.setAttribute("rolePrivileges", rolePrivileges);
		
		//�õ�ϵͳ������Ȩ��
		List<Privilege> privileges = service.getAllPrivilege();
		request.setAttribute("privileges", privileges);
		
		request.getRequestDispatcher("/jsp/addRolePrivilege.jsp").forward(request, response);
	}

}
