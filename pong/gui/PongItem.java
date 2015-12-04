
package pong.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import pong.util.Direction;
import pong.util.Hitbox;


abstract public class PongItem extends JPanel {
	
    private static final long serialVersionUID = 1L;

    protected int width;
    
    protected int height;
    
    protected Point position;
    
    protected Hitbox hitbox;
    
    protected Point speed;
    
    protected Image im;
    
    abstract public void rebondir(Direction d,int window_width,int window_height);
    
    protected PongItem(String s , Point position, Point speed){
    	load(s);
    	init(position, speed);
    }
    
    protected PongItem(){};
    
    protected void init(Point position, Point speed){
    	this.position = (Point) position.clone();
    	this.speed = (Point) speed.clone();
    	hitbox = new Hitbox(this);
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
	hitbox.setPos(position);
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
	
	public void setPosition(Point pos){
		position = (Point) pos.clone();
	}
	
	public Hitbox getHitbox(){
		return hitbox;
	}

    public void setSpeed(Point point){
    	speed = (Point) point.clone();
    }
    
    public Point getCenter(){
    	return new Point (getPosition().x+(getHeight()/2),getPosition().y+(getWidth()/2));
    }
    
    public void setCenter(Point p){
    	setPosition(new Point (p.x-(getHeight()/2),p.y-(getWidth()/2)));
    }
    
    public void paint(Graphics GContext)
    {
    	GContext.drawImage(im, position.x, position.y,
    			width, height, null);
    }
    
    public Direction collision(PongItem i){
    	return hitbox.colision(i.hitbox);
    }
}
