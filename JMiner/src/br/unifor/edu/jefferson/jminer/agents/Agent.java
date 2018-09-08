/*
 * Created on Mar 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jminer.agents;

import br.unifor.edu.jefferson.jminer.gui.DrawPanel;
import br.unifor.edu.jefferson.jminer.table.Table;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Agent extends Thread{
	protected int interval;
	protected Table table;
	protected int x,y;
	protected DrawPanel drawPanel;
	protected boolean stop = false;
	public Agent(Table table,int x,int y, int interval,DrawPanel drawPanel)
	{
		this.x = x;
		this.y = y;
		this.table = table;
		this.drawPanel= drawPanel;
		this.interval = interval;
		
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
	/**
	 * @return
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @param i
	 */
	public void setInterval(int i) {
		interval = i;
	}

}
