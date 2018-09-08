/*
 * Created on 29/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.character;

import java.util.LinkedList;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpaceCraft extends Character{

	private LinkedList shots;
	private boolean dead=false;
	/**
	 * 
	 */
	public SpaceCraft() {

		this.setX(180);
		this.setY(360);
		this.shots = new LinkedList();
	}
	/* (non-Javadoc)
	 * @see br.jefferson.jinvaders.character.Character#moveLeft()
	 */
	public void moveLeft() {
		// TODO Auto-generated method stub
		if(!this.dead)
			this.setX(this.getX()-5);
	}

	/* (non-Javadoc)
	 * @see br.jefferson.jinvaders.character.Character#moveDown()
	 */
	public void moveRight() {
		// TODO Auto-generated method stub
		if(!dead)
			this.setX(this.getX()+5);
	}

	/**
	 * @return Returns the shots.
	 */
	public LinkedList getShots() {
		return shots;
	}
	/**
	 * @param shots The shots to set.
	 */
	public void setShots(LinkedList shots) {
		this.shots = shots;
	}
	
	public void shot(Alien[] aliens)
	{
		if(this.shots.size()==3)
			return;
		SpaceCraftShot shot = new SpaceCraftShot(this.getX(),this.getY(),aliens,this);
		this.shots.add(shot);
		shot.start();
	}
	
	public void removeShot(SpaceCraftShot shot)
	{
		this.shots.remove(shot);
	}
	/**
	 * @return Returns the dead.
	 */
	public boolean isDead() {
		return dead;
	}
	/**
	 * @param dead The dead to set.
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
