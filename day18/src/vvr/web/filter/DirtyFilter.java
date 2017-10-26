package vvr.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class DirtyFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		DirtyRequest dirtyRequest = new DirtyRequest(request);
		//记得放行
		chain.doFilter(dirtyRequest, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}

class DirtyRequest extends HttpServletRequestWrapper{

	private List<String> list = new ArrayList<String>();
	private HttpServletRequest request;
	public DirtyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	@Override
	public String getParameter(String name) {
		
		list.add("傻逼");
		list.add("淘宝");
		
		String value = this.request.getParameter(name);
		if(value == null){
			return null;
		}
		
		for(String str : list){
			if(value.contains(str)){
				value = value.replace(str, "**");
			}
		}
		return value;
	}
	
	
	
}
