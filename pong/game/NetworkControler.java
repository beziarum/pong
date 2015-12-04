package pong.game;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;

import pong.gui.Ball;
import pong.gui.Window;
import pong.util.RandomNumber;


public class NetworkControler {
	
	private LinkedList<Socket> list;
	private Socket connectWait=null;
	private ServerSocket serv;
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
				b.setCenter(readPoint(is));
				//b.setSpeed(readPoint(is));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectWait=null;
			return s;
		}
		else
			{
			Socket s=list.poll();
			StringBuffer init=new StringBuffer();
			init.append("PONG");
			init.append(0);
			init.append(' ');
			init.append(1);
			init.append('\n');
			init.append(RandomNumber.getSeed());
			init.append('\n');
			init.append(RandomNumber.getNumberOfDoubleConsumed());
			init.append('\n');
			try {
				s.getOutputStream().write(init.toString().getBytes());
				sendPos(s.getOutputStream(), b.getCenter());
				//sendPos(s.getOutputStream(), b.getSpeed());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s;
		}
	}
	
	public void connect(String str)
	{
		try {
			Socket sock=new Socket(str,37650);
			String s=readLine(sock.getInputStream());
			if (s.equals("PONG0 1"))
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
	
	public static void sendPos(OutputStream os,Point pos){
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
	
	public static String readLine(InputStream is){
		StringBuffer s=new StringBuffer();
		char c='\0';
		while(c!='\n')
		{
			try {
				c=(char)is.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(c!='\n')
				s.append(c);
		}
		return s.toString();
	}
	
	public static Point readPoint(InputStream is)
	{
		String[] worlds=readLine(is).split(" ");
		return rotate(new Point(Integer.valueOf(worlds[0]),Integer.valueOf(worlds[1])));
	}
	
	
	private static Point rotate(Point p)
	{
		return new Point(Window.WINDOW_SIZE.width-p.x,Window.WINDOW_SIZE.height-p.y);
	}
}
