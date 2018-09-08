/*
 * Created on 20/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.events;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import br.jefferson.jinvaders.character.Alien;
import br.jefferson.jinvaders.character.SpaceCraft;
import br.jefferson.jinvaders.util.SingletonObject;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KeyHandler extends KeyAdapter
{
	
  private SpaceCraft sc;
  private Alien[] aliens;
  
  public KeyHandler(SpaceCraft sc,Alien[] aliens)
  {
  		this.sc = sc;
  		this.aliens = aliens;
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
          this.sc.moveLeft();
          
         //esquerda
        break;
        case KeyEvent.VK_RIGHT:
          this.sc.moveRight();
          
         //direita
        break;
        case KeyEvent.VK_SPACE:
            this.sc.shot(this.aliens);
           //direita
          break;
      }//switch
     
      SingletonObject.repaint();
	}
	catch(Exception ex)
	{
		System.out.println (ex.toString());
		
	}
  }
  
}

 