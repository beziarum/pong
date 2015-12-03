package pong.game;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import pong.gui.Ball;
import pong.gui.Bordure;
import pong.gui.Racket;
import pong.gui.Window;
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
		racket.setPosition(rotate(new Point(Integer.valueOf(worlds[0]), Integer.valueOf(worlds[1]))));
	}
	
	public void sendNewPos(Racket r)
	{
		Point pos=r.getPosition();
		try {
			StringBuffer sb=new StringBuffer();
			sb.append(pos.x);
			sb.append(' ');
			sb.append(pos.y);
			sb.append('\n');
			os.write(sb.toString().getBytes());
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Point rotate(Point p)
	{
		return new Point(Window.WINDOW_SIZE.width-p.x,Window.WINDOW_SIZE.height-p.y);
	}
	
	public boolean isInGameOver(Ball ball)
	{
		return bordure.collision(ball)==Direction.aucune;
	}
}
