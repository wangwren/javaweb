package vvr.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vvr.domain.Privilege;
import vvr.domain.User;
import vvr.service.SecurityService;

public class CheckPrivilegeFilter implements Filter {

	//用于存放相应功能对于的权限，key存放uri的值
	private Map<String,Privilege> map = new HashMap<>();
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		//初始化时将需要的权限加入到对应的功能上，理论上应该创建一个资源管理的表
		map.put("/day19/manager/AddProduct",new Privilege("添加商品"));
		map.put("/day19/manager/UpdateProduct", new Privilege("修改商品"));
		map.put("/day19/manager/DeleteProduct", new Privilege("删除商品"));
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//1、得到用户请求的URI
		String URI = request.getRequestURI();
		
		//2、得到访问资源需要的权限
		Privilege p = map.get(URI);
		
		//3、判断得到的权限是否为空，如果为空则不需要权限，直接放行
		if(p == null){
			chain.doFilter(request, response);
			return;
		}
		
		//4、如果不为空，就需要权限，先检查用户是否登录，如果没有登录，先让用户登录
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			//request.setAttribute("message", "请先登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//5、如果用户已经登录，那么就得到用户当前拥有的所有权限
		SecurityService service = new SecurityService();
		Set set = service.getUserAllPrivilege(user.getId());
		
		//6.判断用户拥有的权限中，是否含有访问资源需要权限
		if(!set.contains(p)){	//list[p1,p2]  p1.equals(p)  object false，需要在Privilege中重写equals，只要name相同就可以
			request.setAttribute("message", "对不起，您没有权限");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		//7.有权限，放行
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}



}
