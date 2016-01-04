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
	private boolean controlBall;
	
	Player(Socket s,Bordure b,boolean control)
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
		controlBall=control;
	}
	
	public Racket getRacket()
	{
		return racket;
	}
	
	public void update(Racket r, Ball b) throws SocketException, EOFException{
		
		if(controlBall)
		{
			NetworkControler.sendRacket(os, racket);
			NetworkControler.sendBall(os, b);
			NetworkControler.readRacket(is, racket);
		}
		else
		{
			NetworkControler.sendRacket(os, racket);
			NetworkControler.readRacket(is, r);
			NetworkControler.readBall(is, b);
		}
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
		return bordure.collision(ball)!=Direction.aucune;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int s){
		score = s;
	}
}
