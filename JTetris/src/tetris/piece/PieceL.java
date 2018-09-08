package tetris.piece;

import java.awt.Color;
import tetris.util.Point;
import tetris.util.Direction;
import tetris.util.TetrisConstants;

public class PieceL extends Piece
{
	/**
	 * Montando a peça.
	 */
	public PieceL()
	{
		super();
		id = TetrisConstants.PIECEL;
		this.direction = Direction.RIGHT;
		this.body.add(new Point(5,-2,Color.blue));//ponto de rotação, não muda
		this.body.add(new Point(6,-2,Color.blue));
		this.body.add(new Point(5,-3,Color.blue));
		this.body.add(new Point(5,-4,Color.blue));
		this.direction = Direction.RIGHT;
	}
	
	/**
	 *configurando valores iniciais
	 */
	public void restartPiece()
	{
		((Point)this.body.get(0)).setAll(5,-2);
		((Point)this.body.get(1)).setAll(6,-2);
		((Point)this.body.get(2)).setAll(5,-3);
		((Point)this.body.get(3)).setAll(5,-4);
	}
	
	/*		
	 *(x-1,y)(x,y)
	 *		 (x,y+1)
	 *		 (x,y+2)
	 *
	 */
	protected void lookingLeft()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()+2);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX());
		((Point)this.body.get(2)).setY(rotatePoint.getY()+1);
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(3)).setY(rotatePoint.getY());
		
		this.direction = Direction.LEFT;
	}
	/*
	 *(x,y-2)
	 *(x,y-1)
	 *(x,y)(x+1,y)
	 */
	protected void lookingRight()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()-2);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX());
		((Point)this.body.get(2)).setY(rotatePoint.getY()-1);
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(3)).setY(rotatePoint.getY());
		
		this.direction = Direction.RIGHT;
	}
	/*				(x,y-1)
	 *	(x-2,y)(x-1,y)(x,y)
	 */
	protected void lookingUp()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()-1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()-2);
		((Point)this.body.get(3)).setY(rotatePoint.getY());
		
		this.direction = Direction.UP;
	}
	
	/*
	 *		        ( x,y)(x+1,y)(x+2,y)
	 *	  	   		(x,y+1)
	 */
	protected void lookingDown()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()+1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()+2);
		((Point)this.body.get(3)).setY(rotatePoint.getY());
		
		this.direction = Direction.DOWN;
	}
	
	public Object clonning()
	{
		try {
			super.clone();
			return this.clone();
	    }
	    catch (Exception ex) {
	    	System.out.println (ex.toString());
	    }
		return null;
	}
}