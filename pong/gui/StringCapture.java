package pong.gui;

import javax.swing.JOptionPane;

/**
 * 
 * classe qui permet de rentrer une chaine de caractere dans une boite de dialogue en jeux pour la connexion
 * @author paul et antoine
 *
 */
public class StringCapture {
	
	public StringCapture(){}
	/**
	 * affiche une boite de dialogue et retourne la chaine de caractere saisie
	 * @return chaine de caractere
	 */
	public static String getString(){
	    return JOptionPane.showInputDialog(null, "Saisie d'une adresse de connexion", null, JOptionPane.QUESTION_MESSAGE);
	}
}
