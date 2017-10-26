package vvr.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/*
 * 使用包装设计模式对BufferedReader的readLine()方法进行增强,显示出行号
 * 
 * 步骤:
 * 1、实现被增强对象相同的接口。（这样能够实现包装对象的所有方法，进而进行重写）
 * 2、定义一个变量记住被增强的对象。
 * 3、定义一个构造器，接收被增强的对象。（接收被增强对象的目的是为了让被增强的对象直接调用其他方法，就不用再去重写）
 * 4、覆盖需要增强的方法。
 * 5、对于不想增强的方法，直接调用被增强对象的方法
 * 
 * 
 * */

//BBufferedReader继承了Reader，又实现了好几个接口，所以这里选择继承它，效果相同，即现在是子类又是包装类
public class BufferedReaderWrapper extends BufferedReader{

	private BufferedReader br;
	private int rowCount = 1;	//行号
	
	public BufferedReaderWrapper(BufferedReader in) {
		super(in);
		
		this.br = in;
	}

	
	//重写方法
	@Override
	public String readLine() throws IOException {
		
		String line = br.readLine();	//readLine()方法作用是读取文本的一行
		if(line == null){
			return line;
		}
		
		return rowCount++ + ": " + line;
	}
	
	
	

}
