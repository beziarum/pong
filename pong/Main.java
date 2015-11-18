package pong;

import game.Game;
import pong.gui.Window;
import pong.gui.Pong;

/**
 * Starting point of the Pong application
 */
public class Main  {
	public static void main(String[] args) {
		Game game= new Game();
		game.run();
	}
}
