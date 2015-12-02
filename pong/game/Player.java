package pong.game;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import pong.gui.Bordure;
import pong.gui.Racket;

public class Player {
	private Socket s;
	private DataInputStream is;
	private OutputStream os;
	private Racket racket;
	private Bordure b;
	
	Player(Socket s,Bordure b)
	{
		this.b=b;
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
		racket.setPosition(Integer.valueOf(worlds[0]),Integer.valueOf(worlds[1]));
		return racket;
	}
	
	public void sendNewPos(Point pos)
	{
		try {
			StringBuffer sb=new StringBuffer();
			sb.append(pos.x);
			sb.append(' ');
			sb.append(pos.y);
			sb.append('\n');
			os.write(sb.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isInGameOver()
	{
		
		return false;
	}
}
