package tetris.gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import tetris.util.Player;
import tetris.util.TetrisConstants;
import tetris.util.Point;
 
public class DrawPaneForNext extends JPanel {
	
  private ArrayList piece;
  private Player player;
  
  /**
   * Cria uma nova instï¿½ncia do DrawPanel
   */
  public DrawPaneForNext(int pieceInt)
  {
    this.piece = this.convertIntoToList(pieceInt);
    super.setPreferredSize(new Dimension(64,100));
    super.setBackground(Color.lightGray); 
  }

  /**
   * Pinta painel.
   */
   public void paint(Graphics g)
   {
     	  super.paint(g);
     	  
     	  Graphics2D g2d = (Graphics2D)g;
     	  g2d.setStroke( new BasicStroke(8,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
     	   
     	  //desenha a peça que se move
     	  for(int i =0; i<this.piece.size(); i++)
     	  {
     	  	Point p = (Point)this.piece.get(i);
     	  	g2d.setColor(p.getColor());
     	  	g2d.fill3DRect(p.getX()*20+2,p.getY()*20+2,20,20,true);
			//g2d.drawRect(p.getX()*20,p.getY()*20,20,20);
     	  }
     	  
     	  g.setColor(Color.black);
     	  g.fillRect(0,85,100,110);
     	  g.setColor(Color.white);
	 
     	  g.drawString("  PLAYER",0,100);
     	  g.setColor(Color.cyan);
     	  g.drawString(player.getName(),0,115);
     	  g.setColor(Color.white);
     	  g.drawString("   SCORE",0,135);
     	  g.setColor(Color.cyan);
		  g.drawString("" + player.getPoints(),25,150);
		  	
     	  g.setColor(Color.white);
     	  g.drawString("   LEVEL",0,170);
     	  g.setColor(Color.cyan);
     	  g.drawString("0",25,185);
     	  
     	  
   
   }
   
   /**
    *
    */
   public void updatePiece(int pieceType)
   {
   	 this.piece = this.convertIntoToList(pieceType);
   }
   
   public ArrayList convertIntoToList(int pieceType)
   {
   	 
   	 ArrayList list = new ArrayList();
   	 switch(pieceType)
   	 {
   	 	case TetrisConstants.PIECE_SQUARE:
   			list.add(new Point(0,0,Color.gray));
   			list.add(new Point(0,1,Color.gray));
   			list.add(new Point(1,0,Color.gray));
   			list.add(new Point(1,1,Color.gray));	
   	 	break;
   	 	case TetrisConstants.PIECEI:
   	 		list.add(new Point(0,0,Color.green));
   			list.add(new Point(0,1,Color.green));
   			list.add(new Point(0,2,Color.green));
   			list.add(new Point(0,3,Color.green));
   	 	break;
   	 	case TetrisConstants.PIECEN:
   	 		list.add(new Point(0,0,Color.yellow));
   			list.add(new Point(0,1,Color.yellow));
   			list.add(new Point(1,1,Color.yellow));
   			list.add(new Point(1,2,Color.yellow));	
   	 	break;
   	 	case TetrisConstants.PIECET:
   	 		list.add(new Point(0,0,Color.red));
   			list.add(new Point(1,0,Color.red));
   			list.add(new Point(2,0,Color.red));
   			list.add(new Point(1,1,Color.red));
   	 	break;
   	 	case TetrisConstants.PIECEL:
   	 		list.add(new Point(0,0,Color.blue));
   			list.add(new Point(0,1,Color.blue));
   			list.add(new Point(0,2,Color.blue));
   			list.add(new Point(1,2,Color.blue));
   	 	break;
   	 }
   	 return list;
   } 
/**
 * @return
 */
public ArrayList getPiece() {
	return piece;
}

/**
 * @return
 */
public Player getPlayer() {
	return player;
}

/**
 * @param list
 */
public void setPiece(ArrayList list) {
	piece = list;
}

/**
 * @param player
 */
public void setPlayer(Player player) {
	this.player = player;
}

}