package pong.gui;

import java.awt.Point;

import pong.util.Direction;

public class Racket extends PongItem {

	private static final long serialVersionUID = 1L;
	
	protected static final int INIT_SPEED = 0;
	
	public static final int RACKET_SPEED = 4;
	
	protected static Point INIT_POSITION = new Point (4,100);
	
	public Racket(){
		super("image/racket.png", INIT_POSITION, new Point (INIT_SPEED,INIT_SPEED));
	}
	
	
	public void rebondir(Direction d,int window_width,int window_height){
		if (d == Direction.bas && this.position.y<=0)
			this.position.y=0;
		else if (d == Direction.haut && this.position.y + this.height >= window_height)
			this.position.y= window_height - this.height;
		hitbox.setPos(this.getPosition());
	}
	
	
}
