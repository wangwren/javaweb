package it.cast.utils;

import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class WebUtils {
	
	//��request�е����ݷ�װ��bean��
	public static <T> T request2Bean(HttpServletRequest request,Class<T> clazz){
		
		try{
			
			T t = clazz.newInstance();
			
			//ָ����bean�����������ͣ�ע��һ��ת����
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()){
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				
				//ʹ��beanutils���߷�װ��ָ��bean��
				BeanUtils.setProperty(t, name, value);
			}
			
			return t;
			
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}
	
	//ע��id
	public static String makeID(){
		return UUID.randomUUID().toString();
	}

}
