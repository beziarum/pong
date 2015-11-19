
package pong.gui;

import javax.swing.JPanel;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import pong.util.Direction;


abstract public class PongItem extends JPanel {
    private static final long serialVerisonUID = 1L;

    //protected final Image im;

    protected int width;
    
    protected int height;
    
    protected Point position;

    //abstract protected static final int INIT_SPEED;
    
    protected Point speed;
    
    protected Image im;
    
    abstract public void rebondir(Direction d,int window_width,int window_height);
    
    public PongItem()
    {
    	position=new Point(0,0);
    }
    
    protected void load(String s){
    	ImageIcon icon;
    	im = Toolkit.getDefaultToolkit().createImage(
    	    ClassLoader.getSystemResource(s));
    	icon = new ImageIcon(im);
    	width = icon.getIconWidth();
    	height = icon.getIconHeight();
    }

    public void animate(){
	position.translate(speed.x,speed.y);
    }
    
    public int getHeight(){
		return height;
	}
    
	public int getWidth(){
		return width;
	}
	
	public Point getPosition(){
		return position;
	}

    public void setSpeed(Point point){
    	speed = (Point) point.clone();
    }
    
    public void paint(Graphics GContext)
    {
    	GContext.drawImage(im, position.x, position.y,
    			width, height, null);
    }
}
