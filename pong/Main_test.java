package pong;

import java.awt.Point;

import pong.gui.Ball;
import pong.gui.BonusSpeed;
import pong.gui.Bordure;
import pong.gui.GamePanel;
import pong.util.Direction;
/**
 * 
 * @author paul et antoine
 *Cette classe contient les différents tests que nous avons implémenté pour nos fonctions
 *Les tests ne sont pas complet certaine fonction ayant été testé visuellement
 */
public class Main_test {
	/**
	 * Fonction de test qui test certaine fonction du programme.
	 * @param args
	 */
	public static void main (String[] args){	
	
	int windowSizeX=GamePanel.WINDOW_SIZE.width;
	int windowSizeY=GamePanel.WINDOW_SIZE.height;
		
	boolean success=true;
	
	//test des fonction de base de ball et de PongItem
	Ball b = new Ball();
	Point pos = (Point) b.getPosition().clone();
	Point center = (Point) b.getCenter().clone();
	b.animate();
	if (b.getPosition().getX() != pos.getX()+b.getSpeed().getX())
		success=false;
	if (b.getPosition().getY() != pos.getY()+b.getSpeed().getY())
		success=false;
	if (b.getCenter().getX() != center.getX()+b.getSpeed().getX())
		success=false;
	if (b.getCenter().getY() != center.getY()+b.getSpeed().getY())
		success=false;
	if (!(b.getHitbox().getPos().equals(b.getPosition())))
		success = false;
	
	//test du respawn de la balle
	
	Point spawn1,spawn2;
	b.respawn();
	spawn1 = (Point) b.getPosition().clone();
	b.respawn();
	spawn2= (Point) b.getPosition().clone();
	if(spawn1 == spawn2)
		success = false;
	
	//test des bordure et des collision
	
	Bordure bh = new Bordure(Direction.haut,windowSizeX,windowSizeY);
	b.setPosition(new Point(100,-1));
	Direction d = b.collision(bh);
	if(d!=Direction.bas)
		success = false;
	
	//test du bonusSpeed et de la bonne application de son effet
	
	BonusSpeed bs = new BonusSpeed(b);
	bs.setPosition(new Point(100,100));
	b.setPosition(new Point(100,100));
	b.setSpeed(new Point(4,-4));
	bs.process();
	
	if(!(b.getSpeed().equals(new Point(6,-6))))
		success = false;
	
	if(!(bs.getPosition().equals(new Point (-100,-100))))
		success = false;
	
	
	if (success == true)
		System.out.println("les tests sont tous passé avec succés");
	else
		System.out.println("les tests n'ont pas réussie");
		
	
	}
	
}
