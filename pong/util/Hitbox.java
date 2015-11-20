package pong.util;

import java.awt.Point;

import pong.gui.PongItem;

public class Hitbox {
	
	public Point angleSupGauche;
	public Point angleInfDroite;
	public int width;
	public int height;
	
	public Hitbox(PongItem item){
		angleSupGauche = item.getPosition();
		width = item.getWidth();
		height = item.getHeight();
		angleInfDroite = new Point(angleSupGauche.x + width , angleSupGauche.y + height);
	}
	
	public void translate(Point pos){
		angleSupGauche = pos;
		angleInfDroite = new Point(angleSupGauche.x + width , angleSupGauche.y + height);
	}
	
	
}
