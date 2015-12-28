package pong;


import pong.game.Game;

public class Main  {
	public static void main(String[] args) {
		if(System.console()!=null)
		System.out.println(System.console().readLine());
		Game game= new Game();
		game.run();
	}
}
