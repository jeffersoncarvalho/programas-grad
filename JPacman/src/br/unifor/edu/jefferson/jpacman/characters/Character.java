/*
 * Created on 08/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.characters;

import java.awt.Point;

import br.unifor.edu.jefferson.jpacman.table.Table;
import br.unifor.edu.jefferson.jpacman.util.JPacmanConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class Character extends Thread {
	
	protected Point position;
	protected int interval;
	protected int direction = JPacmanConstants.RIGHT;
	protected Table tableGame; 
	
	public Character(int x, int y, int interval, Table tableGame)
	{
		this.interval = interval;
		this.position = new Point(x,y);
		this.tableGame = tableGame;
		
	}

	/**
	 * Muda o estado de position...
	 *
	 */
	protected abstract void walk();
	/**
	 * @return
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param point
	 */
	public void setPosition(Point point) {
		position = point;
	}

	protected void waitInterval()
	{
		try {
			Thread.sleep(this.interval);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param i
	 */
	public void setDirection(int i) {
		 
			direction = i;
	}
		
	public int getX()
	{
		return (int)this.getPosition().getX()/JPacmanConstants.pixelJump;
	}
	
	public int getY()
	{
		return (int)this.getPosition().getY()/JPacmanConstants.pixelJump;
	}
}
