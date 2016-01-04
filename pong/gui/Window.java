package pong.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Classe supervisant l'affichage
 * @author paul et antoine
 *
 */
public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * définit la hauteur de la zone dédiée à l'affichage des fps
	 */
	private static final int SCORE_HEIGHT = 50;
	
	/**
	 * définit la taille totale de la fenêtre
	 * @see Window#getSize()
	 */
	public static final Dimension WINDOW_SIZE=new Dimension(GamePanel.WINDOW_SIZE.width, GamePanel.WINDOW_SIZE.height + SCORE_HEIGHT);
	
	/**
	 * temps d'attente entre deux frames
	 * @see Window#sleep()
	 */
	private static final int timestep = 10;
	
	/**
	 * pan est un Panel personalisé pour afficher la zone de jeu
	 */
	private GamePanel pan;
	
	/**
	 * scorePanel est un Label destiné à afficher le score
	 */
	private JLabel scorePanel;
	
	
	/**
	 * Constructeur de la classe Window
	 * @param a liste des items existants
	 */
	public Window(ArrayList<PongItem> a){
		setMinimumSize(WINDOW_SIZE);
		setTitle("Pong");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pan=new GamePanel(a);
		scorePanel=new JLabel("Score = 0/0",SwingConstants.CENTER);
		
		getContentPane().add(pan);
		getContentPane().add(scorePanel,BorderLayout.PAGE_END);
		
		pack();
		setVisible(true);
	}

	/**
	 * @return La taille de la fenetre
	 */
	public Dimension getSize(){
		return (Dimension)WINDOW_SIZE.clone();
	}
	
	/**
	 * Cette fonction commande le réaffichage des composantes de Window
	 */
	public void paint(){
		repaint();
	}
	
	/**
	 * execute une pause du programme de timstep millisecondes
	 */
	public void sleep(){
		try {
			Thread.sleep(timestep);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Cette fonction met à jour l'affichage du score, pour deux joueurs
	 * @param s1 score du joueur un
	 * @param s2 score du joueur deux
	 */
	public void updateScore(int s1, int s2){
		StringBuffer text=new StringBuffer();
		text.append("Score = ");
		text.append(s1);
		text.append('/');
		text.append(s2);
		scorePanel.setText(text.toString());
	}
	
	/**
	 * Cette fonction met à jour l'affichage du score, pour un seul joueur
	 * @param s nombre d'echecs
	 */
	public void updateScore(int s){
		scorePanel.setText("Echecs : "+s);
	}
}
