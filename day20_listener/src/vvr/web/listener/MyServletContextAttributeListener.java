package vvr.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements
		ServletContextAttributeListener {

	public void attributeAdded(ServletContextAttributeEvent scab) {
		
		System.out.println(scab.getValue());
		
		System.out.println("加东西了！！");

	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		System.out.println("删东西了！！");

	}

	public void attributeReplaced(ServletContextAttributeEvent scab) {
		System.out.println("替换东西了！！");

	}

}
