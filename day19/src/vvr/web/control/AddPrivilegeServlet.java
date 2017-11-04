package vvr.web.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.WebUtils;
import vvr.domain.Privilege;
import vvr.service.SecurityService;

public class AddPrivilegeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			
			Privilege privilege = new Privilege();
			privilege.setName(name);
			privilege.setDescription(description);
			privilege.setId(WebUtils.makeUUID());
			
			SecurityService service = new SecurityService();
			service.add(privilege);
			
			request.setAttribute("message", "添加成功！！！");
			
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败！！！");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
