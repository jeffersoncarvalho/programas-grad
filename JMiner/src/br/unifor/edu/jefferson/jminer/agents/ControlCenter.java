/*
 * Created on Mar 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jminer.agents;

import java.util.Vector;

import br.unifor.edu.jefferson.jminer.gui.DrawPanel;
import br.unifor.edu.jefferson.jminer.table.Table;
import br.unifor.edu.jefferson.jminer.util.JMinerConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ControlCenter extends Agent{
	
	private Vector minersList;
	private int numMiners =1;
	 
	/**
	 * @param table
	 * @param x
	 * @param y
	 * @param interval
	 * @param drawPanel
	 */
	public ControlCenter(Table table, int x, int y, int interval, DrawPanel drawPanel, int numMiners) {
		super(table, x, y, interval, drawPanel);
		this.setPriority(Thread.MAX_PRIORITY);
		this.numMiners = numMiners;
		
		
		 
	}
	
	 private void createMiners()
	 {
	 	 for(int i=0;i<this.numMiners;i++)
	 	 {
			Miner m = new Miner(this.table, 15,15);
				this.table.setMatrixPoint(15,15,JMinerConstants.MINER_NOGOLD);
			this.minersList.add(m);
	 	 }
	 
	 }
	 
	 
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		while(!stop)
		{
			this.updateClue();
			for(int i=0; i<this.minersList.size(); i++)
			{
				Miner m = (Miner)this.minersList.get(i);
				m.walk();
			}
			this.waitInterval();
			this.drawPanel.repaint();
			
		}
		
	}

	private void updateClue()
	{
			int clue = 0;
					for(int i=0;i<JMinerConstants.height;i++)
						for(int j=0;j<JMinerConstants.width;j++)
						{	
						  
   				 
							 //clue
							clue = table.getMatrixCluePoint(i,j);
							if(clue>0)
							{
							
							
								this.table.setMatrixCluePoint(i,j,table.getMatrixCluePoint(i,j)-1);	
					
							}
						}
	  }
	  
	  public void startCC()
	  {
			this.minersList = new Vector(numMiners);
	  		this.createMiners();
	  		this.start();	
	  }
 

	/**
	 * @return
	 */
	public int getNumMiners() {
		return numMiners;
	}

	public void stopCC()
	{
		this.stop = true; 
		
	}

	/**
	 * @param i
	 */
	public void setNumMiners(int i) {
		numMiners = i;
	}
	
	private static void square(int x, int y, int w, int h, int value, int[][] mat)
	{
		for(int i=x;i<x+w;i++)
		{
			mat[y][i] = value;
			mat[y+h-1][i] = value;
		}
		
		for(int i=y;i<y+h;i++)
		{
			mat[i][x] = value;
			mat[i][x+w-1] = value;
		}	
	}
	
	public static void initRadar(int xParam,int yParam,int[][] mat, int range)
	{
		range = (range>15)?15:range;
		int value=100;
		int w =1;
		int h =1;
		for(int i=0;i<range;i++)
		{
			ControlCenter.square(xParam,yParam,w,h,value,mat);
			xParam-=1;
			yParam-=1;
			value-=1;
			w+=2;
			h+=2;
		}
	}
	
	 public static void main(String[] args) {
		
		int[][] mat = new int[30][30];
		ControlCenter.initRadar(14,14,mat,3000);
		for(int i=0;i<30;i++)
		{
			for(int j=0;j<30;j++)
				System.out.print("  " + mat[j][i]); 
			System.out.println("");
		}
		
		
	} 
	
}
