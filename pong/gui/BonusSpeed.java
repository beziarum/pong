package pong.gui;

import java.awt.Point;

import pong.util.Direction;
import pong.util.RandomNumber;

public class BonusSpeed extends PongItem{
	
	private static final long serialVersionUID = 1L;

	protected static final int INIT_SPEED= 0;
	
	protected static Point INIT_POSITION = RandomNumber.randomPoint((Window.WINDOW_SIZE.width/2)-100,(Window.WINDOW_SIZE.width/2)+100,(Window.WINDOW_SIZE.height/2)-100,(Window.WINDOW_SIZE.height/2)+100);//a vérifier
	
	
	public BonusSpeed()
    {	
    	super("image/boostSpeed.png", INIT_POSITION,new Point(INIT_SPEED ,INIT_SPEED));
    }


	public void rebondir(Direction d, int window_width, int window_height) {
	}
	
	public static boolean spawn(){
		int x = RandomNumber.randomValue(0, 180);//a amélioré
		return (x<=1);
	}
	
	
	
	
}
