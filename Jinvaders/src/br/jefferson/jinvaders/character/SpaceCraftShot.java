/*
 * Created on 29/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.character;

import br.jefferson.jinvaders.util.SingletonObject;
import br.jefferson.jinvaders.util.SingletonPlayer;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpaceCraftShot extends Thread{
	
	private int x;
	private int y;
	private Alien[] aliens;
	private boolean hit = false;
	private SpaceCraft sc;
	 
	/**
	 * 
	 */
	public SpaceCraftShot(int x,int y,Alien[] aliens, SpaceCraft sc) {

		this.setX(x);
		this.setY(y);
		this.setPriority(Thread.MAX_PRIORITY);
		this.aliens = aliens;
		this.sc = sc;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		
		 
		while(!hit && this.y>=0)
		{
			SingletonObject.repaint();
			
			this.setY(this.getY()-5);
			this.detectColision();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.sc.removeShot(this);
	}
	
	private void detectColision()
	{
		for(int i=0;i<aliens.length;i++)
		{
			Alien alien = this.aliens[i];
			if(alien!=null)
			{
				double dis = Math.sqrt( (alien.getX()-this.getX())*(alien.getX()-this.getX())
									   +(alien.getY()-this.getY())*(alien.getY()-this.getY()) );
				if(dis<15)
				{
					//System.out.println("colidiu");
					aliens[i] = null;
					hit = true;
					AlienController.setDeads(AlienController.getDeads()+1);
					this.sc.removeShot(this);
					SingletonPlayer.increasePontuation(0);
				}
				
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object shot) {
		if(((SpaceCraftShot)shot).getId()==this.getId())
			return true;
		return false;
	}
}
