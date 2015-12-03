package pong.gui;

import java.io.*;

public class StringCapture {

	private static String s = null;
	public static BufferedReader entre = new BufferedReader(new InputStreamReader(System.in));
	
	public StringCapture(){}
	
	public static String getString(){
		
		try {
			s = entre.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		while (s==null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		String test = new String(StringCapture.getString());
		System.out.println(test);
	}
}
