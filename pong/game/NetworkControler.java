package pong.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.LinkedList;


public class NetworkControler {
	
	private LinkedList<Socket> list;
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
		return !list.isEmpty();
	}
	
	public Socket getNewConnection()
	{
		return list.poll();
	}
	
	public void connect(String str)
	{
		try {
			Socket sock=new Socket(str,37650);
			list.add(sock);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
