
package pong.gui;

import java.awt.Point;

import pong.util.Direction;
import pong.util.RandomNumber;

public class Ball extends PongItem {
    
	private static final long serialVersionUID = 1L;
	
	protected static final int INIT_SPEED= 4;
	
	protected static Point INIT_POSITION = RandomNumber.randomPoint((Window.WINDOW_SIZE.width/2)-100,(Window.WINDOW_SIZE.width/2)+100,(Window.WINDOW_SIZE.height/2)-100,(Window.WINDOW_SIZE.height/2)+100);
	
    private static int generationSpeed(){
    	int x = RandomNumber.randomValue(0 , 1);
    	if(x==0) 
    		x = -1;
    	return x*INIT_SPEED;
    }
	
	
    public Ball()
    {	
    	super("image/ball.png", INIT_POSITION, new Point(generationSpeed(),generationSpeed()));
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
    	hitbox.setPos(this.getPosition());
    	
    }
    
    public void respawn(){
    	this.setSpeed(new Point(generationSpeed(),generationSpeed()));
    	this.setPosition(RandomNumber.randomPoint((Window.WINDOW_SIZE.width/2)-100,(Window.WINDOW_SIZE.width/2)+100,(Window.WINDOW_SIZE.height/2)-100,(Window.WINDOW_SIZE.height/2)+100));
    }
}
