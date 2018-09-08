package tetris.piece;

import java.util.*;

import tetris.util.Point;
import tetris.util.Direction; 

public abstract class Piece implements Cloneable
{
	protected ArrayList body;
	protected int direction;
	protected int id;
	
	public Piece()
	{
		body = new ArrayList();
		
	}
	
	public void setBody(ArrayList b)
	{
		this.body.clear();
		for(int i=0; i<b.size(); i++)
		{
			Point p = (Point)b.get(i);
			Point newP = new Point(p.getX(),p.getY(),p.getColor());
			this.body.add(newP);
		}
	}
	
	public ArrayList getBody()
	{
		return this.body;
	}
	
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	
	public int getDirection()
	{
		return this.direction;
	}
	

	
	public String toString()
	{
		String result = "";
		for(int i =0; i< this.body.size(); i++)
		{
			Point p = (Point)this.body.get(i);
			result += p.getX() + " " + p.getY() + "-";
			
		}
		
		return result;
	}
	
	/**
	 *Fazendo a peça andar de lado um quadrado
	 */
	public void strafe(int dir)
	{
		int signal = 1;
		if(dir == Direction.LEFT)
		 signal = -1;
		
		for(int i =0; i< this.body.size(); i++)
		{
			Point p = (Point)this.body.get(i);
			p.setX(p.getX() + signal);
		}
		
	}
	
	/**
	 *Fazendo a peça cair um quadrado
	 */
	public void fall()
	{
		for(int i =0; i< this.body.size(); i++)
		{
			Point p = (Point)this.body.get(i);
			p.setY(p.getY() + 1);
		}
	}
	
	
	
	public void rotate(int key)
	{
		
		if(key == Direction.UP)//se o jogador apertou para cima
		{

			switch(this.direction)//para onde a peça está apontada?
			{
				case Direction.DOWN:
					this.lookingLeft();
				break;
				case Direction.UP:
					this.lookingRight();
				break;
				case Direction.LEFT:
					this.lookingUp();
				break;
				case Direction.RIGHT:
					this.lookingDown();
				break;
					
			}
			
		}//if
		else
		//apertou para baixo
		{
			switch(this.direction)//para onde a peça está apontada?
			{
				case Direction.DOWN:
					this.lookingRight();
				break;
				case Direction.UP:
					this.lookingLeft();
				break;
				case Direction.LEFT:
					this.lookingDown();
				break;
				case Direction.RIGHT:
					this.lookingUp();
				break;
					
			}
		}//else	
	}
	
	public int getID(){
		return this.id;
	}
	
	public abstract void restartPiece();
	public abstract Object clonning();
	protected abstract void lookingLeft();
	protected abstract void lookingRight();
	protected abstract void lookingUp();
	protected abstract void lookingDown();
	
	
}