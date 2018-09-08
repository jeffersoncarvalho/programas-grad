package tetris.table;

import java.util.ArrayList;
import java.awt.Color;

import tetris.piece.*;
import tetris.gui.DrawPaneForNext;
import tetris.gui.DrawPanel;
import tetris.util.Player;
import tetris.util.Point;
import tetris.util.Direction;

public class TableRules
{
	protected DrawPanel drawPanel;
	protected DrawPaneForNext drawPaneForNext;
	protected int timeDelay = 300 ;
	protected Piece piece, nextPiece;
	protected Color[][] tableGame;
	protected boolean lost = false;
	private Piece pt,pl,ps,pn,pi;
	final int ONE = 1;
	private boolean change = true;
	protected Player player;
	
	public TableRules()
	{
		pt = new PieceT();
		pl = new PieceL();
		ps = new PieceSquare();
		pn = new PieceN();
		pi = new PieceI();
		this.piece = createPiece();
		this.nextPiece = createPiece();
		tableGame = new Color[20][10];
	}
	
	/**
	 *Crianda peça
	 */
	protected Piece createPiece()
	{
		int r = (int)(Math.random()*5);
		//System.out.println (r);
		if(r == 1)
		 return pl;
		else
		 if(r == 2)
			return pt;
		  else
		   if(r == 3)
			return ps;
		   else
		    if( r == 4)
		     return pi;
		return pn;
		
		
	}
	
	/**
	 *Define quando uma peça deve parar de cair
	 */
	protected boolean mustStopFall()
	{
		ArrayList body = piece.getBody();
		for(int i=0; i<body.size(); i++)
		{
			Point p = (Point)body.get(i);
			
			//**testa com a parede de baixo
			if( ( (p.getY()+1) == tableGame.length ) 
			  )
			 return true;
			
			//**testa com a matrix
			if(p.getY()+1>=0)
			 if(tableGame[ p.getY() + 1 ][p.getX()] != null)
			  return true;
		}
		return false;
	}
	
	/**
	 * Verifica se uma peça tem permissão para andar de lado.
	 */
	protected boolean canStrafe(int dir)
	{
		int direction = 1;
		if(dir == Direction.LEFT)
		 direction = -1;
		 
		ArrayList body = piece.getBody();
		for(int i=0; i<body.size(); i++)
		{
			Point p = (Point)body.get(i);
			
			//**testa com a parede do lado
			if( ( (p.getX() + direction) == tableGame[0].length ) ||
			    ((p.getX() + direction) == -1 ) 
			  ) 
			 return false;
			
			//**testa com a matrix
			try{
			if((p.getX()+ direction >=0) && (p.getX()+ direction < tableGame[0].length) && (p.getY()>=0))
			 if(tableGame[p.getY()][p.getX() + direction] != null)
			  return false;
			}
			catch(Exception e)
			{
				//System.out.println (p.getY());
				//System.out.println (tableGame[0].length);
				System.out.println ("Furou o array");
			}
		}
		return true;
	}
	
	/**
	 *Verifica a peça atual pode ou não girar
	 */
	protected boolean canRotate(int dir)
	{
		 Piece clone = null;
		 if(piece instanceof PieceI)
		  clone = new PieceI();
		 else
		  if(piece instanceof PieceN)
		  	clone = new PieceN();
		  else
		   if(piece instanceof PieceT)
		  	clone = new PieceT();
		   else
		    if(piece instanceof PieceSquare)
		  	 clone = new PieceSquare();
		 	else
		 	 clone = new PieceL();
		 
		  
		 clone.setBody(piece.getBody());
		 clone.setDirection(piece.getDirection()); 
		 
		 clone.rotate(dir);
		 
		 ArrayList body = clone.getBody();
		 for(int i =0; i< body.size(); i++)
		 {
		 	
		 	Point p = (Point)body.get(i);
		 	//System.out.print(" "+p.getX());
		 	try {
			 	 if((p.getX() >= tableGame[0].length) ||
			 	   (p.getX() < 0) ||
			 	   (p.getY() >= tableGame.length) ||
			 	   tableGame[p.getY()][p.getX()] != null
			 	  )
			 	  return false;
			 }
			 catch (Exception ex) {
			 }
		 	
		 }
//		 System.out.println ("");
		 return true;
	  	
	}
	
	/**
	 *Alimentando a matriz
	 */
	protected void feedMatrix()
	{
		ArrayList body = piece.getBody();
		try{
			for(int i=0; i<body.size(); i++)
			{
				Point p = (Point)body.get(i);
				tableGame[p.getY()][p.getX()] = p.getColor(); 
				 
			}
		}
		catch(ArrayIndexOutOfBoundsException aiobe)
		{
			System.out.println ("YOU LOST");
			lost = true;
			
		}
	}
	
	/**
	 *Teste para saber se uma linha já pode ser destruída. Retorna a linha que deve ser destruída.
	 */
	protected int testLine()
	{
		for(int i = tableGame.length -1 ; i>=0; i--)
		{
		   boolean line = true;	
		   for(int j =0; j< tableGame[0].length; j++)
		   {
		     if(tableGame[i][j] == null)
		     {
		      line = false;
		      break;
		     }
		   }
		   
		   if(line)
		    return i;
		}
		  
		return -1;
	}
	
	/**
	 *Remonta a matrix destruindo a linha com vários 1s
	 */
	protected void remountMatrix(int line)
	{
		this.blinkLine(line);
		for(int i = line; i > 0; i--)
		 for(int j = 0; j < tableGame[0].length; j++)
		  tableGame[i][j] = tableGame[i-1][j];
		  
		  if(player != null)
		  {
			player.setPoints(player.getPoints() + 1);
		  }
		  	
	}
	
	protected void blinkLine(int line)
	{
		this.drawPanel.setEntered(true);
		if(change)
		  for(int i=0;i<this.tableGame[0].length;i++)
		  {
				tableGame[line][i] = Color.white;
				try{Thread.sleep(50);}catch(Exception e){}	
			    this.drawPanel.repaint();	
		  }
		else
		  for(int i=tableGame[0].length-1; i>=0; i--)
		  {
				tableGame[line][i] = Color.white;
				try{Thread.sleep(50);}catch(Exception e){}	
			    this.drawPanel.repaint();	
		  }
		change = !change;
		this.drawPanel.setEntered(false);
	}
	/**
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

} 