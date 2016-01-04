package pong.game;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	 */
	public void updatePos() throws EOFException, SocketException
	{
		racket.setCenter(NetworkControler.readPos(is));
	}
	
	/**
	 * envoi au jouer la position de notre raquette
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
