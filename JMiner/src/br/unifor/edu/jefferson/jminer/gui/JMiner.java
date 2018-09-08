/*
 * Created on Mar 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jminer.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

 

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JMiner extends JFrame {

	 
	  public JMiner()
	  {
		//Setando o layout
		super("JMiner");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		this.setResizable(false);
		c.add(new JMinerPanel(),BorderLayout.CENTER);
//		 
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
	  
	  public static void main(String[] args) {
		new JMiner();
	}
}
