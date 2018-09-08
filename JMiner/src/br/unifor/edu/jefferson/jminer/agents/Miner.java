/*
 * Created on Mar 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jminer.agents;

import java.awt.Point;
import java.util.Vector;

import br.unifor.edu.jefferson.jminer.table.Table;
import br.unifor.edu.jefferson.jminer.util.JMinerConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Miner{

	protected int interval;
	protected Table table;
	protected int x,y;
	private Vector vectorPoints = new Vector(); 
	public final static int LOOKING_FOR_GOLD=0;
	public final static int GOING_TO_CC=1;
	private int minerValue = JMinerConstants.MINER_NOGOLD;
	private int status = LOOKING_FOR_GOLD;
	private Point p1 = new Point();
	private Point p2 = new Point();
	private Point p3 = new Point();
	private Point p4 = new Point();
	private boolean goldDepleted = false;
	private int clueTime = 30;
	
	 /**
	 * @param table
	 */
	public Miner(Table table,int x,int y) {
		this.x = x;
		this.y = y;
		this.table = table;
		
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void walk() {
		 
		 	 
			//apagando rastro(nao mexer)
			this.table.setMatrixPoint(x,y,0);
			
			/*LOGICA DO PASSO*/
			if(this.status==Miner.LOOKING_FOR_GOLD)
			{
				this.sortNextStep();
				if(this.foundGold())
				{
					this.minerValue = JMinerConstants.MINER_HASGOLD;
					this.status=Miner.GOING_TO_CC;
				}
			}
			else if(this.status==Miner.GOING_TO_CC)
			{
				//this.setMinorDistance();
				this.getRadarSignal();
				if(this.foundCC())
				{
					this.minerValue = JMinerConstants.MINER_NOGOLD;
					this.status=Miner.LOOKING_FOR_GOLD;
				}
			}
				
			/*LOGICA DO PASSO*/
			
			//ajustando novo passo(nao mexer)
			this.table.setMatrixPoint(x,y,this.minerValue);
			
			 			 
	}
	
	
	
	public void sortNextStep()
	{
		//caso ache um cheiro..
		int contClues =this.foundClue();
		if(contClues>0)
		{
			//ande por ali distanciando-se do CC!
			if(!this.goldDepleted)
			{		
					//System.out.println("->" + contClues);
					if(contClues>=2)
					{
						
							 this.table.setMatrixCluePoint(x,y,this.clueTime);
						this.setMajorDistance();
						return;
					}else  if (contClues==1 && this.foundCC())
					{
						
							this.table.setMatrixCluePoint(x,y,this.clueTime);
						this.getFirstClue();
						return;
					}
			}
			
			
		}
		//perdeu a pista
		//this.goldDepleted = true;
		//caso não ache cheiro
		this.vectorPoints.clear();
		//this.goldDepleted = true;
		if( x+1<JMinerConstants.height && this.table.getMatrixPoint(x+1,y) == 0)
		{
			p1.setLocation(x+1,y);
			this.vectorPoints.add(p1);
		}
		if(x -1>=0 && this.table.getMatrixPoint(x-1,y) == 0 )
		{
			p2.setLocation(x-1,y);
			this.vectorPoints.add(p2);
		}
		if(y-1>=0 && this.table.getMatrixPoint(x,y-1) == 0  )
		{
			p3.setLocation(x,y-1);
			this.vectorPoints.add(p3);
		}
		if(y+1<JMinerConstants.width && this.table.getMatrixPoint(x,y+1) == 0 )
		{
			p4.setLocation(x,y+1);
			this.vectorPoints.add(p4);
		}
		
		if(this.vectorPoints.size()==0)
			return;
		else  if(this.vectorPoints.size()==1)
		{
			Point p = (Point)this.vectorPoints.get(0);
			x = (int)p.getX();
			y = (int)p.getY();
		}
		else
		{
			int r =  (int)(Math.random()*(this.vectorPoints.size()));
			Point p = (Point)this.vectorPoints.get(r);
			x = (int)p.getX();
			y = (int)p.getY();		
		}
	}
	
	private boolean foundGold()
	{
		this.goldDepleted = false;
		if( x+1<JMinerConstants.height && (this.table.getMatrixPoint(x+1,y) >0 && this.table.getMatrixPoint(x+1,y)<11))
		{
					this.table.setMatrixPoint(x+1,y, this.table.getMatrixPoint(x+1,y) -1); 
					if( this.table.getMatrixPoint(x+1,y)==0)
						this.goldDepleted = true;
					
					return true;
		}
		if(x -1>=0 && (this.table.getMatrixPoint(x-1,y) >0 && this.table.getMatrixPoint(x-1,y) <11))
		{
					this.table.setMatrixPoint(x-1,y, this.table.getMatrixPoint(x-1,y) -1);
					if(this.table.getMatrixPoint(x-1,y)==0)
						this.goldDepleted = true;
					
					return true;
		}
		if(y-1>=0 && (this.table.getMatrixPoint(x,y-1) > 0 && this.table.getMatrixPoint(x,y-1)<11 ) )
		{
					this.table.setMatrixPoint(x,y-1, this.table.getMatrixPoint(x,y-1) -1);
					if(this.table.getMatrixPoint(x,y-1)==0)
						this.goldDepleted=true;
					return true;
		}
		if(y+1<JMinerConstants.width && (this.table.getMatrixPoint(x,y+1)>0 && this.table.getMatrixPoint(x,y+1)<11 ) )
		{
			        this.table.setMatrixPoint(x,y+1, this.table.getMatrixPoint(x,y+1) -1);
					if(this.table.getMatrixPoint(x,y+1)==0)
						this.goldDepleted = true;
					return true;
		}
		
		return false;
	}
	
	private boolean foundCC()
   {
			if( x+1<JMinerConstants.height && this.table.getMatrixPoint(x+1,y)==JMinerConstants.CC )
			{
						return true;
			}
			if(x-1 >= 0 && (this.table.getMatrixPoint(x-1,y)==JMinerConstants.CC))
			{
						return true;
			}
			if(y-1>=0 && (this.table.getMatrixPoint(x,y-1)==JMinerConstants.CC ) )
			{
						return true;
			}
			if(y+1<JMinerConstants.width && (this.table.getMatrixPoint(x,y+1)==JMinerConstants.CC ) )
			{
						return true;
			}
		
			return false;
   }
	
   private int foundClue()
	 {
	 		   int result =0; 
			   if( x+1<JMinerConstants.height && this.table.getMatrixCluePoint(x+1,y)>0 )
			   {
							result++;
			   }
			   if(x-1 >= 0 && this.table.getMatrixCluePoint(x-1,y)>0)
			   {
							result++;
			   }
			   if(y-1>=0 && this.table.getMatrixCluePoint(x,y-1)>0 )
			   {
							result++;
			   }
			   if(y+1<JMinerConstants.width && this.table.getMatrixCluePoint(x,y+1)>0 )
			   {
							result++;
			   }
		
			   return result;
	 }
   
   private boolean foundSignal()
   {
				   
				   if( x+1<JMinerConstants.height && this.table.getMatrixRadarPoint(x+1,y)>0 )
				   {
						 
							 return true;	
				   }
				   if(x-1 >= 0 && this.table.getMatrixRadarPoint(x-1,y)>0)
				   {
								x=x-1;
								return true;
				   }
				   if(y-1>=0 && this.table.getMatrixRadarPoint(x,y-1)>0 )
				   {
								y=y-1;
								return true;
				   }
				   if(y+1<JMinerConstants.width && this.table.getMatrixRadarPoint(x,y+1)>0 )
				   {
								y=y+1;
								return true;
				   }
		
				   return false;
	}
	private double distance(int x1,int y1, int x2, int y2)
	{
		return Math.sqrt(  ( x1- x2 )*( x1-x2 ) + (y1-y2)*(y1-y2) );
		//return Math.sqrt( (x1- x2)*(x1- x2) +  (y1- y2)*(y1- y2) );
	}
	
	private void setMinorDistance()
	{
		 
		double calculated =0;
		double distanceValue = Double.MAX_VALUE;
		if(!this.goldDepleted)
			this.table.setMatrixCluePoint(x,y,this.clueTime);
		int tempX = this.x;
		int tempY = this.y;
		int count = 0;
		if( x+1<JMinerConstants.height && this.table.getMatrixPoint(x+1,y) == 0)
		{
			calculated = this.distance(x+1,y,14,14);
			if(calculated<=distanceValue)
			{
				distanceValue = calculated;
				tempX = x+1;
				tempY = y;
			 
			}
			
			count ++;
		}
		if(x-1 >= 0 && this.table.getMatrixPoint(x-1,y) == 0 )
		{
		
			calculated = this.distance(x-1,y,14,14);
			if(calculated<=distanceValue)
			{
				distanceValue = calculated;
				tempX = x-1;
				tempY = y;
				 
			}
			
			count ++;
		}
		if(y-1>=0 && this.table.getMatrixPoint(x,y-1) == 0  )
		{
			calculated = this.distance(x,y-1,14,14);
			if(calculated<=distanceValue)
			{
				distanceValue = calculated;
				tempY = y-1;
				tempX=  x;
				 
			}
			
			count ++;
		}
		if(y+1<JMinerConstants.width && this.table.getMatrixPoint(x,y+1) == 0 )
		{
			calculated = this.distance(x,y+1,14,14);
			if(calculated<=distanceValue)
			{
				distanceValue = calculated;
				tempY = y+1;
				tempX = x;
				 
			}
			
			count ++;
		}
		this.x = tempX;
		this.y = tempY;
		
		 
	}
	
	private void setMajorDistance()
	{
		 
			double calculated =0;
			double distanceValue = 0;
			
			int tempX = this.x;
			int tempY = this.y;
			
			if( x+1<JMinerConstants.height && this.table.getMatrixCluePoint(x+1,y) >0)
			{
				calculated = this.distance(x+1,y,14,14);
				if(calculated>distanceValue)
				{
					distanceValue = calculated;
					tempX = x+1;
					tempY = y;
					
				}
			
				 
			}
			if(x-1 >= 0 && this.table.getMatrixCluePoint(x-1,y) > 0 )
			{
		
				calculated = this.distance(x-1,y,14,14);
				if(calculated>distanceValue)
				{
					distanceValue = calculated;
					tempX = x-1;
					tempY = y;
					
				}
			
				 
			}
			if(y-1>=0 && this.table.getMatrixCluePoint(x,y-1) > 0  )
			{
				calculated = this.distance(x,y-1,14,14);
				if(calculated>distanceValue)
				{
					distanceValue = calculated;
					tempY = y-1;
					tempX=  x;
					
				}
			 
			}
			if(y+1<JMinerConstants.width && this.table.getMatrixCluePoint(x,y+1) > 0 )
			{
				calculated = this.distance(x,y+1,14,14);
				if(calculated>distanceValue)
				{
					distanceValue = calculated;
					tempY = y+1;
					tempX = x;
					
				}
			
				 
			}
			this.x = tempX;
			this.y = tempY;
		
			
		}
		
	private void getFirstClue()
    {
				   
				   if( x+1<JMinerConstants.height && this.table.getMatrixCluePoint(x+1,y)>0 )
				   {
							x=x+1;
							 return;	
				   }
				   if(x-1 >= 0 && this.table.getMatrixCluePoint(x-1,y)>0)
				   {
								x=x-1;
								return;
				   }
				   if(y-1>=0 && this.table.getMatrixCluePoint(x,y-1)>0 )
				   {
								y=y-1;
								return;
				   }
				   if(y+1<JMinerConstants.width && this.table.getMatrixCluePoint(x,y+1)>0 )
				   {
								y=y+1;
								return;
				   }
		
				   
	}
	
	private void getRadarSignal()
	{
				if(!this.foundSignal())
				{	
					this.sortNextStep();
					return;
					
				}
				int major = -1;
				int xChosen =0;
				int yChosen =0;
				int value;
				if(!this.goldDepleted)
					this.table.setMatrixCluePoint(x,y,this.clueTime);
				if( x+1<JMinerConstants.height  )
				{
					value = this.table.getMatrixRadarPoint(x+1,y);
					 if(value>major)
					 {
					 	major = value;
					 	xChosen = x+1;
					 	yChosen = y;
					 }
				}
				if(x -1>=0 )
				{
					value = this.table.getMatrixRadarPoint(x-1,y);
					 if(value>major)
					 {
						major = value;
						xChosen = x-1;
						yChosen = y;
					 }
				}
				if(y-1>=0 )
				{
					value = this.table.getMatrixRadarPoint(x,y-1);
					 if(value>major)
					 {
						major = value;
						xChosen = x;
						yChosen = y-1;
					 }
				}
				if(y+1<JMinerConstants.width)
				{
					value = this.table.getMatrixRadarPoint(x,y+1);
					 if(value>major)
					 {
						major = value;
						xChosen = x;
						yChosen = y+1;
					 }
				}
				
				this.x= xChosen;
				this.y=yChosen;
	}
	
	/**
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param i
	 */
	public void setStatus(int i) {
		status = i;
	}
	
	

}
