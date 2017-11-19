package vvr.domain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class MyBean2 implements HttpSessionActivationListener,Serializable {

	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("session从硬盘回到内存了");
	}

	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("session被序列化到硬盘了");

	}

}
