package vvr.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("context´Ý»Ù");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("context´´½¨");

	}

}
