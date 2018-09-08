/*
 * Created on 20/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.events;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import br.threads.Neguin;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KeyHandler extends KeyAdapter
{
 
  private Neguin neguin;
  public KeyHandler(Neguin neguin)
  {
  		this.neguin = neguin;
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
          
          neguin.moverParaEsquerda();
         
         //esquerda
        break;
        case KeyEvent.VK_RIGHT:
          neguin.moverParaDireita();

         //direita
        break;
        case KeyEvent.VK_UP:
          neguin.moverParaCima();
         //cima
        break;
        case KeyEvent.VK_DOWN:
          neguin.moverParaBaixo();
         //baixo
        break;
      }//switch
	}
	catch(Exception ex)
	{
		System.out.println (ex.toString());
		
	}
  }
  
}

 