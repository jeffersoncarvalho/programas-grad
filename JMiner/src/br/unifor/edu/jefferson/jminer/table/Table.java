/*
 * Created on Mar 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jminer.table;

import br.unifor.edu.jefferson.jminer.agents.ControlCenter;
import br.unifor.edu.jefferson.jminer.gui.DrawPanel;
import br.unifor.edu.jefferson.jminer.util.JMinerConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Table {

	 private int[][] matrix = new int[JMinerConstants.width][JMinerConstants.height];
	 private int[][] matrixClue = new int[JMinerConstants.width][JMinerConstants.height];
	private int[][] matrixRadar = new int[JMinerConstants.width][JMinerConstants.height];
	 private DrawPanel drawPanel;
	 private ControlCenter cc;
	 private int interval = 1000;
	 private int numMiners = 2;
	 private int range=8;
	/**
	 * @return Returns the range.
	 */
	public int getRange() {
		return range;
	}
	/**
	 * @param range The range to set.
	 */
	public void setRange(int range) {
		range = (range>15)?15:range;
		this.range = range;
	}
	 public Table(DrawPanel drawPanel)
	 {
	 	 this.drawPanel= drawPanel;
	 	 this.createGold();
	 	 this.createCC();
	 	 
	 }
	 
	 public void setMatrixPoint(int x, int y, int value)
	 {
	 	//teste de integridade!!
	 	x = (x>=JMinerConstants.height)?JMinerConstants.height-1:x;
	 	x = (x<0)?0:x;
	 	y = (y>=JMinerConstants.width)?JMinerConstants.width-1:y;
	 	y = (y<0)?0:y;
	 	
	 	this.matrix[y][x] = value;
	 }
	 
	 public int getMatrixPoint(int x,int y)
	 {
		
	 	return this.matrix[y][x];
	 }
	 
	 public void setMatrixCluePoint(int x, int y, int value)
	 {
	 	//teste de integridade!!
	 	x = (x>=JMinerConstants.height)?JMinerConstants.height-1:x;
	 	x = (x<0)?0:x;
	 	y = (y>=JMinerConstants.width)?JMinerConstants.width-1:y;
	 	y = (y<0)?0:y;
	 	
	 	this.matrixClue[y][x] = value;
	 }
	 
	 public int getMatrixCluePoint(int x,int y)
	 {
	 	return this.matrixClue[y][x];
	 }
	 
	public int getMatrixRadarPoint(int x,int y)
	{
		return this.matrixRadar[y][x];
	}
	 
	 private void createCC()
	 {
	 	cc = new ControlCenter(this,14,14,this.interval,this.drawPanel,this.numMiners);
	 	this.setMatrixPoint(cc.getX(),cc.getY(),JMinerConstants.CC);
	 	ControlCenter.initRadar(14,14,this.matrixRadar,this.range);
		
	 }
	 
	 private void createGold()
	 {
	 	 
	 	 
	 	this.setMatrixPoint(5,6,5);
		this.setMatrixPoint(10,4,5);
		//this.setMatrixPoint(15,16,5);
		this.setMatrixPoint(8,9,5);
		this.setMatrixPoint(12,7,5);
		//this.setMatrixPoint(12,10,5);
		this.setMatrixPoint(20,15,5);
		this.setMatrixPoint(1,8,5);
		this.setMatrixPoint(28,24,5);
		this.setMatrixPoint(25,26,5);
		this.setMatrixPoint(15,8,5);
		this.setMatrixPoint(10,18,5);
		this.setMatrixPoint(14,2,5);
		this.setMatrixPoint(9,9,5);
		this.setMatrixPoint(5,14,5);
		this.setMatrixPoint(7,77,5);
		this.setMatrixPoint(15,21,5);
		this.setMatrixPoint(0,8,5);
		this.setMatrixPoint(0,20,5);
		this.setMatrixPoint(16,27,5);
		this.setMatrixPoint(25,24,5);
		this.setMatrixPoint(17,28,5);
		
	 
	 }
	 
	 public void startTable()
	 {
	 	 
		this.cc.setNumMiners(this.numMiners);
		 
		 this.cc.setInterval(this.interval);
		 ControlCenter.initRadar(14,14,this.matrixRadar,this.range);
		 for(int i=0;i<30;i++)
			{
				for(int j=0;j<30;j++)
					System.out.print(" " + matrixRadar[j][i]); 
				System.out.println("");
			}
		//this.createGold();
	 	cc.startCC();
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

	/**
	 * @return
	 */
	public int getNumMiners() {
		return numMiners;
	}

	/**
	 * @param i
	 */
	public void setNumMiners(int i) {
		numMiners = i;
	}
	
	public void stopTable()
	{
		 matrix = new int[JMinerConstants.width][JMinerConstants.height];
		  matrixClue = new int[JMinerConstants.width][JMinerConstants.height];
		 matrixRadar = new int[JMinerConstants.width][JMinerConstants.height];
		
		 cc.stopCC();
		 this.cc = null;
		 this.createCC();
		 this.createGold();
		 this.drawPanel.repaint();
	}
}
