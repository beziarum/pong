package pong.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		setTitle("Pong");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		buffer=createImage(WINDOW_SIZE.width,WINDOW_SIZE.height);
		if (buffer == null)
			throw new RuntimeException("Could not instanciate graphics");
		gContext = buffer.getGraphics();
		this.a=a;
	}

	public Dimension getSize(){
		return (Dimension)WINDOW_SIZE.clone();
	}
	
	public void paint(){
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
			Thread.sleep(timestep);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void gameOver(){
		boolean replay=false;
		while(!replay){
			gContext.setColor(Color.blue);
			gContext.setFont(new Font("Courier", Font.BOLD, 30));
			gContext.drawString("Game Over !", 300, 250);
			repaint();
			try {
				Thread.sleep(timestep);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
