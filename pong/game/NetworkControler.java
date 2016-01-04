package pong.game;

import java.awt.Point;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;

import pong.gui.Ball;
import pong.gui.GamePanel;
import pong.gui.Racket;
import pong.util.RandomNumber;


public class NetworkControler {
	
	private LinkedList<Socket> list;
	private Socket connectWait=null;
	private ServerSocket serv;
	private boolean LHBA = false;
	
	/**
	 * indique si il faut appliquer une symétrie à la balle quand on la réinitialise.
	 */
	public static boolean invertAleaPoint=false;
	
	public NetworkControler()
	{
		try {
			serv=new ServerSocket(37650);
			serv.setSoTimeout(1);
			serv.setReuseAddress(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		list=new LinkedList<Socket>();
	}
	
	public boolean haveNewConnection()
	{
		Socket tmp;
		try {
			tmp = serv.accept();
			if(tmp!=null)
				list.add(tmp);
		} catch (SocketTimeoutException e) {
			//normal
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connectWait!=null || !list.isEmpty();
	}
	
	public Socket getNewConnection(Ball b)
	{
		if(connectWait!=null)
		{
			Socket s=connectWait;
			try {
				InputStream is=s.getInputStream();
				RandomNumber.setSeed(Long.valueOf(readLine(is)));
				RandomNumber.consumeNDouble(Long.valueOf(readLine(is)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectWait=null;
			invertAleaPoint=true;
			LHBA=true;
			return s;
		}
		else
			{
			Socket s=list.poll();
			StringBuffer init=new StringBuffer();
			init.append("PONG");
			init.append(0);
			init.append(' ');
			init.append(2);
			init.append('\n');
			init.append(RandomNumber.getSeed());
			init.append('\n');
			init.append(RandomNumber.getNumberOfDoubleConsumed());
			init.append('\n');
			try {
				s.getOutputStream().write(init.toString().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			LHBA = false;
			return s;
		}
	}
	
	public void connect(String str)
	{
		try {
			Socket sock=new Socket(str,37650);
			String s=readLine(sock.getInputStream());
			if (s.equals("PONG0 2"))
				connectWait=sock;
			else
			{
				sock.close();
				throw new RuntimeException("Unsuported Protocol"+s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void sendPos(OutputStream os,Point pos) throws IOException{
			StringBuffer sb=new StringBuffer();
			sb.append(pos.x);
			sb.append(' ');
			sb.append(pos.y);
			sb.append('\n');
			os.write(sb.toString().getBytes());
			os.flush();
	}
	
	private static String readLine(InputStream is) throws IOException{
		StringBuffer s=new StringBuffer();
		char c='\0';
		while(c!='\n' && c!=-1)
		{
			c=(char)is.read();
			if(c!='\n')
				s.append(c);
		}
		if(c==-1)
			throw new EOFException();
		//System.out.println(s.toString());
		return s.toString();
	}
	
	private static Point readPos(InputStream is) throws IOException
	{
		return GamePanel.rotate(readPoint(is));
	}
	
	private static Point readPoint(InputStream is) throws IOException
	{
		String[] worlds=readLine(is).split(" ");
		return new Point(Integer.valueOf(worlds[0]),Integer.valueOf(worlds[1]));
	}
	
	public static void readRacket(InputStream is,Racket r) throws IOException
	{
		r.setCenter(readPos(is));
	}
	
	public static void sendRacket(OutputStream os, Racket r) throws IOException
	{
		os.write("RACK".getBytes());
		sendPos(os, r.getCenter());
		
	}
	
	public static void readBall(InputStream is, Ball b) throws IOException
	{
		b.setCenter(readPos(is));
		Point speed=readPoint(is);
		speed.x=-speed.x;
		speed.y=-speed.y;
		b.setSpeed(speed);
	}
	
	public static void sendBall(OutputStream os, Ball b) throws SocketException
	{
		try{
			os.write("BALL".getBytes());
			sendPos(os, b.getCenter());
			sendPos(os, b.getSpeed());
			}catch (SocketException e){
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static String nextData(InputStream is) throws IOException
	{
		StringBuffer ret=new StringBuffer();
		for(int i=0; i<4; i++)
			ret.append((char)is.read());
		if(ret.charAt(3)==-1)
			throw new EOFException();
		return ret.toString();
	}
	
	public boolean lastHasBeenAsked()
	{
		return LHBA;
	}
}
