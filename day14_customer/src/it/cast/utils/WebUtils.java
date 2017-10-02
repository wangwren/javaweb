package it.cast.utils;

import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class WebUtils {
	
	//把request中的数据封装到bean中
	public static <T> T request2Bean(HttpServletRequest request,Class<T> clazz){
		
		try{
			
			T t = clazz.newInstance();
			
			//指定的bean中有日期类型，注册一个转换器
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()){
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				
				//使用beanutils工具封装到指定bean中
				BeanUtils.setProperty(t, name, value);
			}
			
			return t;
			
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}
	
	//注册id
	public static String makeID(){
		return UUID.randomUUID().toString();
	}

}
