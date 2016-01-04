package pong.game;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
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

/**
 * Classe game. Cette classe constitue le centre du jeu. C'est ici que tous
 * les items sont instanciés et que la boucle principale est implémentée
 * 
 * @author paul et antoine
 *
 */
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
	
	/**
	 * Constructeur il initialise toutes les données necessaire au jeu
	 */
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
	
	/**
	 * Fonction qui capture les pressions de touche du clavier et agit en conséquence
	 * @param KeyEvent e
	 */
	public void keyPressed(KeyEvent e) {
		if(gameOver)
			return;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				r.setSpeed(new Point(0,-Racket.RACKET_SPEED)); //modifie la vitesse de la raquette
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				r.setSpeed(new Point(0,Racket.RACKET_SPEED));
				break;
			default:
		}
	}
	
	/**
	 * Fonction qui capture les relachements de touche du clavier et agit en conséquence
	 * @param KeyEvent e
	 */
	public void keyReleased(KeyEvent e) {
		if(gameOver)
			return;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				r.setSpeed(new Point(0,0));//remet la vitesse de la raquette a 0
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			r.setSpeed(new Point(0,0));
				break;
			default:
				
		}
	}
	
	/**
	 * Fonction qui fait appel à la classe StringCapture si on appuie sur la touche "j"
	 * Ceci sert a rentrer l'adresse de connexion
	 * @param KeyEvent e
	 */
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()){
			case 'j':
				String addr=StringCapture.getString();
				if(addr==null)
					return;
			try {
				control.connect(addr);
			} catch (SocketException | UnknownHostException e1) {
				return;//si la connection n'as pas pu être effectué, alors on ne continue pas
			}  
				for(Player p:listPlayer)
					a.remove(p.getRacket());
				listPlayer.clear();
				break;
		}
	}
	

	/**
	 * Boucle de jeux principale
	 */
	public void run()
	{
		mainLoop:
		while(true)
		{	
			if(control.haveNewConnection()) // verifie si un joueur tente de se connecter
			{
				Player tmp=new Player(control.getNewConnection(b),bd); //ajoute le joueur sur la bordure droite
				listPlayer.add(tmp);
				a.add(tmp.getRacket());
				bs.reinit();					//reinitialise le bonus pour éviter des bug
				score1=score2=0;			//reinitialise les scores
			}
			
			
			for (PongItem e :a)				// effectue les déplacements des items
				e.animate();
				
			for(Player p:listPlayer)
			{
				try{
					p.sendNewPos(r);				//envoi et reception des nouvelles positions des raquettes
					p.updatePos();
				}catch(SocketException | EOFException | ProtocolException e){
					a.remove(p.getRacket());		//cas en cas de déconnexion ou de tricherie
					listPlayer.remove(p);			//enleve le joueur et reinitialise le score
					score1=score2=0;
					if(e instanceof ProtocolException)//si c'est une tricherie…
						System.out.println("Une tricherie a été détecté chez le joueur adverse, déconnexion");
					continue mainLoop;
				}
				
				if(p.isInGameOver(b)){				// vérifie si l'autre joueur est en game over
					gameOver=true;
					score1++;
				}
			}
			
			if(gameOver){						//vérifie si le joueur 1 est en game over
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
						if(d!=Direction.aucune)			// verifie si la balle ou la raquette sont en collision avec un item
						{
							if(e== b && e2==bg){		//si c'est la bordure gauche alors le joueur a perdu
								gameOver=true;
								score2++;
							}
							else if (e2 == bs);       // si c'est un bonus alors on ne fait rien
							else					// sinon on rebondit
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
