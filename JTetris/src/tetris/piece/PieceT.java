package tetris.piece;


import java.awt.Color;
import tetris.util.Point;
import tetris.util.Direction;
import tetris.util.TetrisConstants;

public class PieceT extends Piece
{
	/**
	 * Montando a peça.
	 */
	public PieceT()
	{
		super();
		id = TetrisConstants.PIECET;
		this.direction = Direction.DOWN;
		this.body.add(new Point(5,-2,Color.red));//ponto de rotação, não muda
		this.body.add(new Point(4,-2,Color.red));
		this.body.add(new Point(6,-2,Color.red));
		this.body.add(new Point(5,-1,Color.red));
	}
	
	
	/**
	 * 		 (x,y-1)
	 *(x-1,y)(X , Y)
	 *		 (x,y+1) 	
	 */
	protected void lookingLeft()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()-1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX());
		((Point)this.body.get(3)).setY(rotatePoint.getY()+1);
		
		this.direction = Direction.LEFT;
	}
	
	/**
	 * 		 (x,y-1)
	 *		 (X , Y)(x+1,y)
	 *		 (x,y+1) 	
	 */
	protected void lookingRight()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()-1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX());
		((Point)this.body.get(3)).setY(rotatePoint.getY()+1);
		
		this.direction = Direction.RIGHT;
	}
	
	/**
	 * 		 (x,y-1)
	 *(x-1,y)(X , Y)(x+1,y)
	 *		  	
	 */
	protected void lookingUp()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()-1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(3)).setY(rotatePoint.getY());
		
		this.direction = Direction.UP;
	}
	
	/**
	 * 		 
	 *(x-1,y)(X , Y)(x+1,y)
	 *		 (x,y+1) 	
	 */
	protected void lookingDown()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()+1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(3)).setY(rotatePoint.getY());
		
		this.direction = Direction.DOWN;
	}
	
	/**
	 * Coloca a peça com seu valores originais.
	 */
	public void restartPiece()
	{
		((Point)this.body.get(0)).setAll(5,-2);
		((Point)this.body.get(1)).setAll(4,-2);
		((Point)this.body.get(2)).setAll(6,-2);
		((Point)this.body.get(3)).setAll(5,-1);
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