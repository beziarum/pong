
package pong.gui;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import pong.util.Direction;

public class Ball extends PongItem {
    
    protected static final int INIT_SPEED = 4;
    
    
    public Ball()
    {
    load("image/ball.png");
	speed = new Point(INIT_SPEED,INIT_SPEED);
    }

    public void rebondir(Direction d,int window_width,int window_height)
    {
    	if(d==Direction.haut || d==Direction.bas)
    		speed.y=0-speed.y;
    	else if(d==Direction.droite || d==Direction.gauche)
    		speed.x=0-speed.x;
    	
    }
}
