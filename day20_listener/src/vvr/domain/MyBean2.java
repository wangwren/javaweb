package vvr.domain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class MyBean2 implements HttpSessionActivationListener,Serializable {

	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("session��Ӳ�̻ص��ڴ���");
	}

	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("session�����л���Ӳ����");

	}

}
