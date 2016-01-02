package pong.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Dimension WINDOW_SIZE=new Dimension(800,600);
	private Color backgroundColor=new Color(0xFF, 0x40, 0);
	private ArrayList<PongItem> a;
	GamePanel(ArrayList<PongItem> a)
	{
		this.a=a;
		setMinimumSize(WINDOW_SIZE);
	}
	
	public void Paint(){};
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, WINDOW_SIZE.width, WINDOW_SIZE.height);
		for(PongItem e : a)
			e.paint(g);
	}

	public static Point rotate(Point p)
	{
		return new Point(Window.WINDOW_SIZE.width-p.x,GamePanel.WINDOW_SIZE.height-p.y);
	}
}
