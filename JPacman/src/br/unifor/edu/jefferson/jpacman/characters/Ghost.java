/*
 * Created on 08/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.characters;

import br.unifor.edu.jefferson.jpacman.characters.heuristic.Heuristic;
import br.unifor.edu.jefferson.jpacman.table.Table;
import br.unifor.edu.jefferson.jpacman.util.JPacmanConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Ghost extends Character {

	private Heuristic heuristic;
	/**
	 * @param x
	 * @param y
	 */
	public Ghost(int x, int y, int interval, Table tableGame, Heuristic heuristic) {
		super(x*JPacmanConstants.pixelJump, y*JPacmanConstants.pixelJump, interval, tableGame);
		this.heuristic = heuristic; 
	}

	/* (non-Javadoc)
	 * @see br.unifor.edu.jefferson.jpacman.characters.Character#walk()
	 */
	protected void walk() {
		
		this.heuristic.calculateNextStep(this);
	}
	
	public void run() {
		while (true)
		{
			//System.out.println("("+ this.getPosition().getX()+","+this.getPosition().getY()+")");
			if(this.tableGame.getObserver()!=null)
				this.tableGame.getObserver().updateDrawPanel();
			this.waitInterval();
			this.walk();
			 
		}
	}

}
