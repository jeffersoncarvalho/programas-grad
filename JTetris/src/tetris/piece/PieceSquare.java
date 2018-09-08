package tetris.piece;

import java.awt.Color;
import tetris.util.Point;
import tetris.util.Direction;
import tetris.util.TetrisConstants;

public class PieceSquare extends Piece
{
	/**
	 * Montando a peça.
	 */
	public PieceSquare()
	{
		super();
		id = TetrisConstants.PIECE_SQUARE;
		this.direction = Direction.RIGHT;
		this.body.add(new Point(6,-3,Color.darkGray));//ponto de rotação, não muda
		this.body.add(new Point(6,-2,Color.darkGray));
		this.body.add(new Point(5,-3,Color.darkGray));
		this.body.add(new Point(5,-2,Color.darkGray));
	}
	
	/**
	 *configurando valores iniciais
	 */
	public void restartPiece()
	{
		((Point)this.body.get(0)).setAll(6,-3);
		((Point)this.body.get(1)).setAll(6,-2);
		((Point)this.body.get(2)).setAll(5,-3);
		((Point)this.body.get(3)).setAll(5,-2);
	}
	
	protected void lookingLeft()
	{
		
		
		this.direction = Direction.LEFT;
	}
	
	protected void lookingRight()
	{
		
		this.direction = Direction.RIGHT;
	}
	
	protected void lookingUp()
	{
			
		this.direction = Direction.UP;
	}
	
	
	protected void lookingDown()
	{
			
		this.direction = Direction.DOWN;
	}
	
	public Object clone()
	{
		return this.clone();
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