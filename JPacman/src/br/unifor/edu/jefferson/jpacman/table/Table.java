/*
 * Created on 07/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.table;

 

import br.unifor.edu.jefferson.jpacman.characters.Ghost;
import br.unifor.edu.jefferson.jpacman.characters.Pacman;
import br.unifor.edu.jefferson.jpacman.characters.heuristic.SimpleHeuristic;
import br.unifor.edu.jefferson.jpacman.map.MapCreator;
//import br.unifor.edu.jefferson.jpacman.util.JPacmanConstants;
import br.unifor.edu.jefferson.jpacman.util.Observer;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Table {
	
	private int gameMatrix[][] = MapCreator.createMap("testmap.map");
	//new int[JPacmanConstants.gameDimension.width][JPacmanConstants.gameDimension.height];
	private Observer observer;
	
	private Pacman pacman;
	//teste
	public Ghost testGhost;
	
	public Table()
	{
		this.pacman = new Pacman(9,9,200,this);
		this.testGhost = new Ghost(1,1,200,this,new SimpleHeuristic(this));
		this.pacman.setPriority(Thread.MAX_PRIORITY);
	}
	/**
	 * @return the game main matrix
	 */
	public int[][] getGameMatrix() {
		return gameMatrix;
	}
	
	public synchronized void setPoint(int x, int y, int value)
	{
		this.gameMatrix[x][y] = value;
	}

	/**
	 * @return
	 */
	public Pacman getPacman() {
		return pacman;
	}

	/**
	 * @param pacman
	 */
	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
	
	 

	/**
	 * @return
	 */
	public Observer getObserver() {
		return observer;
	}

	/**
	 * @param observer
	 */
	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	
	public void startGame()
	{
		this.pacman.start();
		this.testGhost.start();
	}

}
