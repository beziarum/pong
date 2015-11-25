package pong.gui;

import java.awt.Graphics;
import java.awt.Point;

import pong.util.Direction;

public class Bordure extends PongItem {


	private static final long serialVersionUID = 1L;

	public Bordure(Direction d,int window_width, int window_height)
	{
		
		init(new Point(0,window_width),new Point(0,0));
	}
	
	public void rebondir(Direction d, int window_width, int window_height) {}
		
	public void paint(Graphics GContext){};
}
