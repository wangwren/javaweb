package vvr.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyRequestAndSessionAttributeListener implements
		HttpSessionAttributeListener, ServletRequestAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("session中加东西了！！");

	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("session中删东西了！！");

	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("session中替换东西了！！");

	}

	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("request中加东西了！！");

	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("request中删东西了！！");

	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("request中替换东西了！！");

	}

}
