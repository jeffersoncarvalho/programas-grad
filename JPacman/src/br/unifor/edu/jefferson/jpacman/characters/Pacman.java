/*
 * Created on 08/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.characters;

import br.unifor.edu.jefferson.jpacman.table.Table;
import br.unifor.edu.jefferson.jpacman.util.JPacmanConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Pacman extends Character{

	/**
	 * @param x
	 * @param y
	 */
	public Pacman(int x, int y, int interval,Table tableGame) {
		super(x*JPacmanConstants.pixelJump, y*JPacmanConstants.pixelJump, interval,tableGame);
	}

	/* (non-Javadoc)
	 * @see br.unifor.edu.jefferson.jpacman.characters.Character#walk()
	 */
	protected void walk() {
		
	    int jump = + JPacmanConstants.pixelJump;
		int x = (int)this.position.getX()/JPacmanConstants.pixelJump;
		int y = (int)this.position.getY()/JPacmanConstants.pixelJump;
		switch(this.direction)
		{
			
			case JPacmanConstants.DOWN:
			    if(this.tableGame.getGameMatrix()[y+1][x] == JPacmanConstants.WALL)
					return;
				this.position.setLocation(this.position.getX(),this.position.getY()+jump);
			break;
			case JPacmanConstants.UP:
				if(this.tableGame.getGameMatrix()[y-1][x] == JPacmanConstants.WALL)
					return;
				this.position.setLocation(this.position.getX(),this.position.getY()-jump);
			break;
			case JPacmanConstants.LEFT:
				if(this.tableGame.getGameMatrix()[y][x-1] == JPacmanConstants.WALL)
					return;
				this.position.setLocation(this.position.getX()-jump,this.position.getY());
			break;
			case JPacmanConstants.RIGHT:
				if(this.tableGame.getGameMatrix()[y][x+1] == JPacmanConstants.WALL)
					return;
				this.position.setLocation(this.position.getX()+jump,this.position.getY());
			break;
		}
		
		 
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (true)
		{
			/*if(this.tableGame.getObserver()!=null)
				this.tableGame.getObserver().updateDrawPanel();*/
			this.waitInterval();
			this.walk();
			this.isEating();
		}
	}
	
	private boolean isEating()
	{
		int x = (int)this.position.getX()/JPacmanConstants.pixelJump;
		int y = (int)this.position.getY()/JPacmanConstants.pixelJump;
		if(this.tableGame.getGameMatrix()[y][x] == JPacmanConstants.FOOD)
		{ 
			this.tableGame.getGameMatrix()[y][x] = JPacmanConstants.EMPTY;
		}
		return false;
	}

}
