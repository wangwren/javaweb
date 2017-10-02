package it.cast.el;

public class WebELUtils {
	
	
	/*
	 * 自定义一个EL函数subString,当传过来的字符串超过指定的length时显示省略号
	 * 
	 * EL函数开发步骤
	 * 
	 * 1、编写一个java类的静态方法
	 * 
	 * 2、编写标签库描述符（tld）文件，在tld文件中描述自定义函数。
	 * 
	 * 3、在JSP页面中导入和使用自定义函数
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
