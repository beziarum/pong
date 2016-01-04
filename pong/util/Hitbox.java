package pong.util;

import java.awt.Point;
import java.awt.Rectangle;

import pong.gui.PongItem;

/**
 * la classe hitbox genere et gére les hitbox des items du jeux
 * @author paul et antoine
 * 
 */
public class Hitbox {
	
	/**
	 * la hitbox est un rectangle
	 */
	private Rectangle hitbox;
	
	/**
	 * constructeur qui crée une hitbox par rapport a un item
	 * @param item item lié a la hitbox
	 */
	public Hitbox(PongItem item){
		hitbox = new Rectangle(item.getPosition().x,item.getPosition().y,item.getWidth(),item.getHeight());
	}
	
	/**
	 * bouge la hitbox en fonction d'une position
	 * @param pos
	 */
	public void translate(Point pos){
		hitbox.translate(pos.x, pos.y);
	}
	
	/**
	 * 	vérifie si des hitbox rentre en contact
	 * @param h
	 * @return direction de la collision
	 */
	public Direction colision(Hitbox h)
	{
		Rectangle inter=hitbox.intersection(h.hitbox);
		if(inter.isEmpty())
			return Direction.aucune;
		else if(inter.height>inter.width)
			if(inter.getCenterX()>hitbox.getCenterX())
				return Direction.gauche;
			else
				return Direction.droite;
		else
			if(inter.getCenterY()>hitbox.getCenterY())
				return Direction.haut;
			else
				return Direction.bas;
	}
	
	/**
	 * retourne position hitbox
	 * @return pos hitbox
	 */
	public Point getPos(){
		return hitbox.getLocation();
	}
	
	/**
	 * modifie la position de la hitbox
	 * @param pos
	 */
	public void setPos(Point pos){
		hitbox.setLocation(pos);
	}
	
	/**
	 * retourne largeur de la hitbox
	 * @return
	 */
	public int getWidth(){
		return hitbox.width;
	}
	
	/**
	 * retourne hauteur de la hitbox
	 * @return
	 */
	public int getHeight(){
		return hitbox.height;
	}
}
