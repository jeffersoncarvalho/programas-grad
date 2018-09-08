/*
 * Created on 07/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.unifor.edu.jefferson.jpacman.events.KeyHandler;
import br.unifor.edu.jefferson.jpacman.table.Table;
import br.unifor.edu.jefferson.jpacman.util.Observer;;


/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JPacmanFrame extends JFrame{

 

	  private DrawPanel drawPanel;
	  private Table tableGame;
	  private Observer observer;
	  //private ControlPanel controlPanel;
	 // private ResultPanel resultPanel;
  
	  public JPacmanFrame()
	  {
		//Setando o layout
		super("JPacman - Pacman in Java!");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		this.setResizable(false);
		
		//Tabuleiro controlador do jogo!
		this.tableGame = new Table();
	
		//alocando memória para os painéis
		drawPanel = new DrawPanel(this.tableGame);
		//controlPanel = new ControlPanel();
		//resultPanel = new ResultPanel();
    
		//Guaribando o drawPanel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(drawPanel, BorderLayout.CENTER);	
		panel.setBorder(new EmptyBorder(1,1,1,1));
		panel.setBackground(Color.BLACK);
    
		//Adcionando Painéis ao Frame
		c.add(panel, BorderLayout.CENTER); // desenho
		//c.add(controlPanel.getBar(),BorderLayout.NORTH);
		//c.add(resultPanel,BorderLayout.SOUTH);
		
		//Observer settings
		observer = new Observer(this.drawPanel);
		this.tableGame.setObserver(this.observer);
		
		//start!
		this.tableGame.startGame();
		
		//Mostrando Frame
		show();
		pack();
	
		 
		
		 
		//ouvindo teclado
		KeyHandler keyHandler = new KeyHandler(this.tableGame);
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
  
}