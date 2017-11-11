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

	//���ڴ����Ӧ���ܶ��ڵ�Ȩ�ޣ�key���uri��ֵ
	private Map<String,Privilege> map = new HashMap<>();
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		//��ʼ��ʱ����Ҫ��Ȩ�޼��뵽��Ӧ�Ĺ����ϣ�������Ӧ�ô���һ����Դ����ı�
		map.put("/day19/manager/AddProduct",new Privilege("�����Ʒ"));
		map.put("/day19/manager/UpdateProduct", new Privilege("�޸���Ʒ"));
		map.put("/day19/manager/DeleteProduct", new Privilege("ɾ����Ʒ"));
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//1���õ��û������URI
		String URI = request.getRequestURI();
		
		//2���õ�������Դ��Ҫ��Ȩ��
		Privilege p = map.get(URI);
		
		//3���жϵõ���Ȩ���Ƿ�Ϊ�գ����Ϊ������ҪȨ�ޣ�ֱ�ӷ���
		if(p == null){
			chain.doFilter(request, response);
			return;
		}
		
		//4�������Ϊ�գ�����ҪȨ�ޣ��ȼ���û��Ƿ��¼�����û�е�¼�������û���¼
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			//request.setAttribute("message", "���ȵ�¼");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//5������û��Ѿ���¼����ô�͵õ��û���ǰӵ�е�����Ȩ��
		SecurityService service = new SecurityService();
		Set set = service.getUserAllPrivilege(user.getId());
		
		//6.�ж��û�ӵ�е�Ȩ���У��Ƿ��з�����Դ��ҪȨ��
		if(!set.contains(p)){	//list[p1,p2]  p1.equals(p)  object false����Ҫ��Privilege����дequals��ֻҪname��ͬ�Ϳ���
			request.setAttribute("message", "�Բ�����û��Ȩ��");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		//7.��Ȩ�ޣ�����
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}



}
