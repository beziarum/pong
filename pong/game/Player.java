package pong.game;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import pong.gui.Ball;
import pong.gui.Bordure;
import pong.gui.Racket;
import pong.util.Direction;

public class Player {
	private Socket s;
	private DataInputStream is;
	private OutputStream os;
	private Racket racket;
	private Bordure bordure;
	Player(Socket s,Bordure b)
	{
		bordure=b;
		racket=new Racket();
		this.s=s;
		try {
			is=new DataInputStream(s.getInputStream());
			os=s.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Racket getRacket()
	{
		return racket;
	}
	public void updatePos()
	{
		racket.setCenter(NetworkControler.readPoint(is));
	}
	
	public void sendNewPos(Racket r)
	{
		NetworkControler.sendPos(os, r.getCenter());
	}
	
	public boolean isInGameOver(Ball ball)
	{
		return bordure.collision(ball)!=Direction.aucune;
	}
}
