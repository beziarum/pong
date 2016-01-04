
package pong.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import pong.util.Direction;
import pong.util.Hitbox;

/**
 * @author paul et antoine
 * PongItem est la classe mère de tous les item présent dans le jeux
 */
abstract public class PongItem extends JPanel {
	
    private static final long serialVersionUID = 1L;
    
    /**
     * taille de l'item
     * @see PongItem#getWidth()
     */
    protected int width;
    
    /**
     * taille du l'item
     * @see PongItem#getHeight()
     */
    protected int height;
    
    /**
     * position de l'objet
     * @see PongItem#getPosition()
     */
    protected Point position;
    
    /**
     * hitbox de l'objet
     * @see PongItem#getHitbox()
     */
    protected Hitbox hitbox;
    
    /**
     * vitesse de l'objet
     * @see PongItem#getSpeed()
     */
    protected Point speed;
    
    /**
     * image de l'objet
     */
    protected Image im;
    
    /**
     * variable de temps pour syncroniser les joueurs
     */
    private long time;
    
    /**
     * Détermine les actions a éffectuer l'or d'un rebond.
     * @param d direction de la collision
     * @param window_width largeur de la fenetre
     * @param window_height hauteur de la fenetre
     */
    abstract public void rebondir(Direction d,int window_width,int window_height);
    
    /**
     * Constructeur de PongItem
     * @param s nom de l'image a charger
     * @param position position de l'objet
     * @param speed vitesse de l'objet
     */
    protected PongItem(String s , Point position, Point speed){
    	load(s);
    	init(position, speed);
    	time=System.currentTimeMillis();
    }
    
    /**
     * second constructeur pour la bordure du jeux
     */
    protected PongItem(){
    	time=System.currentTimeMillis();
    }
    
    /**
     * initialise positin speed et hitbox de l'objet tout en respectant l'encapsulation
     * @param position position de l'item
     * @param speed vitesse de l'objet
     */
    protected void init(Point position, Point speed){
    	this.position = (Point) position.clone();
    	this.speed = (Point) speed.clone();
    	hitbox = new Hitbox(this);
    }
    
    /**
     * charge une image en fonction de son nom s
     * @param s nom de l'image a charger
     */
    protected void load(String s){
    	ImageIcon icon;
    	im = Toolkit.getDefaultToolkit().createImage(
    	    ClassLoader.getSystemResource(s));
    	icon = new ImageIcon(im);
    	width = icon.getIconWidth();
    	height = icon.getIconHeight();
    }

    /**
     * Fonction gérant les déplacement des items en fonction du temps
     */
    public void animate(){
    	long newtime=System.currentTimeMillis();
    	int tick=(int)(newtime-time);
    	time=newtime;
    	position.translate(speed.x,speed.y);
    	hitbox.setPos(position);
    }
    
    /**
     * retourne hauteur
     * @return hauteur item
     */
    public int getHeight(){
		return height;
	}
    
    /**
     * retourne largeur
     * @return largeur item
     */
	public int getWidth(){
		return width;
	}
	
	/**
	 * retourne la position de l'item
	 * @return position de l'item
	 */
	public Point getPosition(){
		return position;
	}
	
	/**
	 * modifie la position de l'item
	 * @param pos
	 * 			position de l'item
	 */
	public void setPosition(Point pos){
		position = (Point) pos.clone();
		hitbox.setPos(position);
	}
	
	/**
	 * retourne la hitbox de l'item
	 * @return la hitbox de l'item
	 */
	public Hitbox getHitbox(){
		return hitbox;
	}

	/**
	 * retourne la vitesse de l'item
	 * @return la vitesse de l'item
	 */
	public Point getSpeed(){
		return speed;
	}
	
	/**
	 * Modifie la vitesse de l'item
	 * @param point
	 * 				point qui correspond a une vitesse
	 */
    public void setSpeed(Point point){
    	speed = (Point) point.clone();
    }
    
    
   /**
    * retourne le centre de l'item
    * @return un point qui est le centre de l'item
    */
    public Point getCenter(){
    	return new Point (getPosition().x+(getWidth()/2),getPosition().y+(getHeight()/2));
    }
    
    /**
     * modifie le centre de l'item et la position de ce dernier
     * @param p
     * 			point qui sera le nouveau centre de l'item
     */
    public void setCenter(Point p){
    	setPosition(new Point (p.x-(getWidth()/2),p.y-(getHeight()/2)));
    }
    
    /**
     * affiche l'item
     * @param Graphics fenetre
     */
    public void paint(Graphics GContext)
    {
    	GContext.drawImage(im, position.x, position.y,
    			width, height, null);
    }
    
    /**
     * retourne la direction de la collision
     * @param i
     * 			item
     * @return la direction de la colision
     */
    public Direction collision(PongItem i){
    	return hitbox.colision(i.hitbox);
    }
}
