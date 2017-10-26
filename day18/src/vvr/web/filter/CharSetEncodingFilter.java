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

//���������Ͻ��ȫվ����������
public class CharSetEncodingFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("����");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//���post�ύ��ʽ����������
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

//��ǿrequest��.getParameter(name)����������get��ʽ�ύ��������������ת��
//ʹ�ð�װ���ģʽ�����������ǿ
//HttpServletRequestWrapper��sun��˾д�õ�һ����װ�ֻ࣬��Ҫ�̳�����࣬��дҪ���ǵķ����Ϳ���
class MyRequestCharSet extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	public MyRequestCharSet(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	//��дҪ��ǿ�ķ���
	@Override
	public String getParameter(String name) {
		
		try{
			
			String value = this.request.getParameter(name);
			System.out.println(value);
			
			if(value == null){
				return null;
			}
			
			//�ж��������ύ��ʽ
			if(!this.request.getMethod().equalsIgnoreCase("get")){
				
				//equalsIgnoreCase()�����ǲ����ִ�Сд�ıȽ�
				return null;
			}
			
			//�����get��ʽ�ύ
			value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
			System.out.println(value);
			return value;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}

