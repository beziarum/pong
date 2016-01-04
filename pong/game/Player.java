package pong.game;

import java.awt.Point;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketException;

import pong.gui.Ball;
import pong.gui.Bordure;
import pong.gui.Racket;
import pong.util.Direction;

/**
 * La classe Player gère un joueur distant
 * @author paul et antoine
 *
 */
public class Player {
	/**
	 * InputStream vers ce joueur
	 */
	private InputStream is;
	
	/**
	 * OutputStream vers ce joueur
	 */
	private OutputStream os;
	
	/**
	 * Raquette gérée par ce joueur
	 */
	private Racket racket;
	
	/**
	 * Bordure liée à ce joueur
	 */
	private Bordure bordure;
	
	/**
	 * score de ce joueur
	 */
	private int score;
	
	private boolean isInit=false;
	
	/**
	 * Constructeur de la classe Player
	 * @param s Socket vers ce joueur
	 * @param b Bordure lié à ce joueur
	 */
	Player(Socket s,Bordure b)
	{
		score=0;
		bordure=b;
		racket=new Racket();
		try {
			is=s.getInputStream();
			os=s.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return La raquette liée à ce joueur
	 */
	public Racket getRacket()
	{
		return racket;
	}
	
	/**
	 * met à jour la position de la raquette de ce joueur
	 * @throws EOFException
	 * @throws SocketException
	 * @throws ProtocolException 
	 */
	public void updatePos() throws EOFException, SocketException, ProtocolException
	{
		Point oldPos=racket.getCenter();
		racket.setCenter(NetworkControler.readPos(is));
		if(isInit && Math.abs(oldPos.y-racket.getCenter().y)>4)//si la raquette va plus vite qu'elle ne peut le faire normalement
			throw new ProtocolException();//alors il y a un problème dans le protocole
		else
			isInit=true;
	}
	
	/**
	 * envoi au joueur la position de notre raquette
	 * @param r notre raquette
	 * @throws SocketException
	 */
	public void sendNewPos(Racket r) throws SocketException
	{
		NetworkControler.sendPos(os, r.getCenter());
	}
	
	/**
	 * @param ball la balle du pong
	 * @return vrai si ce joueur est en situation de gameover, faux sinon
	 */
	public boolean isInGameOver(Ball ball)
	{
		return bordure.collision(ball)!=Direction.aucune;
	}
	
	/**
	 * @return le score du joueur
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * met à jour le score du joueur
	 * @param s le nouveau score du joueur
	 */
	public void setScore(int s){
		score = s;
	}
}
