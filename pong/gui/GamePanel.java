package pong.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * GamePanel est un JPanel spécialisé pour afficher la zone de
 * Jeu proprement dite, avec les différents PongItems
 * @author paul et antoine
 *
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Définit la taille de la zone de jeu
	 */
	public static final Dimension WINDOW_SIZE=new Dimension(800,600);
	
	/**
	 * Définit la couleur du fond de la zone de Jeu
	 */
	private Color backgroundColor=new Color(0xFF, 0x40, 0);
	
	/**
	 * Tableau contenant l'ensemble des items existants, afin de pouvoir
	 * simplement les afficher
	 */
	private ArrayList<PongItem> a;
	
	/**
	 * Constructeur de la classe
	 * @param a référence vers la liste des items existants
	 */
	GamePanel(ArrayList<PongItem> a)
	{
		this.a=a;
		setMinimumSize(WINDOW_SIZE);
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, WINDOW_SIZE.width, WINDOW_SIZE.height);
		for(PongItem e : a)
			e.paint(g);
	}

	/**
	 * effectue une symétrie centrale autours du centre de la zone de jeu
	 * @param p Le point sur lequel effectuer la symétrie
	 * @return Le symétrique de p par rapport au centre de la zone de jeu
	 */
	public static Point rotate(Point p)
	{
		return new Point(Window.WINDOW_SIZE.width-p.x,GamePanel.WINDOW_SIZE.height-p.y);
	}
}
