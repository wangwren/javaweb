package it.cast.el;

public class WebELUtils {
	
	
	/*
	 * �Զ���һ��EL����subString,�����������ַ�������ָ����lengthʱ��ʾʡ�Ժ�
	 * 
	 * EL������������
	 * 
	 * 1����дһ��java��ľ�̬����
	 * 
	 * 2����д��ǩ����������tld���ļ�����tld�ļ��������Զ��庯����
	 * 
	 * 3����JSPҳ���е����ʹ���Զ��庯��
	 * 
	 * */
	
	
	public static String subString(String source,Integer length){
		
		if(source.length() > length){
			
			return source.substring(0, length) + "...";
			
		}else{
			
			return source;
		}
		
	}

}
