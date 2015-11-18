package game;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import pong.gui.Ball;
import pong.gui.PongItem;
import pong.gui.Racket;
import pong.util.Direction;

public class Game {
	
	int SIZE_PONG_X = 480;
	int SIZE_PONG_Y = 480;
	
	
	private ArrayList<PongItem> a;
	
	private Racket r;
	
	Game()
	{
		r= new Racket();
		a.add(r);
		a.add(new Ball());
	}
	
	
	private void limit(PongItem i){
		if (i.getPosition().y < 0)
			i.rebondir(Direction.haut,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().y > SIZE_PONG_Y - i.getHeight()/2)
			i.rebondir(Direction.bas,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().x > SIZE_PONG_X - i.getWidth()/2)
			i.rebondir(Direction.gauche,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().x < 0)
			i.rebondir(Direction.droite,SIZE_PONG_X,SIZE_PONG_Y);
	}
	
	public void keyPressed(KeyEvent e) {
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
	}

	public void run()
	{
		while(true)
		{
			for (PongItem e : a) {
				limit(e);
				e.animate();
			}
		}
	}
}
