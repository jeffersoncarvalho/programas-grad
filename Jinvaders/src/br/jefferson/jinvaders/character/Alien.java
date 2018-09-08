/*
 * Created on 30/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.character;

import java.util.List;
import java.util.Vector;

import br.jefferson.jinvaders.main.SpaceInvadersApplet;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Alien extends Character{

	private int pixHor = 5;
	private int pixVer = 10;
	private AlienController ac;
	private boolean canShot;
	private int alienType;
	
	public static List aliensShots = new Vector();
	public Alien(AlienController ac,boolean canShot,int at)
	{
		this.ac = ac;
		this.canShot = canShot;
		this.alienType = at;
	}
	/* (non-Javadoc)
	 * @see br.jefferson.jinvaders.character.Character#moveLeft()
	 */
	public void moveLeft() {
		this.setX(this.getX()-pixHor);
	}

	/* (non-Javadoc)
	 * @see br.jefferson.jinvaders.character.Character#moveRight()
	 */
	public void moveRight() {
		  
		
		this.setX(this.getX()+pixHor);
	}

	public void moveDown(){
		this.setY(this.getY()+pixVer);
	}
	
	public void shot(SpaceCraft sc)
	{
		AlienShot shot = new AlienShot(this.getX()+3,this.getY()+8,sc);
		Alien.aliensShots.add(shot);
		shot.start();
		SpaceInvadersApplet.playAlienShot();
		 
	}
	/**
	 * @return Returns the canShot.
	 */
	public boolean isCanShot() {
		return canShot;
	}
	/**
	 * @param canShot The canShot to set.
	 */
	public void setCanShot(boolean canShot) {
		this.canShot = canShot;
	}
	/**
	 * @return Returns the alienType.
	 */
	public int getAlienType() {
		return alienType;
	}
	/**
	 * @param alienType The alienType to set.
	 */
	public void setAlienType(int alienType) {
		this.alienType = alienType;
	}
}
