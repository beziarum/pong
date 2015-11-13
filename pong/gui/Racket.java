package pong.gui;

import java.awt.*;
import pong.util.Direction;

public class Racket extends PongItem {
	
	protected static final int INIT_SPEED = 4;
	
	public Racket(){
		load("image/racket.png");
		speed = new Point (INIT_SPEED,INIT_SPEED);
	}
	
	public void animate(){}
	
	public void rebondir(Direction d){
		
	}
}
