package pong.gui;

import java.awt.Point;

import pong.util.Direction;
import pong.util.RandomNumber;

public class BonusSpeed extends PongItem{
	
	private static final long serialVersionUID = 1L;

	protected static final int INIT_SPEED= 0;
	
	protected static final double boost= 1.5;
	protected static final int delay= 10000;
	protected static Ball ball;
	protected static long timer;
	protected static boolean efficient;
	protected static boolean spawner;
	
	protected static Point INIT_POSITION = new Point(-100,-100);
	
	/*RandomNumber.randomPoint((Window.WINDOW_SIZE.width/2)-100,(Window.WINDOW_SIZE.width/2)+100,(Window.WINDOW_SIZE.height/2)-100,(Window.WINDOW_SIZE.height/2)+100);//a vérifier*/
	
	public BonusSpeed(Ball b)
    {	
    	super("image/boostSpeed.png", INIT_POSITION,new Point(INIT_SPEED ,INIT_SPEED));
    	ball = b;
    	timer = 0;
    	efficient = false;
    	spawner = false;
    }


	public void rebondir(Direction d, int window_width, int window_height) {
	}
	
	public boolean spawn(){
		int x = RandomNumber.randomValue(0, 20);//a amélioré
		return (x<=1);	
	}
	
	public void spawning(){
		this.position = RandomNumber.randomPoint((Window.WINDOW_SIZE.width/2)-100,(Window.WINDOW_SIZE.width/2)+100,(Window.WINDOW_SIZE.height/2)-100,(Window.WINDOW_SIZE.height/2)+100);//a vérifier*/
		spawner = true;
	}
	
	
	public void process(){
		if (efficient)
		{
			if ((System.currentTimeMillis()-timer)>=10000)
			{
				desactivation();
			}
		}
		else{
			if (ball.collision(this)!=Direction.aucune)
			{
				activation();
			}
			else if(!spawner)
			{
				if (spawn())
					spawning();
			}
		}
	}
	
	public void activation(){
		Point pnew = (Point) ball.getSpeed().clone();
		pnew.setLocation(getX()*1.5, getY()*1.5);
		ball.setSpeed(pnew);
		efficient = true;
		timer = System.currentTimeMillis();
		this.position= INIT_POSITION;
		spawner = false;
	}
	
	public void desactivation(){
		Point pold = (Point) ball.getSpeed().clone();
		pold.setLocation(getX()/1.5, getY()/1.5);
		ball.setSpeed(pold);
		efficient = false;
		timer=0;
	}
	
	
	
	
}