package game;

import pong.gui.PongItem;
import pong.gui.Racket;
import pong.util.Direction;

public class Game {
	
	int SIZE_PONG_X = 480;
	int SIZE_PONG_Y = 480;
	
	public PongItem i;
	
	public void limit(){
		if (i.getPosition().y < 0)
			i.rebondir(Direction.haut,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().y > SIZE_PONG_Y - i.getHeight()/2)
			i.rebondir(Direction.bas,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().x > SIZE_PONG_X - i.getWidth()/2)
			i.rebondir(Direction.gauche,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().x < 0)
			i.rebondir(Direction.droite,SIZE_PONG_X,SIZE_PONG_Y);
	}
}
