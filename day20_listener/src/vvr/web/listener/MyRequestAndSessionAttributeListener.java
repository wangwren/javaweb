package vvr.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyRequestAndSessionAttributeListener implements
		HttpSessionAttributeListener, ServletRequestAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("session�мӶ����ˣ���");

	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("session��ɾ�����ˣ���");

	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("session���滻�����ˣ���");

	}

	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("request�мӶ����ˣ���");

	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("request��ɾ�����ˣ���");

	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("request���滻�����ˣ���");

	}

}
