package vvr.web.listener;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//ģ�ⶨʱ���ʼ�
public class SendMailListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		//���һ��Calendar��������ָ��ʱ�䣬����ο��ö���API
		Calendar ca = Calendar.getInstance();
		//�÷���ָ��ʱ�䣺2017��11��18��14��56��0�룬������·��Ǵ�0��ʼ�����
		ca.set(2017, 10, 18, 14, 56, 0);
		
		//����һ����ʱ������
		Timer timer = new Timer();
		
		//schedule �÷���ָ����ĳһʱ����ʲô���飬��һ����������Ҫ�������飬�ڶ�����������ʱ��
		//Ҳ����ͨ���̳� TimerTask ʵ��run������ʵ����Ҫ��������
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				//���ڻ����ᷢ�ʼ��������Դ�ӡһ�仰����
				System.out.println("���ʼ�������");
				
			}
		}, ca.getTime());

	}

}
