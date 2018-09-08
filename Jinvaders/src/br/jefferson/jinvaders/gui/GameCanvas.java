/*
 * Created on 29/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import br.jefferson.jinvaders.character.Alien;
import br.jefferson.jinvaders.character.AlienShot;
import br.jefferson.jinvaders.character.SpaceCraft;
import br.jefferson.jinvaders.character.SpaceCraftShot;
import br.jefferson.jinvaders.events.KeyHandler;
import br.jefferson.jinvaders.util.SingletonPlayer;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GameCanvas extends DoubleBuffer{
	
	private SpaceCraft spaceCraft;
	private Alien[] aliens;
	private Font variable, value;
	
	
	
	/**
	 * 
	 */
	public GameCanvas() {
		// TODO Auto-generated constructor stub
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(400,500));
		this.variable = new Font("Arial",Font.BOLD,14);
		this.variable = new Font("Arial",Font.PLAIN,14);
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	public void paintBuffer(Graphics g) {
		// TODO Auto-generated method stub
		//super.paint(g);
		 
		g.drawLine(5,0,5,400);
		g.drawLine(395,0,395,400);
		g.drawLine(5,350,395,350);
		//cenario
		g.setColor(Color.cyan);
		g.fillRect(6,0,389,350);
		g.setColor(Color.green);
		g.fillRect(6,351,389,390);
		//anti alias
	   	this.antiAliasOn(g);
	   	//nave
	   	g.setColor(Color.black);
	   	if(this.spaceCraft!=null)
	   	{
	   		Painter.paintSpaceCraft((Graphics2D)g,
	   							 	 this.spaceCraft.getX(),
	   								 this.spaceCraft.getY(),Color.black);
	   		List shots = this.spaceCraft.getShots();
	   		for(int i=0;i<shots.size();i++)
	   		{
	   			SpaceCraftShot shot = (SpaceCraftShot)shots.get(i);
	   			Painter.paintShot((Graphics2D)g,shot.getX(),shot.getY());
	   		}
	   		
	   	}
	   	//aliens
	   	if(this.aliens!=null)
	   	{
	   		//tiros
	   		List alienShots = Alien.aliensShots;
	   		if(alienShots!=null)
	   			for(int i=0;i<alienShots.size();i++)
	   			{
	   				AlienShot ashot = (AlienShot)alienShots.get(i);
	   				Painter.paintAlienShot((Graphics2D)g,ashot.getX(),ashot.getY());
	   			}
	   		
	   		//os próprios
	   		for(int i=0;i<this.aliens.length;i++)
	   		{
	   			Alien alien = this.aliens[i];
	   			if(alien!=null){
	   				if(alien.getAlienType()==0)
	   					Painter.paintAlien((Graphics2D)g,alien.getX(),alien.getY());
	   				else if (alien.getAlienType()==1)
	   					Painter.paintAlien2((Graphics2D)g,alien.getX(),alien.getY());
	   					else
	   						Painter.paintAlien3((Graphics2D)g,alien.getX(),alien.getY());
	   			}
	   				
	   		}
	   		
	   	}
	   	
	   	//status side bar
	   	//lifes
	   	int lifes = SingletonPlayer.lifes;
	   	for(int i=0;i<lifes;i++)
	   		Painter.paintSpaceCraft((Graphics2D)g,408+i*30,370,Color.white);
	   	//information..
	    //variables
	   	g.setColor(Color.white);
	    g.setFont(this.variable);
	   	g.drawString("Name",400,20);
	   	g.drawString("Points",400,80);
	   	g.drawString("Level",400,140);
	   	
	   	//values
	   	g.setColor(Color.yellow);
	   	g.setFont(this.value);
	    g.drawString(SingletonPlayer.name,400,40);
	    g.drawString(SingletonPlayer.points+"",400,100);
	    g.drawString(SingletonPlayer.level+"",400,160);
	}
	
	//ligando antialias
	private void antiAliasOn(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                			 RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	/**
	 * @return Returns the sc.
	 */
	public SpaceCraft getSpaceCraft() {
		return spaceCraft;
	}
	/**
	 * @param sc The sc to set.
	 */
	public void setSpaceCraft(SpaceCraft sc) {
		this.spaceCraft = sc;
	}
	/**
	 * @return Returns the aliens.
	 */
	public Alien[] getAliens() {
		return aliens;
	}
	/**
	 * @param aliens The aliens to set.
	 */
	public void setAliens(Alien[] aliens) {
		this.aliens = aliens;
	}
	
	
	public void setKeyListener(KeyHandler keyHandler){
		this.addKeyListener(keyHandler);
	}
	 
}
