/*
 * Created on 20/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import br.events.KeyHandler;
import br.table.Table;
import br.threads.Neguin;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Principal extends JFrame{
	
	  private PainelDeDesenho drawPanel;
	  
	  public Principal()
	  {
	  	//Setando o layout
	    super("Teste GUI");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		//table
		Table table = new Table();
		//table.pintar(2,10,1);
		//alocando memória para os painéis
	    drawPanel = new PainelDeDesenho(table);  
	    
	    //Adcionando Painéis ao Frame
		c.add(drawPanel, BorderLayout.CENTER);
		
		//neguins..
		Neguin n1 = new Neguin(table,drawPanel,5,5);
		
		//teclas
		KeyHandler key = new KeyHandler(n1);
		
		//enxergando teclas
		this.addKeyListener(key);
		
		//Mostrando Frame
		this.setVisible(true);
	    pack();


		
		
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
	  
	  public static void main(String args[])
	  {
	        final Principal i = new Principal();
	  }

}
