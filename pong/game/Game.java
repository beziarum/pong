package pong.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import pong.gui.Ball;
import pong.gui.Bordure;
import pong.gui.PongItem;
import pong.gui.Racket;
import pong.util.Direction;

public class Game extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	int SIZE_PONG_X = 800;
	int SIZE_PONG_Y = 600;
	
	public static final int timestep = 10;
	
	private ArrayList<PongItem> a;
	
	
	private Bordure bg;
	private Racket r;
	private Ball b;
	
	private boolean keyIsPressed=false;
	
	protected Image buffer;
	protected Graphics gContext;
	
	private static final Color backgroundColor = new Color(0xFF, 0x40, 0);
	
	public Game()
	{
		setPreferredSize(new Dimension(SIZE_PONG_X, SIZE_PONG_Y));
		pack();
		setTitle("Pong");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		buffer=createImage(SIZE_PONG_X,SIZE_PONG_Y);
		if (buffer == null)
			throw new RuntimeException("Could not instanciate graphics");
		gContext = buffer.getGraphics();
		a=new ArrayList<PongItem>();
		r= new Racket();
		b= new Ball();
		a.add(r);
		a.add(b);
		a.add(bg=new Bordure(Direction.gauche,SIZE_PONG_X,SIZE_PONG_Y));
		a.add(new Bordure(Direction.droite,SIZE_PONG_X,SIZE_PONG_Y));
		a.add(new Bordure(Direction.haut,SIZE_PONG_X,SIZE_PONG_Y));
		a.add(new Bordure(Direction.bas,SIZE_PONG_X,SIZE_PONG_Y));
		this.addKeyListener(this);
	}
	
	
	public void keyPressed(KeyEvent e) {
		if(keyIsPressed)
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
		keyIsPressed=true;
	}
	public void keyReleased(KeyEvent e) {
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
		keyIsPressed=false;
	}
	public void keyTyped(KeyEvent e) { }
	

	public void run()
	{
		while(true)
		{
			gContext.setColor(backgroundColor);
			gContext.fillRect(0, 0, SIZE_PONG_X, SIZE_PONG_Y);
			
			for (PongItem e :a){
				e.animate();
				e.paint(gContext);
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
								gameOver();
							else
								e.rebondir(d, SIZE_PONG_X, SIZE_PONG_Y);
						}
					}
				}
			}
			repaint();
			try {
				Thread.sleep(timestep);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(buffer, 0, 0, this);
	}
	
	public void gameOver(){
		boolean replay=false;
		while(!replay){
			gContext.setColor(Color.blue);
			gContext.setFont(new Font("Courier", Font.BOLD, 30));
			gContext.drawString("Game Over !", 300, 250);
			repaint();
			try {
				Thread.sleep(timestep);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
