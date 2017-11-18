package vvr.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计当前在线用户个数
public class OnLineCountListener implements HttpSessionListener {
	
	public void sessionCreated(HttpSessionEvent se) {
		
		ServletContext context = se.getSession().getServletContext();
		Integer num = (Integer) context.getAttribute("num");
		if(num==null){
			context.setAttribute("num", 1);
		}else{
			num++;
			context.setAttribute("num", num);
		}
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
	
		ServletContext context = se.getSession().getServletContext();
		Integer num = (Integer) context.getAttribute("num");
		if(num==null){
			context.setAttribute("num", 1);
		}else{
			num--;
			context.setAttribute("num", num);
		}
	}

}
