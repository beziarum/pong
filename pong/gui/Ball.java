
package pong.gui;

import java.awt.Point;


import pong.game.NetworkControler;
import pong.util.Direction;
import pong.util.RandomNumber;


/**
 *  classe de l'item ball
 * @author paul et antoine
 *
 */
public class Ball extends PongItem {
    
	private static final long serialVersionUID = 1L;
	
	/**
	 * vitesse initialle de la balle
	 */
	public static final int INIT_SPEED= 4;
	
	/**
	 * position initiale de la balle générée aléatoirement
	 */
	private static Point INIT_POSITION = RandomNumber.randomPoint((Window.WINDOW_SIZE.width/2)-100,(Window.WINDOW_SIZE.width/2)+100,(Window.WINDOW_SIZE.height/2)-100,(Window.WINDOW_SIZE.height/2)+100);
	

	/**
	 * coordonée minimale, en x ou en y pour le spawn de la balle
	 */
	private int zoneSpawnMin=(GamePanel.WINDOW_SIZE.width/2)-200;
	
	/**
	 * coordonée maximale, en x ou en y pour le spawn de la balle
	 */
	private int zoneSpawnMax=(GamePanel.WINDOW_SIZE.width/2)+200;
	
	/**
	 * génératon d'une direction aléatoire de départ pour la balle
	 * @return Point vitesse
	 */
    private static int generationSpeed(){
    	int x = RandomNumber.randomValue(0 , 1);
    	if(x==0) 
    		x = -1;
    	return x*INIT_SPEED;
    }
	
	/**
	 * constructeur appelant celui de la classe mère pongItem
	 */
    public Ball()
    {	
    	super("image/ball.png", INIT_POSITION, new Point(generationSpeed(),generationSpeed()));
    }

    /**
     * génére un rebond en fonction de la direction de la collision
     * @param d direction de la collision
     * @param window_width largeur de la fenetre
     * @param window_height hauteur de la fenetre
     */
    public void rebondir(Direction d,int window_width,int window_height)
    {
    	if(d==Direction.haut)
    		speed.y=-Math.abs(speed.y);
    	else if(d==Direction.bas)
    		speed.y=Math.abs(speed.y);
    	else if(d==Direction.gauche)
    		speed.x=-Math.abs(speed.x);
    	else if(d==Direction.droite)
    		speed.x=Math.abs(speed.x);
    	hitbox.setPos(this.getPosition());
    	
    }
    
    /**
     * fait réaparaitre la balle a une position aléatoire
     */
    public void respawn(){
    	Point speed=new Point(generationSpeed(),generationSpeed());
    	Point position=RandomNumber.randomPoint(zoneSpawnMin,zoneSpawnMax,zoneSpawnMin,zoneSpawnMax);
    	if(NetworkControler.invertAleaPoint)
    	{
    		speed.x=-speed.x;		//inverse le vecteur vitesse (pour le joueur en réseau.)
    		speed.y=-speed.y;
    	}
    	setSpeed(speed);
    	setCenter(position);
    }
    
}
