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
	
}
