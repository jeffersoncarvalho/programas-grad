/*
 * Created on 12/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.characters.heuristic;

import br.unifor.edu.jefferson.jpacman.characters.Ghost;
import br.unifor.edu.jefferson.jpacman.table.Table;
import br.unifor.edu.jefferson.jpacman.util.JPacmanConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SimpleHeuristic implements Heuristic {
	
	private Table tableGame;
		
	public SimpleHeuristic(Table tableGame)
	{
		this.tableGame = tableGame;
	}

	/* (non-Javadoc)
	 * @see br.unifor.edu.jefferson.jpacman.characters.heuristic.Heuristic#nextStep(java.awt.Dimension, java.awt.Dimension)
	 */
	public void calculateNextStep(Ghost me) {
		
		 
		int jump = + JPacmanConstants.pixelJump;
		int x = me.getX();//(int)me.getPosition().getX()/JPacmanConstants.pixelJump;
		int y = me.getY();//(int)me.getPosition().getY()/JPacmanConstants.pixelJump;
		
		
		int []directions = {JPacmanConstants.DOWN,JPacmanConstants.UP,JPacmanConstants.LEFT,JPacmanConstants.RIGHT};
		 
		
		boolean isNextStepAWall = false;
		
		do{
			isNextStepAWall = false;
			switch(me.getDirection())
			{
			
				case JPacmanConstants.DOWN:
					if(this.tableGame.getGameMatrix()[y+1][x] == JPacmanConstants.WALL)
						isNextStepAWall = true;
					else
						me.getPosition().setLocation(me.getPosition().getX(),me.getPosition().getY()+jump);	 
				break;
				case JPacmanConstants.UP:
					if(this.tableGame.getGameMatrix()[y-1][x] == JPacmanConstants.WALL)
						isNextStepAWall = true;
					else
						me.getPosition().setLocation(me.getPosition().getX(),me.getPosition().getY()-jump);
				break;
				case JPacmanConstants.LEFT:
					if(this.tableGame.getGameMatrix()[y][x-1] == JPacmanConstants.WALL)
						isNextStepAWall = true;	
					else
						me.getPosition().setLocation(me.getPosition().getX()-jump,me.getPosition().getY());
				break;
				case JPacmanConstants.RIGHT:
					if(this.tableGame.getGameMatrix()[y][x+1] == JPacmanConstants.WALL)
						isNextStepAWall = true;	
					else
						me.getPosition().setLocation(me.getPosition().getX()+jump,me.getPosition().getY());
				break;
			}
			
			if(isNextStepAWall)
				me.setDirection(this.sortDirections(directions));
			
		}while(isNextStepAWall);
		
		
		 
	}
	
	private int sortDirections(int[] directions)
	{
		
		int any = (int)(Math.random()*4);
		return directions[any];
	}

}
