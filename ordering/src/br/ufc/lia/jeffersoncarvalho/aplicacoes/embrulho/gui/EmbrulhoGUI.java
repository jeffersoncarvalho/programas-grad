package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class EmbrulhoGUI extends JFrame{
	
	 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//***GUI***GUI***GUI
	public EmbrulhoGUI(){
		
		 
		super("Embrulho 3D - Jefferson de Carvalho Silva");
		Dimension resolucao = new Dimension(800,600);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		this.setResizable(true);
		 
		EmbrulhoPainel ep = new EmbrulhoPainel(resolucao);
		PainelControle pc = new PainelControle(ep);
		c.add(pc.getBar(),BorderLayout.NORTH);
		c.add(ep,BorderLayout.CENTER);
		this.setSize(resolucao);
		
		this.setVisible(true);
		
		pack();
		
		 
		KeyHandler keyHandler = new KeyHandler(ep);
		addKeyListener(keyHandler);

		//Fechando Janela
		addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e)
					{
						
						System.exit(0);
					}
				}  
			);
		 
	}

	public static void main(String[] args) {
		/* try {
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception e) { }*/
		new EmbrulhoGUI();
	}
 
	
}
class KeyHandler extends KeyAdapter
{
	private EmbrulhoPainel tela;
   
  public KeyHandler(EmbrulhoPainel tela){
	  this.tela = tela;
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
         
          tela.moveLeft();
          tela.display();
          tela.repaint();
          //esquerda
        break;
        case KeyEvent.VK_RIGHT:
        	
        	tela.moveRight();
        	tela.display();
            tela.repaint();
         //direita
        break;
        case KeyEvent.VK_UP:
          tela.moveUp();
          tela.display();
          tela.repaint();
         //cima
        break;
        case KeyEvent.VK_DOWN:
          tela.moveDown();
          tela.display();
          tela.repaint();
         //baixo
        break;
        case KeyEvent.VK_I:
             tela.zoomIn();
             tela.display();
             tela.repaint();
        break;
        case KeyEvent.VK_O:
           
            tela.zoomOut();
            tela.display();
            tela.repaint();
        break;
        case KeyEvent.VK_F:
            tela.faster();
       break;
       case KeyEvent.VK_S:
           
           tela.slower();
       break;
       case KeyEvent.VK_C:
           
           tela.mudarColorir();
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_P:
           
           tela.tiraPontos();
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_L:
           
           tela.mudarApagaLinha();
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_E:
           
           tela.mudarApagaEixo();
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_B:
           
           tela.setExplodir(true);
       break;
       case KeyEvent.VK_V:
           
           tela.rodarAnimacaoFechoDaMorte();
       break;
	   case KeyEvent.VK_A:
		           
		   tela.tetraedroDidatico--;
		   tela.display();
           tela.repaint();
	   break;
	   case KeyEvent.VK_Q:
		    
		    tela.tetraedroDidatico++;
		    tela.display();
	        tela.repaint();
	   break;
       //
       case KeyEvent.VK_NUMPAD8:
           tela.setAngleX(tela.getAngleX()-2);
           tela.display();
           tela.repaint();
           
       break;
       case KeyEvent.VK_NUMPAD2:
           
    	   tela.setAngleX(tela.getAngleX()+2);
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_NUMPAD4:
           
    	   tela.setAngleY(tela.getAngleY()+2);
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_NUMPAD6:
           
    	   tela.setAngleY(tela.getAngleY()-2);
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_NUMPAD7:
    	   tela.setAngleX(tela.getAngleX()-2);
    	   tela.setAngleY(tela.getAngleY()+2);
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_NUMPAD9:
    	   tela.setAngleX(tela.getAngleX()+2);
    	   tela.setAngleY(tela.getAngleY()-2);
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_NUMPAD1:
    	   tela.setAngleX(tela.getAngleX()-2);
    	   tela.setAngleY(tela.getAngleY()-2);
           tela.display();
           tela.repaint();
       break;
       case KeyEvent.VK_NUMPAD3:
    	   tela.setAngleX(tela.getAngleX()+2);
    	   tela.setAngleY(tela.getAngleY()+2);
           tela.display();
           tela.repaint();
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


