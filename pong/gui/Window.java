package pong.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<PongItem> a;
	
	private static final Dimension WINDOW_SIZE=new Dimension(800,600);
	
	public static final int timestep = 10;
	
	private Color backgroundColor=new Color(0xFF, 0x40, 0);
	
	protected Image buffer;
	protected Graphics gContext;
	
	public Window(ArrayList<PongItem> a){
		setPreferredSize(WINDOW_SIZE);
		pack();
		setTitle("Pong");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		buffer=createImage(WINDOW_SIZE.height,WINDOW_SIZE.width);
		if (buffer == null)
			throw new RuntimeException("Could not instanciate graphics");
		gContext = buffer.getGraphics();
		
		this.a=a;
	}

	public Dimension getSize(){
		return (Dimension)WINDOW_SIZE.clone();
	}
	
	public void paint(Graphics g){
		gContext.setColor(backgroundColor);
		gContext.fillRect(0, 0, WINDOW_SIZE.height, WINDOW_SIZE.width);
		for(PongItem e : a)
			e.paint(gContext);
		g.drawImage(buffer, 0, 0, this);
	}
	
	public void sleep(){
		try {
			Thread.sleep(timestep);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
