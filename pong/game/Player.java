package pong.game;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import pong.gui.Bordure;
import pong.gui.Racket;

public class Player {
	private Socket s;
	private DataInputStream is;
	private Racket racket;
	private Bordure b;
	
	Player(Socket s,Bordure b)
	{
		this.b=b;
		racket=new Racket();
		this.s=s;
		try {
			is=new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Racket getRacket()
	{
		StringBuffer s=new StringBuffer();
		char c='\0';
		while(c!='\n')
		{
			try {
				c=is.readChar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.append(c);
		}
		String[] worlds=s.toString().split(" ");
		if(worlds.length != 2)
			throw new RuntimeException("Unexpected protocol");
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
