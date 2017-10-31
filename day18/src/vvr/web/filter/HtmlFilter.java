package vvr.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class HtmlFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		MyHtmlRequest htmlRequest = new MyHtmlRequest(request);
		
		arg2.doFilter(htmlRequest, response);
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}


class MyHtmlRequest extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	
	public MyHtmlRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		
		String value = this.request.getParameter(name);
		if(value==null){
			return null;
		}
		return filter(value);		//调用filter方法，该方法是由tomact写好的
	}
	
	 public String filter(String message) {

	        if (message == null)
	            return (null);

	        char content[] = new char[message.length()];
	        message.getChars(0, message.length(), content, 0);
	        StringBuffer result = new StringBuffer(content.length + 50);
	        for (int i = 0; i < content.length; i++) {
	            switch (content[i]) {
	            case '<':
	                result.append("&lt;");
	                break;
	            case '>':
	                result.append("&gt;");
	                break;
	            case '&':
	                result.append("&amp;");
	                break;
	            case '"':
	                result.append("&quot;");
	                break;
	            default:
	                result.append(content[i]);
	            }
	        }
	        return (result.toString());

	    }
	
}
