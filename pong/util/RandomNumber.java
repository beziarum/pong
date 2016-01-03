package pong.util;

import java.awt.Point;
import java.util.Random;

import pong.game.NetworkControler;
import pong.gui.GamePanel;

/**
 * Random number and point generator
 */
public class RandomNumber {
	private static long seed=System.currentTimeMillis();
	private static Random random=new Random(seed);
	private static long nbDoubleConsume=0;
	/**
	 * @param min
	 * @param max
	 * @return a random integer value between min and max
	 */
	public static int randomValue(int min, int max) {
		nbDoubleConsume++;
		return ((int) (random.nextDouble() * (max - min + 1)) + min);
	}

	/**
	 * @param min_x
	 * @param max_x
	 * @param min_y
	 * @param max_y
	 * @return a random Point (x,y) where min_x <= x <= max_x and min_y <= y <=
	 *         max_y
	 */
	public static Point randomPoint(int min_x, int max_x, int min_y, int max_y) {
		Point p=new Point(randomValue(min_x, max_x), randomValue(min_y, max_y));
		if(NetworkControler.invertAleaPoint)
			p=GamePanel.rotate(p);
		return p;
	}
	
	public static long getNumberOfDoubleConsumed(){
		return nbDoubleConsume;
	}
	
	public static void consumeNDouble(long n){
		for(int i=0;i<n;i++)
			random.nextDouble();
	}
	
	public static long getSeed(){
		return seed;
	}
	
	public static void setSeed(long s){
		random.setSeed(s);
		s=seed;
	}
}
