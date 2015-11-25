package pong.gui;

import java.awt.Graphics;
import java.awt.Point;

import pong.util.Direction;

public class Bordure extends PongItem {


	private static final long serialVersionUID = 1L;

	public Bordure(Direction d,int window_width, int window_height)
	{
		if(d==Direction.haut)
		{
			height=1;
			width=window_width;
			init(new Point(0,0),new Point(0,0));
		}
		else if(d==Direction.gauche)
		{
			height=window_height;
			width=1;
			init(new Point(0,0),new Point(0,0));
		}
		else if(d==Direction.bas)
		{
			height=1;
			width=window_width;
			init(new Point(0,window_height-1), new Point(0,0));
		}
		else if(d==Direction.droite)
		{
			height=window_height;
			width=1;
			init(new Point(window_width-1,0),new Point(0,0));
		}
	}
	
	public void rebondir(Direction d, int window_width, int window_height) {}
		
	public void paint(Graphics GContext){};
}
