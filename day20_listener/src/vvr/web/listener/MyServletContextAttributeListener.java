package vvr.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements
		ServletContextAttributeListener {

	public void attributeAdded(ServletContextAttributeEvent scab) {
		
		System.out.println(scab.getValue());
		
		System.out.println("�Ӷ����ˣ���");

	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		System.out.println("ɾ�����ˣ���");

	}

	public void attributeReplaced(ServletContextAttributeEvent scab) {
		System.out.println("�滻�����ˣ���");

	}

}
