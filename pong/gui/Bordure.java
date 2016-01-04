
package pong.gui;

import java.awt.Graphics;
import java.awt.Point;

import pong.util.Direction;

/**
 * 
 * classe de l'item bordure qui délimite le jeux
 * @author paul et antoine
 */
public class Bordure extends PongItem {


	private static final long serialVersionUID = 1L;

	/**
	 * Créer une bordure en fonction d'une position
	 * 
	 * @param d bord de l'écran où sera placé la bordure
	 * @param window_width largeur fenêtre
	 * @param window_height hauteur fenêtre
	 */
	public Bordure(Direction d,int window_width, int window_height)
	{
		if(d==Direction.haut)
		{
			height=1;
			width=window_width;
			init(new Point(0,0),new Point(0,0));
		}
		else if(d==Direction.gauche)
		{
			height=window_height;
			width=1;
			init(new Point(0,0),new Point(0,0));
		}
		else if(d==Direction.bas)
		{
			height=1;
			width=window_width;
			init(new Point(0,window_height-1), new Point(0,0));
		}
		else if(d==Direction.droite)
		{
			height=window_height;
			width=1;
			init(new Point(window_width-1,0),new Point(0,0));
		}
	}
	
	/**
	 * fonction héritée de pongItem mais qui ne sera pas appelée
	 */
	public void rebondir(Direction d, int window_width, int window_height) {}
		
	/**
	 * fonction héritée de pongItem mais qui ne sera pas appelée
	 */
	public void paint(Graphics GContext){};
}
