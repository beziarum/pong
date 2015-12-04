package pong.gui;

import javax.swing.JOptionPane;

public class StringCapture {
	
	public StringCapture(){}
	
	public static String getString(){
	    return JOptionPane.showInputDialog(null, "Saisie d'adresse de connexion", null, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static void main(String[] args) {
		String test = new String(StringCapture.getString());
		System.out.println(test);
	}
}
