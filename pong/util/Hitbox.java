package pong.util;

import java.awt.Point;
import java.awt.Rectangle;

import pong.gui.PongItem;

public class Hitbox {
	
	private Rectangle hitbox;
	
	public Hitbox(PongItem item){
		hitbox = new Rectangle(item.getPosition().x,item.getPosition().y,item.getWidth(),item.getHeight());
	}
	
	public void translate(Point pos){
		hitbox.translate(pos.x, pos.y);
	}
	
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
	
	public Point getPos(){
		return hitbox.getLocation();
	}
	
	public void setPos(Point pos){
		hitbox.setLocation(pos);
	}
	
	public int getWidth(){
		return hitbox.width;
	}
	public int getHeight(){
		return hitbox.height;
	}
}
