package pong.gui;

import java.awt.*;
import pong.util.Direction;

public class Racket extends PongItem {

	private static final long serialVersionUID = 1L;
	
	public static final int INIT_SPEED = 0;
	public static final int RACKET_SPEED = 4;
	protected static Point INIT_POSITION = new Point (4,100);
	
	public Racket(){
		load("image/racket.png");
		speed = new Point (INIT_SPEED,INIT_SPEED);
		position = INIT_POSITION;
	}
	
	
	public void rebondir(Direction d,int window_width,int window_height){
		if (d == Direction.haut)
			this.position.y=0;
		if (d == Direction.bas)
			this.position.y= window_height - this.height;
	}
	
	
}
