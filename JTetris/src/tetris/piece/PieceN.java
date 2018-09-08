package tetris.piece;

import java.awt.Color;
import tetris.util.Point;
import tetris.util.Direction;
import tetris.util.TetrisConstants;

public class PieceN extends Piece
{
	/**
	 * Montando a peça.
	 */
	public PieceN()
	{
		super();
		id = TetrisConstants.PIECEN;
		this.direction = Direction.LEFT;
		this.body.add(new Point(5,-2,Color.orange));//ponto de rotação, não muda
		this.body.add(new Point(5,-1,Color.orange));
		this.body.add(new Point(6,-2,Color.orange));
		this.body.add(new Point(6,-3,Color.orange));
	}
	
	/**
	 *configurando valores iniciais
	 */
	public void restartPiece()
	{
		((Point)this.body.get(0)).setAll(5,-2);
		((Point)this.body.get(1)).setAll(5,-1);
		((Point)this.body.get(2)).setAll(6,-2);
		((Point)this.body.get(3)).setAll(6,-3);
		this.direction = Direction.LEFT;
	}
	
	/*		
	 *              (x+1,y-1)  
	 *       (x,y  )(x+1,y )
	 *		 (x,y+1)
	 *
	 */
	protected void lookingLeft()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()+1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(3)).setY(rotatePoint.getY()-1);
		
		this.direction = Direction.LEFT;
	}
	/*		
	 *              (x,y-1)  
	 *       (x-1,y )(x,y )
	 *		 (x-1,y+1)
	 *
	 */
	protected void lookingRight()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()-1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(3)).setY(rotatePoint.getY()+1);
		
		this.direction = Direction.RIGHT;
	}
	/*		
	 *              
	 *       (x-1,y  )(x,y )
	 *		 		(x,y+1)(x+1,y+1)  
	 *
	 */
	protected void lookingUp()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX());
		((Point)this.body.get(1)).setY(rotatePoint.getY()+1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(2)).setY(rotatePoint.getY()+1);
		
		((Point)this.body.get(3)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(3)).setY(rotatePoint.getY());
		
		this.direction = Direction.UP;
	}
	
	/*		
	 *              
	 *       (x-1,y-1 )(x,y-1 )
	 *		 			(x,y)(x+1,y)  
	 *
	 */
	protected void lookingDown()
	{
		Point rotatePoint = (Point)this.body.get(0);
		
		((Point)this.body.get(1)).setX(rotatePoint.getX()-1);
		((Point)this.body.get(1)).setY(rotatePoint.getY()-1);
		
		((Point)this.body.get(2)).setX(rotatePoint.getX()+1);
		((Point)this.body.get(2)).setY(rotatePoint.getY());
		
		((Point)this.body.get(3)).setX(rotatePoint.getX());
		((Point)this.body.get(3)).setY(rotatePoint.getY()-1);
		
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