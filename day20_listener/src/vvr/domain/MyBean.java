package vvr.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MyBean implements HttpSessionBindingListener {

	private String name;
	
	
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("�Լ����ӵ�session����");

	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("�Լ���session�߳�����");

	}

}
