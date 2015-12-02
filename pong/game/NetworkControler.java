package pong.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class NetworkControler {
	
	private LinkedList<Socket> list;
	private ServerSocket serv;
	public NetworkControler()
	{
		try {
			serv=new ServerSocket(37650);
			serv.setSoTimeout(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return !list.isEmpty();
	}
	
	public Socket getNewConnection()
	{
		return list.poll();
	}
}
