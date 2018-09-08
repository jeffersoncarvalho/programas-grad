/*
 * Created on 01/08/2005
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
public class AlienShot extends Thread{
	private int x;
	private int y;
	private boolean hit = false;
	private SpaceCraft sc;
	 
	/**
	 * 
	 */
	public AlienShot(int x,int y,SpaceCraft sc) {

		this.setX(x);
		this.setY(y);
		this.setPriority(Thread.MAX_PRIORITY);
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
		
		 
		while(!hit && this.y<=365)
		{
			SingletonObject.repaint();
			
			this.setY(this.getY()+5);
			this.detectColision();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Alien.aliensShots.remove(this);
	}
	
	private void detectColision()
	{
		  
			 
			if(this.sc!=null)
			{
				double dis = Math.sqrt( (this.sc.getX()-this.getX())*(this.sc.getX()-this.getX())
									   +(this.sc.getY()-this.getY())*(this.sc.getY()-this.getY()) );
				if(dis<=10)
				{
					 //System.out.println("colidiu");
					 this.sc.setDead(true);
					 hit = true;
					 SingletonPlayer.decreaseLife();
				}
			}
				
		 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object shot) {
		if(((AlienShot)shot).getId()==this.getId())
			return true;
		return false;
	}
}