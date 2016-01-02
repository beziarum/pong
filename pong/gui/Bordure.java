
package pong.gui;

import java.awt.Graphics;
import java.awt.Point;

import pong.util.Direction;

/**
 * @author paul et antoine
 * 
 * classe de l'item bordure qui délimite le jeux
 */
public class Bordure extends PongItem {


	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param d direction a retourner en cas de collision
	 * @param window_width largeur fenetre
	 * @param window_height hauteur fenetre
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
	 * fonction hérité de ponItem mais qui ne sera pas appelé
	 */
	public void rebondir(Direction d, int window_width, int window_height) {}
		
	/**
	 * fonction hérité de ponItem mais qui ne sera pas appelé
	 */
	public void paint(Graphics GContext){};
}
