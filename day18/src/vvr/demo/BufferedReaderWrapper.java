package vvr.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/*
 * ʹ�ð�װ���ģʽ��BufferedReader��readLine()����������ǿ,��ʾ���к�
 * 
 * ����:
 * 1��ʵ�ֱ���ǿ������ͬ�Ľӿڡ��������ܹ�ʵ�ְ�װ��������з���������������д��
 * 2������һ��������ס����ǿ�Ķ���
 * 3������һ�������������ձ���ǿ�Ķ��󡣣����ձ���ǿ�����Ŀ����Ϊ���ñ���ǿ�Ķ���ֱ�ӵ��������������Ͳ�����ȥ��д��
 * 4��������Ҫ��ǿ�ķ�����
 * 5�����ڲ�����ǿ�ķ�����ֱ�ӵ��ñ���ǿ����ķ���
 * 
 * 
 * */

//BBufferedReader�̳���Reader����ʵ���˺ü����ӿڣ���������ѡ��̳�����Ч����ͬ�����������������ǰ�װ��
public class BufferedReaderWrapper extends BufferedReader{

	private BufferedReader br;
	private int rowCount = 1;	//�к�
	
	public BufferedReaderWrapper(BufferedReader in) {
		super(in);
		
		this.br = in;
	}

	
	//��д����
	@Override
	public String readLine() throws IOException {
		
		String line = br.readLine();	//readLine()���������Ƕ�ȡ�ı���һ��
		if(line == null){
			return line;
		}
		
		return rowCount++ + ": " + line;
	}
	
	
	

}
