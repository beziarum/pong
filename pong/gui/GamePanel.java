package pong.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
	};
	
	public void Paint(){};
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, WINDOW_SIZE.width, WINDOW_SIZE.height-50);
		for(PongItem e : a)
			e.paint(g);
	}

}
