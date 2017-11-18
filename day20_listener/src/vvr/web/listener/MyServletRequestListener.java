package vvr.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		System.out.println(sre.getServletRequest() + "销毁了！！！");

	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		
		System.out.println(sre.getServletRequest() + "创建了!!!");

	}

}
