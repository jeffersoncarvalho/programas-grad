package tetris.table;


import java.awt.Color;
import tetris.gui.DrawPaneForNext;
import tetris.gui.DrawPanel;


public class Table extends TableRules 
{
	
	
	
	
	public Table()
	{
		super();
		drawPanel = new DrawPanel(tableGame,this.piece.getBody());
		drawPaneForNext = new DrawPaneForNext(this.nextPiece.getID());
		drawPaneForNext.repaint();	
	}
	
	/**
	 * M�todo primordial do tabuleiro.
	 */
	public void run()
	{
		 Thread t = new Thread(){
	      public void run(){
	      	  
				while(true && !lost) 
				{
					if(!mustStopFall())
						piece.fall();
					else
					{
						feedMatrix();
						
						//teste
						int lineToBeDestroyed = testLine();
						int point = 0;
						while(lineToBeDestroyed != -1)
						{
						  remountMatrix(lineToBeDestroyed);
						  lineToBeDestroyed = testLine();
						  point++ ;
					    }
						
						
						
						piece.restartPiece();
						piece = nextPiece;
						nextPiece = createPiece();
						drawPanel.updatePiece(piece.getBody());//para o painel de desenho n�o perder a refer�ncia da pe�a nova
						drawPaneForNext.updatePiece(nextPiece.getID());
						drawPaneForNext.repaint();
					}
					
					try {
						Thread.sleep(timeDelay);
				    }
				    catch (Exception ex) {
				    }
				    drawPanel.repaint();
				}//while
				
				boundaryFill(5,5,new Color(158,231,50));
				drawPanel.setLost(true);
				
		  }//run
		};//Thread
		t.start();
		t.setPriority(Thread.MAX_PRIORITY);
   }
	
	
	
	/**
	 *Retorna o painel de desenho
	 */
	public DrawPanel getDrawPanel()
	{
		return this.drawPanel;
	}
	
	/**
	 *Retorna o painel de desenho para next
	 */
	public DrawPaneForNext getDrawPaneForNext()
	{
		return this.drawPaneForNext;
	}
	
	/**
	 * Faz a pe�a andar de lado de acordo com a dire��o.
	 */
	public void strafePiece(int dir)
	{
		if(this.canStrafe(dir))
		 piece.strafe(dir);
	}
	
	/**
	 * Faz a pe�a em jogo rotacionar de acordo com a dire��o passada.
	 */
	public void rotatePiece(int dir)
	{
		if(this.canRotate(dir))
		 piece.rotate(dir);
	}
	
	/**
	 * repaint
	 */
	public void repaintPanel()
	{
		this.drawPanel.repaint();
	}
	
	/**
	 *
	 */
	public void fallAtOnce()
	{
		while(!this.mustStopFall())
		 piece.fall();		
	}
	
	public void boundaryFill(int x, int y, Color fill)
	{

	   	   if((y>=0) && (y<this.tableGame.length) &&  //20
	   	      (x>=0) && (x<this.tableGame[0].length))  //10
	   		if((this.tableGame[y][x] == null) || (!this.tableGame[y][x].equals(fill)))
	   		{
	   			tableGame[y][x] = fill;
	   			//   g2d.fill3DRect(a*20,b*20,20,20,true);
	   			try{Thread.sleep(10);}catch(Exception e){}
	   			//System.out.println (x +" "+ y);
		     	this.drawPanel.repaint();
		     	
		     	this.boundaryFill(x+1,y,fill);
		     	this.boundaryFill(x-1,y,fill);
		     	this.boundaryFill(x,y+1,fill);
		     	this.boundaryFill(x,y-1,fill);
		     	
		     	this.boundaryFill(x+1,y-1,fill);
		     	this.boundaryFill(x-1,y+1,fill);
		     	this.boundaryFill(x-1,y-1,fill);
		     	this.boundaryFill(x-1,y+1,fill);
	   		}
	}
}