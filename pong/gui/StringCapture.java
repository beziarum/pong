package pong.gui;

import javax.swing.JOptionPane;

public class StringCapture {
	
	public StringCapture(){}
	
	public static String getString(){
	    return JOptionPane.showInputDialog(null, "Saisie d'une adresse de connexion", null, JOptionPane.QUESTION_MESSAGE);
	}
}
