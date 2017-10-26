package vvr.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestBufferedReaderWrapper {

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new FileReader("src/vvr/demo/BufferedReaderWrapper.java"));
		BufferedReaderWrapper brw = new BufferedReaderWrapper(br);
		
		FileWriter out = new FileWriter("e:\\1.java");
		
		String line = null;
		
		while((line = brw.readLine()) != null){
			out.write(line + "\r\n");
		}
		
		out.close();
		brw.close();
		br.close();

	}

}
