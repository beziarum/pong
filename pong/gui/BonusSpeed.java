package pong.gui;

import java.awt.Point;

import pong.util.Direction;
import pong.util.RandomNumber;

/**
 * 
 * @author paul et antoine
 * classe de l'item bonus speed
 * c'est un item qui apparait de maniére aléatoire et augmente la vitesse de la balle si cette derniere le touche
 */
public class BonusSpeed extends PongItem{
	
	private static final long serialVersionUID = 1L;

	/**
	 * vitesse de bonus speed
	 */
	private static final int INIT_SPEED= 0;
	
	/**
	 * valeur du multiplicative de l'augmentation de la vitesse
	 */
	private static final double boost= 1.5;
	
	/**
	 * delai en miliseconde de l'application du boost
	 */
	private static final int delay = 10000;
	
	/**
	 * probabilité de faire apparaitre le bonus
	 */
	private static final int prob = 500;
	
	/**
	 * balle de la partie en cour
	 */
	private Ball ball;
	
	/**
	 * chrono pour gérer le temps d'activation de l'effet
	 */
	private long timer;
	
	/**
	 * boolean qui indique si l'effet est actif
	 */
	private boolean efficient;
	
	/**
	 * boolean qui indique si l'objet est actuellement sur le terrain
	 */
	private boolean spawner;
	
	/**
	 * position initial hors de l'écrans
	 */
	private static Point INIT_POSITION = new Point(-100,-100);
	
	/**
	 * constructeur du bonusSpeed
	 * @param Ball du jeux en cours
	 */
	public BonusSpeed(Ball b)
    {	
    	super("image/boostSpeed.png", INIT_POSITION,new Point(INIT_SPEED ,INIT_SPEED));
    	ball = b;
    	timer = 0;
    	efficient = false;
    	spawner = false;
    }

	/**
	 * fonction hérité de ponItem mais qui ne sera pas appelé
	 */
	public void rebondir(Direction d, int window_width, int window_height) {
	}
	
	/**
	 * détermine si il faut faire apparaitre l'objet
	 */
	public boolean spawn(){
		int x = RandomNumber.randomValue(0, prob);//a amélioré
		return (x<=1);	
	}
	
	/**
	 * fait apparaitre le bonus a une position aléatoire
	 */
	public void spawning(){
		this.setCenter(RandomNumber.randomPoint((Window.WINDOW_SIZE.width/2)-100,(Window.WINDOW_SIZE.width/2)+100,(Window.WINDOW_SIZE.height/2)-50,(Window.WINDOW_SIZE.height/2)+100));//a vérifier*/
		spawner = true;
	}
	
	/**
	 * réinitialise les valeur de la classe en cas de point marqué
	 */
	public void reinit(){
		timer = 0;
    	efficient = false;
    	spawner = false;
    	this.position = INIT_POSITION;
	}
	
	/**
	 * fonction principale de la classe qui gére son comportement en fonction de ou en est le jeux
	 */
	public void process(){
		//System.out.println("bonusspeed = " +ball.speed);
		if (efficient)
		{
			if ((System.currentTimeMillis()-timer)>=delay)
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
		timer = System.currentTimeMillis();
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
		timer=0;
	}
	
	private int boostAxis(int value){
		boolean isNegative=value<0;
		value=Math.abs(value);
		value*=boost;
		if (isNegative)
			value=-value;
		return value;
	}
	
	private int unboostAxis(int value)
	{
		boolean isNegative=value<0;
		value=Math.abs(value);
		value/=boost;
		if (isNegative)
			value=-value;
		return value;
	}
}
