/*
 * Created on 29/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.character;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class Character {
	
	private int x;
	private int y;
	
	public abstract void moveLeft();
	public abstract void moveRight();
	
	/**
	 * @return Returns the x.
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x The x to set.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return Returns the y.
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y The y to set.
	 */
	public void setY(int y) {
		this.y = y;
	}
}
