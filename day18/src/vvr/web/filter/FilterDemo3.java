package vvr.web.filter;


//���ȫվ��������
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo3 implements Filter {
	
	private FilterConfig filterConfig = null;
	private String defaultCharset = "utf-8";
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String charset = filterConfig.getInitParameter("charset");		//��ȡfilter�ĳ�ʼ������
		if(charset == null){
			charset = defaultCharset;		//���charsetΪ�գ���ô�Ͱ�ȱʡ�ĸ���
		}
		
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);
		
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		this.filterConfig = filterConfig;
	}

}
