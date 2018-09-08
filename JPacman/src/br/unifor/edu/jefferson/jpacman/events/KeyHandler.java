/*
 * Created on 07/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.events;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import br.unifor.edu.jefferson.jpacman.table.Table;
import br.unifor.edu.jefferson.jpacman.util.JPacmanConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class KeyHandler extends KeyAdapter
{
  
  Table tableGame;
  
  public KeyHandler(Table tableGame)
  {
	this.tableGame = tableGame;
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
		 //esquerda
		 	this.tableGame.getPacman().setDirection(JPacmanConstants.LEFT);
		break;
		case KeyEvent.VK_RIGHT:
		 //direita
			this.tableGame.getPacman().setDirection(JPacmanConstants.RIGHT);
		break;
		case KeyEvent.VK_UP:
		 //cima
			this.tableGame.getPacman().setDirection(JPacmanConstants.UP);
		break;
		case KeyEvent.VK_DOWN:
		 //baixo
			this.tableGame.getPacman().setDirection(JPacmanConstants.DOWN);
		break;
	  }//switch
	  
	   
	}
	catch(Exception ex)
	{
		System.out.println ("Problemas no envio da direção");
		System.out.println (ex.toString());
		
	}
  }
  
}
