package game;

import java.util.ArrayList;

import pong.gui.Ball;
import pong.gui.PongItem;
import pong.gui.Racket;
import pong.util.Direction;

public class Game {
	
	int SIZE_PONG_X = 480;
	int SIZE_PONG_Y = 480;
	
	
	private ArrayList<PongItem> a;
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
	
	Game()
	{
		a.add(new Racket());
		a.add(new Ball());
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
