package pong.game;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import pong.gui.Ball;
import pong.gui.Bordure;
import pong.gui.Racket;
import pong.util.Direction;

public class Player {
	private DataInputStream is;
	private OutputStream os;
	private Racket racket;
	private Bordure bordure;
	private int score;
	
	Player(Socket s,Bordure b)
	{
		score=0;
		bordure=b;
		racket=new Racket();
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
	public void updatePos() throws EOFException, SocketException
	{
		racket.setCenter(NetworkControler.readPos(is));
	}
	
	public void sendNewPos(Racket r) throws SocketException
	{
		NetworkControler.sendPos(os, r.getCenter());
	}
	
	public boolean isInGameOver(Ball ball)
	{
		return ball.collision(bordure)!=Direction.aucune || (ball.collision(racket)==Direction.bas || ball.collision(racket)==Direction.haut);
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int s){
		score = s;
	}
}
