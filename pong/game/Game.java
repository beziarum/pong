package pong.game;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.net.SocketException;
import java.util.ArrayList;

import pong.gui.Ball;
import pong.gui.BonusSpeed;
import pong.gui.Bordure;
import pong.gui.GamePanel;
import pong.gui.PongItem;
import pong.gui.Racket;
import pong.gui.StringCapture;
import pong.gui.Window;
import pong.util.Direction;

public class Game implements KeyListener{
	
	private Window window;
	
	private ArrayList<PongItem> a;
	
	private int score1 =0,score2 =0;
	
	private Bordure bg;
	private Bordure bd;
	private Racket r;
	private Ball b;
	private BonusSpeed bs;
	
	private boolean gameOver=false;
	
	private NetworkControler control; 
	
	private ArrayList<Player> listPlayer;
	
	
	public Game()
	{	
		a=new ArrayList<PongItem>();
		
		window=new Window(a);
		
		int windowSizeX=GamePanel.WINDOW_SIZE.width;
		int windowSizeY=GamePanel.WINDOW_SIZE.height;
		
		r= new Racket();
		b= new Ball();
		a.add(r);
		a.add(b);
		a.add(bg=new Bordure(Direction.gauche,windowSizeX,windowSizeY));
		a.add(bd=new Bordure(Direction.droite,windowSizeX,windowSizeY));
		a.add(new Bordure(Direction.haut,windowSizeX,windowSizeY));
		a.add(new Bordure(Direction.bas,windowSizeX,windowSizeY));
		a.add(bs=new BonusSpeed(b));
		
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
		mainLoop:
		while(true)
		{	
			if(control.haveNewConnection())
			{
				Player tmp=new Player(control.getNewConnection(b),bd,control.lastHasBeenAsked());
				listPlayer.add(tmp);
				a.add(tmp.getRacket());
				bs.reinit();
				score1=score2=0;
			}
			for(PongItem e:a)
				e.animate();
			if(!listPlayer.isEmpty()){
				System.out.println(b.getCenter());
				System.out.println(b.getSpeed());
			}
			for(Player p:listPlayer)
			{
				try{
					p.update(r,b);
				}catch(SocketException | EOFException  e){
					a.remove(p.getRacket());
					listPlayer.remove(p);
					score1=score2=0;
					continue mainLoop;
				}
				
				if(p.isInGameOver(b)){
					gameOver=true;
					score1++;
				}
			}
			
			if(gameOver){
				b.respawn();
				bs.reinit();
				gameOver=false;
			}
			bs.process();
			for (PongItem e :a){
				if(e==b || e==r)
				{
					for(PongItem e2 : a)
					{
						if(e==e2)
							continue;
						Direction d=e.collision(e2);
						if(d!=Direction.aucune)
						{
							if(e== b && e2==bg){
								gameOver=true;
								score2++;
							}
							else if (e2 == bs);
							else
								e.rebondir(d, window.getSize().width, window.getSize().height-50);
						}
					}
				}
			}
			window.paint();
			if (!listPlayer.isEmpty())
				window.updateScore(score1,score2);
			else
				window.updateScore(score2);
			window.sleep();
		}
	}
}
