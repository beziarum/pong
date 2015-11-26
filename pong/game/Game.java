package pong.game;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import pong.gui.Ball;
import pong.gui.Bordure;
import pong.gui.PongItem;
import pong.gui.Racket;
import pong.gui.Window;
import pong.util.Direction;

public class Game implements KeyListener{
		
	public static final int timestep = 10;
	
	private Window window;
	
	private ArrayList<PongItem> a;
	
	
	private Bordure bg;
	private Racket r;
	private Ball b;
	
	private boolean keyIsPressed=false;
	
	
	public Game()
	{	
		a=new ArrayList<PongItem>();
		
		window=new Window(a);
		
		int windowSizeX=window.getSize().width;
		int windowSizeY=window.getSize().height;
		
		r= new Racket();
		b= new Ball();
		a.add(r);
		a.add(b);
		a.add(bg=new Bordure(Direction.gauche,windowSizeX,windowSizeY));
		a.add(new Bordure(Direction.droite,windowSizeX,windowSizeY));
		a.add(new Bordure(Direction.haut,windowSizeX,windowSizeY));
		a.add(new Bordure(Direction.bas,windowSizeX,windowSizeY));
		
		window.addKeyListener(this);
	}
	
	
	public void keyPressed(KeyEvent e) {
		if(keyIsPressed)
			return;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				r.setSpeed(new Point(0,-Racket.RACKET_SPEED));
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				r.setSpeed(new Point(0,Racket.RACKET_SPEED));
				break;
			default:
				System.out.println("got press "+e);
		}
		keyIsPressed=true;
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				r.setSpeed(new Point(0,0));
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			r.setSpeed(new Point(0,0));
				break;
			default:
				System.out.println("got release "+e);
				
		}
		keyIsPressed=false;
	}
	public void keyTyped(KeyEvent e) { }
	

	public void run()
	{
		while(true)
		{			
			for (PongItem e :a){
				e.animate();
				if(e==b || e==r)
				{
					for(PongItem e2 : a)
					{
						if(e==e2)
							continue;
						Direction d=e.collision(e2);
						if(d!=Direction.aucune)
						{
							if(e2==bg)
								window.gameOver();
							else
								e.rebondir(d, window.getSize().width, window.getSize().height);
						}
					}
				}
			}
			window.paint();
			window.sleep();
		}
	}
}
