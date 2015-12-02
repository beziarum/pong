package pong.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static boolean printFps=false;
	
	private ArrayList<PongItem> a;
	
	public static final Dimension WINDOW_SIZE=new Dimension(800,600);
	
	public static final int timestep = 10;
	
	private Color backgroundColor=new Color(0xFF, 0x40, 0);
	
	protected Image buffer;
	protected Graphics gContext;
	
	private long time;
	private long delay=0;
	public Window(ArrayList<PongItem> a){
		setPreferredSize(WINDOW_SIZE);
		setTitle("Pong");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		buffer=createImage(WINDOW_SIZE.width,WINDOW_SIZE.height);
		if (buffer == null)
			throw new RuntimeException("Could not instanciate graphics");
		gContext = buffer.getGraphics();
		time=System.currentTimeMillis();
		this.a=a;
		gContext.drawString(" ", 300, 250);
	}

	public Dimension getSize(){
		return (Dimension)WINDOW_SIZE.clone();
	}
	
	public void paint(){
		if(printFps)
		{
			long fps=1000/delay;
			System.out.println(fps);
		}
		
		
		gContext.setColor(backgroundColor);
		gContext.fillRect(0, 0, WINDOW_SIZE.width, WINDOW_SIZE.height);
		for(PongItem e : a)
			e.paint(gContext);
		repaint();
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(buffer, 0, 0, this);
		pack();
	}
	
	public void sleep(){
		try {
			long newtime=System.currentTimeMillis();
			System.out.println(delay);
			delay=0;
			Thread.sleep(timestep-delay);
			time=System.currentTimeMillis();
			delay=time-newtime;
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
