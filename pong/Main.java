package pong;

import pong.game.Game;

/**
 * Starting point of the Pong application
 */
public class Main  {
	public static void main(String[] args) {
		Game game= new Game();
		game.run();
	}
}
