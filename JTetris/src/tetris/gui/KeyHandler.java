package tetris.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import tetris.table.Table;
import tetris.util.Direction;

//Manejando eventos do teclado
public class KeyHandler extends KeyAdapter
{
  private Table table;
  
  public void setTable(Table table)
  {
  	this.table = table;
  }
  
  /**
   *Ouvindo o teclado
   */ 
  public void keyPressed(KeyEvent e)
  {
     int key = e.getKeyCode();
     
     try{
     switch(key)
      {
        case KeyEvent.VK_LEFT:
          table.strafePiece(Direction.LEFT);
          
         //esquerda
        break;
        case KeyEvent.VK_RIGHT:
          table.strafePiece(Direction.RIGHT);
           
         //direita
        break;
        case KeyEvent.VK_UP:
          table.rotatePiece(Direction.UP);
           
         //cima
        break;
        case KeyEvent.VK_DOWN:
          table.rotatePiece(Direction.DOWN);
           
         //baixo
        break;
        case KeyEvent.VK_SPACE:
           table.fallAtOnce();
            
        break;	
      }//switch
      
      table.repaintPanel();
	}
	catch(Exception ex)
	{
		System.out.println (ex.toString());
		
	}
  }
  
}