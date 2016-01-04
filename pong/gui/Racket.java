package pong.gui;

import java.awt.Point;

import pong.util.Direction;

/**
 * 
 * classe de l'item racket
 * @author paul et antoine
 *
 */
public class Racket extends PongItem {

	private static final long serialVersionUID = 1L;
	
	/**
	 * vitesse initiale
	 */
	private static final int INIT_SPEED = 0;
	
	/**
	 * vitesse de la racket lors d'un déplacement
	 */
	public static final int RACKET_SPEED = 4;
	
	/**
	 * position de départ de la raquette
	 */
	private static Point INIT_POSITION = new Point (0,100);
	
	/**
	 * constructeur de la raquette
	 */
	public Racket(){
		super("image/racket.png", INIT_POSITION, new Point (INIT_SPEED,INIT_SPEED));
	}
	
	/**
	 * gère le rebond de la raquette avec les bordures en haut et en bas
	 * @param d direction de la collision
     * @param window_width largeur de la fenetre
     * @param window_height hauteur de la fenetre
	 */
	public void rebondir(Direction d,int window_width,int window_height){
		if (d == Direction.bas && this.position.y<=0)
			this.position.y=0;
		else if (d == Direction.haut && this.position.y + this.height >= window_height)
			this.position.y= window_height - this.height;
		hitbox.setPos(this.getPosition());
	}
	
	
}
