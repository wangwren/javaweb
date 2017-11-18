package vvr.web.listener;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//模拟定时发邮件
public class SendMailListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		//获得一个Calendar对象，用来指定时间，具体参看该对象API
		Calendar ca = Calendar.getInstance();
		//该方法指定时间：2017年11月18号14点56分0秒，老外的月份是从0开始计算的
		ca.set(2017, 10, 18, 14, 56, 0);
		
		//定义一个定时器对象
		Timer timer = new Timer();
		
		//schedule 该方法指定在某一时刻做什么事情，第一个参数代表要做的事情，第二个参数代表时间
		//也可以通过继承 TimerTask 实现run方法来实现想要做的事情
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				//由于还不会发邮件，所以以打印一句话代替
				System.out.println("发邮件！！！");
				
			}
		}, ca.getTime());

	}

}
