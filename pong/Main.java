package pong;


import pong.game.Game;

/**
 * Classe main c'est elle qui éxécute le programme
 * 
 * @author paul et antoine
 *
 */
public class Main  {
	public static void main(String[] args) {
		Game game= new Game();
		game.run();
	}
}
