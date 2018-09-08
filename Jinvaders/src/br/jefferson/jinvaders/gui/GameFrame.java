/*
 * Created on 29/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.gui;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import br.jefferson.jinvaders.character.AlienController;
import br.jefferson.jinvaders.character.SpaceCraft;
import br.jefferson.jinvaders.events.KeyHandler;
import br.jefferson.jinvaders.util.SingletonObject;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GameFrame extends JFrame{
	
	GameCanvas gc = new GameCanvas();
	SpaceCraft sc = new SpaceCraft();
	AlienController ac = new AlienController(sc);
	
	AudioClip soundTrack;
	
	public GameFrame(){
		
		
		//Setando o layout
	    super("JInvaders - Alien Invasion");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		//Ajustando refencias do Singleton
		SingletonObject.setGc(this.gc);
		
		
		//ajustando referencias em canvas
		this.gc.setSpaceCraft(this.sc);
		this.gc.setAliens(this.ac.getAliens());
		
		//Adcionando Painéis ao Frame
		c.add(gc, BorderLayout.CENTER);
		
		//teclas
		KeyHandler key = new KeyHandler(sc,ac.getAliens());
		
		//enxergando teclas
		this.addKeyListener(key);
		
		//Mostrando Frame
		this.setVisible(true);
		this.setResizable(false);
	    pack();
	    
	    //music
	    //SingletonObject.playBackgroungMusic();

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
		new GameFrame();
	}
	
		
}
 
