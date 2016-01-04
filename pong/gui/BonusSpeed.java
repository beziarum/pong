package pong.gui;

import java.awt.Point;

import pong.util.Direction;
import pong.util.RandomNumber;

/**
 * classe de l'item bonus speed
 * c'est un item qui apparait de maniére aléatoire et augmente la vitesse de la balle si cette derniere le touche
 * 
 * @author paul et antoine
 * 
 */
public class BonusSpeed extends PongItem{
	
	private static final long serialVersionUID = 1L;

	/**
	 * vitesse de bonus speed
	 */
	private static final int INIT_SPEED= 0;
	
	/**
	 * valeur de l'augmentation de la vitesse
	 */
	private static final int boost= Ball.INIT_SPEED/2;
	
	/**
	 * probabilité de faire apparaitre le bonus
	 */
	private static final int probAparition = 1500;
	
	/**
	 * probabilité que l'effet de l'item se désactive
	 */
	private static final int probDesactivation = 1000;
	
	/**
	 * balle de la partie en cour
	 */
	private Ball ball;
	
	/**
	 * boolean qui indique si l'effet est actif
	 */
	private boolean efficient;
	
	/**
	 * boolean qui indique si l'objet est actuellement sur le terrain
	 */
	private boolean spawner;
	
	/**
	 * position initialle, hors de l'écran
	 */
	private static Point INIT_POSITION = new Point(-100,-100);
	
	/**
	 * coordonée minimale, en x ou en y pour le spawn du bonus
	 */
	private int zoneSpawnMin=(GamePanel.WINDOW_SIZE.width/2)-100;
	
	/**
	 * coordonée maximale, en x ou en y pour le spawn du bonus
	 */
	private int zoneSpawnMax=(GamePanel.WINDOW_SIZE.width/2)+100;
	/**
	 * constructeur du bonusSpeed
	 * @param Balle du jeu en cours
	 */
	public BonusSpeed(Ball b)
    {	
    	super("image/boostSpeed.png", INIT_POSITION,new Point(INIT_SPEED ,INIT_SPEED));
    	ball = b;
    	efficient = false;
    	spawner = false;
    }

	/**
	 * fonction héritée de pongItem mais qui ne sera pas appelée
	 */
	public void rebondir(Direction d, int window_width, int window_height) {
	}
	
	/**
	 * détermine si il faut faire apparaitre l'objet
	 */
	public boolean spawn(){
		int x = RandomNumber.randomValue(0, probAparition);
		return (x<=1);	
	}
	
	/**
	 * fait apparaitre le bonus a une position aléatoire
	 */
	public void spawning(){
		this.setCenter(RandomNumber.randomPoint(zoneSpawnMin,zoneSpawnMax,zoneSpawnMin,zoneSpawnMax));
		spawner = true;
	}
	
	/**
	 * Fonction qui gère l'arret de l'effet du bonus sur la balle
	 * 
	 * @return true si l'effet doit prendre fin
	 */
	public boolean removeEffect(){
		int x = RandomNumber.randomValue(0, probDesactivation);
		return (x<=1);	
	}
	
	/**
	 * réinitialise les valeurs de la classe en cas de point marqué
	 */
	public void reinit(){
    	efficient = false;
    	spawner = false;
    	this.position = INIT_POSITION;
	}
	
	/**
	 * fonction principale de la classe qui gère son comportement en fonction de où en est le jeu
	 */
	public void process(){
		if (efficient)
		{
			if (removeEffect())
			{
				desactivation();
			}
		}
		else{
			if (ball.collision(this)!=Direction.aucune)
			{
				activation();
			}
			else if(!spawner)
			{
				if (spawn())
					spawning();
			}
		}
	}
	

	
	/**
	 * augmente la vitesse de la balle
	 */
	public void activation(){
		Point pnew = (Point) ball.getSpeed().clone();
		pnew.setLocation(boostAxis(pnew.x), boostAxis(pnew.y));
		ball.setSpeed(pnew);
		efficient = true;
		this.position= INIT_POSITION;
		spawner = false;
	}
	
	/**
	 * retour a la vitesse normal de la balle
	 */
	public void desactivation(){
		Point pold = (Point) ball.getSpeed().clone();
		pold.setLocation(unboostAxis(pold.x), unboostAxis(pold.y));
		ball.setSpeed(pold);
		efficient = false;
	}
	
	/**
	 * Cette fonction retourne les valeurs d'accélération
	 * de la vitesse de la balle
	 * @param value
	 * @return	la valeur de l'accélération
	 */
	private int boostAxis(int value){
		boolean isNegative=value<0;
		if (isNegative)
			value-=boost;
		else
			value+=boost;
		return value;
	}
	
	/**
	 * Cette fonction retourne les valeurs de decélération
	 * de la vitesse de la balle
	 * @param value
	 * @return	la valeur de la décélération
	 */
	private int unboostAxis(int value)
	{
		boolean isPositive=value>0;
		if (isPositive)
			value-=boost;
		else
			value+=boost;
		return value;
	}
}
