package pong.game;

import java.net.Socket;

import pong.gui.Bordure;
import pong.gui.Racket;

public class Player {
	Racket racket;
	Bordure b;
	
	Player(Socket s,Bordure b)
	{
		this.b=b;
		racket=new Racket();
	}
	
	public Racket getRacket()
	{
		return racket;
	}
	
	public void sendNewPos()
	{
		
	}
	
	public boolean isInGameOver()
	{
		return false;
	}
}
