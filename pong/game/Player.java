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
		controlBall=!control;
	}
	
	public Racket getRacket()
	{
		return racket;
	}
	
	public void update(Racket r, Ball b) throws SocketException, EOFException{
		
		if(controlBall)
		{
			try {
				NetworkControler.sendRacket(os, r);
				NetworkControler.sendBall(os, b);
				NetworkControler.nextData(is);
				NetworkControler.readRacket(is, racket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				NetworkControler.sendRacket(os, r);
				NetworkControler.nextData(is);
				NetworkControler.readRacket(is, racket);
				NetworkControler.nextData(is);
				NetworkControler.readBall(is, b);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
