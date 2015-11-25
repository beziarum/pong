package pong.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pong.gui.Ball;
import pong.gui.Bordure;
import pong.gui.PongItem;
import pong.gui.Racket;
import pong.util.Direction;

public class Game extends JFrame implements KeyListener{
	
	int SIZE_PONG_X = 800;
	int SIZE_PONG_Y = 600;
	
	public static final int timestep = 10;
	
	private ArrayList<PongItem> a;
	
	private Bordure bh;
	private Bordure bg;
	private Bordure bd;
	private Bordure bb;
	
	private Racket r;
	private Ball b;
	
	protected Image buffer;
	protected Graphics gContext;
	protected Graphics box;
	
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
		box = buffer.getGraphics();
		a=new ArrayList<PongItem>();
		r= new Racket();
		b= new Ball();
		a.add(r);
		a.add(b);
		a.add(bg=new Bordure(Direction.gauche,SIZE_PONG_X,SIZE_PONG_Y));
		a.add(bd=new Bordure(Direction.droite,SIZE_PONG_X,SIZE_PONG_Y));
		a.add(bh=new Bordure(Direction.haut,SIZE_PONG_X,SIZE_PONG_Y));
		a.add(bb=new Bordure(Direction.bas,SIZE_PONG_X,SIZE_PONG_Y));
		this.addKeyListener(this);
	}
	
	
	/*private void limit(PongItem i){
		if (i.getPosition().y < 0 )
			i.rebondir(Direction.bas,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().y > SIZE_PONG_Y - i.getHeight())
			i.rebondir(Direction.haut,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().x > SIZE_PONG_X - i.getWidth())
			i.rebondir(Direction.gauche,SIZE_PONG_X,SIZE_PONG_Y);
		if(i.getPosition().x < 0)
			i.rebondir(Direction.droite,SIZE_PONG_X,SIZE_PONG_Y);
	}*/
	
	public void keyPressed(KeyEvent e) {
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
	public void keyTyped(KeyEvent e) { }
	

	public void run()
	{
		while(true)
		{
			gContext.setColor(backgroundColor);
			gContext.fillRect(0, 0, SIZE_PONG_X, SIZE_PONG_Y);
			//box.setColor(backgroundColor.red);
			//box.drawString("Game Over !", 50, 50);
			//box.drawRect(b.getHitbox().getPos().x-2, b.getHitbox().getPos().y-2, b.getHitbox().getWidth()+2, b.getHitbox().getHeight()+2);
			//box.drawRect(r.getHitbox().getPos().x-2, r.getHitbox().getPos().y-2, r.getHitbox().getWidth()+2, r.getHitbox().getHeight()+2);*/
			/*for (PongItem e : a) {
				if(e==null)
					System.out.println("ceci este ");
				limit(e);
				e.animate();
				e.paint(gContext);
			}*/
			for (PongItem e :a){
				e.animate();
				e.paint(gContext);
				if (e == b)
					continue;
				Direction d = b.collision(e);
				if(d != Direction.aucune){
					System.out.println("caca"+d);
					b.rebondir(d,SIZE_PONG_X,SIZE_PONG_Y);
				}
			}
			//paint(box);
			repaint();
			/*if (limite(b))
				gameOver();*/
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
			box.setColor(backgroundColor.red);
			box.drawString("Game Over !", 50, 50);
		}
	}
}
