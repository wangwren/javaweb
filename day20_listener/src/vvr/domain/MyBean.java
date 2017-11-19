package vvr.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MyBean implements HttpSessionBindingListener {

	private String name;
	
	
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("自己被加到session中了");

	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("自己被session踢出来了");

	}

}
