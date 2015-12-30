package pong.game;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import pong.gui.Ball;
import pong.gui.Bordure;
import pong.gui.PongItem;
import pong.gui.Racket;
import pong.gui.StringCapture;
import pong.gui.Window;
import pong.util.Direction;

public class Game implements KeyListener{
		
	public static final int timestep = 10;
	
	private Window window;
	
	private ArrayList<PongItem> a;
	
	
	private Bordure bg;
	private Bordure bd;
	private Racket r;
	private Ball b;
	
	private boolean gameOver=false;
	
	private NetworkControler control; 
	
	private ArrayList<Player> listPlayer;
	
	
	public Game()
	{	
		a=new ArrayList<PongItem>();
		
		window=new Window(a);
		
		int windowSizeX=window.getSize().width;
		int windowSizeY=window.getSize().height-50;
		
		r= new Racket();
		b= new Ball();
		a.add(r);
		a.add(b);
		a.add(bg=new Bordure(Direction.gauche,windowSizeX,windowSizeY));
		a.add(bd=new Bordure(Direction.droite,windowSizeX,windowSizeY));
		a.add(new Bordure(Direction.haut,windowSizeX,windowSizeY));
		a.add(new Bordure(Direction.bas,windowSizeX,windowSizeY));
		
		window.addKeyListener(this);
		
		control=new NetworkControler();
		listPlayer=new ArrayList<Player>();
	}
	
	
	public void keyPressed(KeyEvent e) {
		if(gameOver)
			return;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				r.setSpeed(new Point(0,-Racket.RACKET_SPEED));
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				r.setSpeed(new Point(0,Racket.RACKET_SPEED));
				break;
			default:
				System.out.println("got press "+e);
		}
	}
	public void keyReleased(KeyEvent e) {
		if(gameOver)
			return;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				r.setSpeed(new Point(0,0));
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			r.setSpeed(new Point(0,0));
				break;
			default:
				System.out.println("got release "+e);
				
		}
	}
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()){
			case 'j':
				System.out.println("j was typed\n");
				control.connect(StringCapture.getString());
				for(Player p:listPlayer)
					a.remove(p.getRacket());
				listPlayer.clear();
				break;
		}
	}
	

	public void run()
	{
		while(true)
		{	
			if(control.haveNewConnection())
			{
				Player tmp=new Player(control.getNewConnection(b),bd);
				listPlayer.add(tmp);
				a.add(tmp.getRacket());
			}
			for(Player p:listPlayer)
			{
				p.sendNewPos(r);
				p.updatePos();
				if(p.isInGameOver(b)){
					gameOver=true;
					System.out.println("go!");
				}
			}
			
			if(gameOver){
				b.respawn();
				gameOver=false;
			}
			for (PongItem e :a){
				e.animate();
				if(e==b || e==r)
				{
					for(PongItem e2 : a)
					{
						if(e==e2)
							continue;
						Direction d=e.collision(e2);
						if(d!=Direction.aucune)
						{
							if(e2==bg)
								gameOver=true;
							else
								e.rebondir(d, window.getSize().width, window.getSize().height-50);
						}
					}
				}
			}
			window.paint();
			Window.paintScore(1,1);
			window.sleep();
		}
	}
}
