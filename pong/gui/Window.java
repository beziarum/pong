package pong.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private static boolean printFps=false;
	private static final int SCORE_HEIGHT = 50;
	public static final Dimension WINDOW_SIZE=new Dimension(GamePanel.WINDOW_SIZE.width, GamePanel.WINDOW_SIZE.height + SCORE_HEIGHT);
	
	private static final int timestep = 10;
	
	private GamePanel pan;
	private JLabel scorePanel;
	
	private long time;
	private long delay=0;
	
	
	public Window(ArrayList<PongItem> a){
		setMinimumSize(WINDOW_SIZE);
		setTitle("Pong");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		time=System.currentTimeMillis();
		
		pan=new GamePanel(a);
		scorePanel=new JLabel("Score = 0/0",SwingConstants.CENTER);
		
		getContentPane().add(pan);
		getContentPane().add(scorePanel,BorderLayout.PAGE_END);
		
		pack();
		setVisible(true);
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
		repaint();
	}
	
	public void sleep(){
		try {
			long newtime=System.currentTimeMillis();
			delay=0;
			Thread.sleep(timestep-delay);
			time=System.currentTimeMillis();
			delay=time-newtime;
			System.out.println(delay);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void updateScore(int s1, int s2){
		StringBuffer text=new StringBuffer();
		text.append("Score = ");
		text.append(s1);
		text.append('/');
		text.append(s2);
		scorePanel.setText(text.toString());
	}
	
	public void updateScore(int s){
		scorePanel.setText("Echecs : "+s);
	}
}
