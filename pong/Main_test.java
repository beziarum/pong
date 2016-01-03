package pong;

import pong.gui.Ball;
import pong.gui.BonusSpeed;
import pong.gui.Bordure;
import pong.gui.GamePanel;
import pong.gui.Racket;
import pong.gui.Window;
import pong.util.Direction;

public class Main_test {
	
	
	//private static Window window;
	private static Bordure bg;
	private static Bordure bd;
	private static Bordure bh;
	private static Bordure bb;
	private static Racket r;
	private static Ball b;
	private static BonusSpeed bs;
	private static boolean gameOver=false;
	private static boolean test=true;
	
	public static void main (String[] args){
	
	int windowSizeX=GamePanel.WINDOW_SIZE.width;
	int windowSizeY=GamePanel.WINDOW_SIZE.height;
	
	r= new Racket();
	b= new Ball();
	bg=new Bordure(Direction.gauche,windowSizeX,windowSizeY);
	bd=new Bordure(Direction.droite,windowSizeX,windowSizeY);
	bh=new Bordure(Direction.haut,windowSizeX,windowSizeY);
	bb=new Bordure(Direction.bas,windowSizeX,windowSizeY);
	bs=new BonusSpeed(b);
	
	if (!Ball.test())
		test= false;
	if (test=true)
		System.out.println("les test sont tous passé avec succé");
		
	
	}
	
}
