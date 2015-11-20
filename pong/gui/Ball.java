
package pong.gui;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import pong.util.Direction;

public class Ball extends PongItem {
    
	private static final long serialVersionUID = 1L;
	
	protected static final int INIT_SPEED = 4;
	
	protected static Point INIT_POSITION = new Point (50,50);
    
    
    public Ball()
    {
    load("image/ball.png");
	speed = new Point(INIT_SPEED,INIT_SPEED);
	position = INIT_POSITION;
    }

    public void rebondir(Direction d,int window_width,int window_height)
    {
    	if(d==Direction.haut)
    		speed.y=-Math.abs(speed.y);
    	else if(d==Direction.bas)
    		speed.y=Math.abs(speed.y);
    	else if(d==Direction.gauche)
    		speed.x=-Math.abs(speed.x);
    	else if(d==Direction.droite)
    		speed.x=Math.abs(speed.x);
    	
    }
}
