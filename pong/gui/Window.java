package pong.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static boolean printFps=false;
	
	private ArrayList<PongItem> a;
	
	public static final Dimension WINDOW_SIZE=new Dimension(800,600);
	
	public static final int timestep = 10;
	
	private Color backgroundColor=new Color(0xFF, 0x40, 0);
	
	protected Image buffer;
	protected static Graphics gContext;
	
	protected GamePanel pan;
	protected JPanel mainPanel;
	protected JLabel scorePanel;
	private long time;
	private long delay=0;
	
	
	public Window(ArrayList<PongItem> a){
		pan=new GamePanel(a);
		mainPanel=new JPanel();
		scorePanel=new JLabel("Score = 0/0",SwingConstants.CENTER);
		pan.setMinimumSize(WINDOW_SIZE);
		setPreferredSize(WINDOW_SIZE);
		setTitle("Pong");
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(pan);
		getContentPane().add(scorePanel,BorderLayout.PAGE_END);
		//add(mainPanel);
		pack();
		setVisible(true);
		//buffer=createImage(WINDOW_SIZE.width,WINDOW_SIZE.height);
		//if (buffer == null)
		//	throw new RuntimeException("Could not instanciate graphics");
		//gContext = buffer.getGraphics();
		//gContext = pan.getGraphics();
		time=System.currentTimeMillis();
		this.a=a;
		//gContext.drawString(" ", 300, 250);
	}

	public Dimension getSize(){
		return (Dimension)WINDOW_SIZE.clone();
	}
	
	public void paint(){
		if(printFps)
		{
			long fps;
			if(delay!=0)
				fps=1000/delay;
			else
				fps=-1;
			System.out.println(fps);
		}
		//gContext.setColor(Color.WHITE);
		//gContext.fillRect(0, WINDOW_SIZE.height-50, WINDOW_SIZE.width, WINDOW_SIZE.height);
		/*gContext.setColor(backgroundColor);
		gContext.fillRect(0, 0, WINDOW_SIZE.width, WINDOW_SIZE.height-50);
		for(PongItem e : a)
			e.paint(gContext);
		//repaint();*/
		repaint();
	}
	/*
	public void paint(Graphics g)
	{
		g.drawImage(buffer, 0, 0, this);
		pack();
	}*/
	
	public void sleep(){
		try {
			long newtime=System.currentTimeMillis();
			//System.out.println(delay);
			delay=0;
			Thread.sleep(timestep-delay);
			time=System.currentTimeMillis();
			delay=time-newtime;
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void paintScore(int s1, int s2){
		//gContext.setColor(Color.BLACK);
		scorePanel.setText("Score = " +s1+"/"+s2);
		//gContext.drawString("Score = " +s1+"/"+s2 , 350, 575);//a amélioré
	}
}
