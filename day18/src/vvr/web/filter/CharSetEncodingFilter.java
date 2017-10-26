package vvr.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

//真正意义上解决全站的乱码问题
public class CharSetEncodingFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("哈哈");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//解决post提交方式的乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		MyRequestCharSet requestCharSet = new MyRequestCharSet(request);
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	
}

//增强request的.getParameter(name)方法，将以get方式提交过来的中文数据转码
//使用包装设计模式，对其进行增强
//HttpServletRequestWrapper是sun公司写好的一个包装类，只需要继承这个类，重写要覆盖的方法就可以
class MyRequestCharSet extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	public MyRequestCharSet(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	//重写要增强的方法
	@Override
	public String getParameter(String name) {
		
		try{
			
			String value = this.request.getParameter(name);
			System.out.println(value);
			
			if(value == null){
				return null;
			}
			
			//判断是哪种提交方式
			if(!this.request.getMethod().equalsIgnoreCase("get")){
				
				//equalsIgnoreCase()方法是不区分大小写的比较
				return null;
			}
			
			//如果是get方式提交
			value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
			System.out.println(value);
			return value;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}

