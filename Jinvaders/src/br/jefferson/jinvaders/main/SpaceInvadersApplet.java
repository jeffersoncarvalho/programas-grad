/*
 * Created on 04/08/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;

import br.jefferson.jinvaders.character.AlienController;
import br.jefferson.jinvaders.character.SpaceCraft;
import br.jefferson.jinvaders.events.KeyHandler;
import br.jefferson.jinvaders.gui.GameCanvas;
 
import br.jefferson.jinvaders.util.SingletonObject;

/**
 * @author Usuario
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SpaceInvadersApplet extends  Applet implements KeyListener {

	GameCanvas gc = new GameCanvas();

	SpaceCraft sc = new SpaceCraft();

	AlienController ac = new AlienController(sc);

	static AudioClip alienShot;

	public SpaceInvadersApplet() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		 
		 
		//iniciandos sons
		
		SpaceInvadersApplet.alienShot = getAudioClip(getCodeBase(),"audio/drip.au");
		
		//layout
		this.setLayout(new BorderLayout());

		//Ajustando refencias do Singleton
		SingletonObject.setGc(this.gc);

		//ajustando referencias em canvas
		this.gc.setSpaceCraft(this.sc);
		this.gc.setAliens(this.ac.getAliens());
		KeyHandler key = new KeyHandler(sc, ac.getAliens());
		this.gc.setKeyListener(key);
	 

		//Adcionando Painéis ao Frame
		this.add(gc, BorderLayout.CENTER);

		 
		
	}

	public static void playAlienShot()
	{
		if(SpaceInvadersApplet.alienShot!=null)
			SpaceInvadersApplet.alienShot.play();
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("testefgh");
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("wdsadds");
	}
	 

}