/*
 * Created on 29/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.gui;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Painter {

	public static boolean blink = false;
	
	public static void paintSpaceCraft(Graphics2D g2d, int x,int y,Color color)
	{
		g2d.setColor(color);
		g2d.fill3DRect(x+7,y,6,8,true);
		g2d.fill3DRect(x+3,y+6,14,8,true);
		g2d.fill3DRect(x,y+14,20,8,true);
		g2d.fillOval(x,y+21,5,5);
		g2d.fillOval(x+15,y+21,5,5);
		
	}
	
	public static void paintShot(Graphics2D g2d, int x,int y)
	{	
		g2d.setColor(Color.blue);
		g2d.fillOval(x+8,y,5,5);	
	}
	
	public static void paintAlienShot(Graphics2D g2d, int x,int y)
	{	
		g2d.setColor(Color.red);
		g2d.fillOval(x+8,y,5,5);	
	}
	
	public static void paintAlien(Graphics2D g2d, int x,int y)
	{	
		g2d.setColor(Color.magenta);
		g2d.fillRect(x,y,3,3);
		g2d.fillRect(x+3,y+3,3,3);
		g2d.fillRect(x+20,y,3,3);
		g2d.fillRect(x+17,y+3,3,3);
		g2d.fillRect(x+3,y+6,17,10);
		g2d.fillRect(x+17,y+16,3,3);
		g2d.fillRect(x+3,y+16,3,3);
		
		if(!blink){
			g2d.fillRect(x+17,y+19,3,3);
			g2d.fillRect(x+3,y+19,3,3);
		}else
		{
			g2d.fillRect(x+14,y+19,3,3);
			g2d.fillRect(x+6,y+19,3,3);
		}
		
		g2d.setColor(Color.white);
		g2d.fillRect(x+6,y+8,3,3);
		g2d.fillRect(x+14,y+8,3,3);
		//g2d.fillRect(x,y+12,20,6);
		//g2d.drawOval(x,y,20,20);	
	}
	public static void paintAlien2(Graphics2D g2d, int x,int y)
	{	
		g2d.setColor(Color.gray);
		g2d.fillOval(x,y,20,10);
		g2d.fillRect(x,y+5,20,10);
		g2d.fillOval(x,y+10,20,10);
		
		if(!blink){
			g2d.setColor(Color.white);
			g2d.fillOval(x+3,y+5,5,5);
			g2d.fillOval(x+14,y+5,5,5);}
		else
		{
			g2d.setColor(Color.white);
			g2d.fillOval(x+5,y+5,5,5);
			g2d.fillOval(x+11,y+5,5,5);
		}
		g2d.fillRect(x+3,y+13,3,3);
		g2d.fillRect(x+7,y+13,3,3);
		g2d.fillRect(x+11,y+13,3,3);
		g2d.fillRect(x+15,y+13,3,3);
		 
	}
	public static void paintAlien3(Graphics2D g2d, int x,int y)
	{	
		g2d.setColor(Color.orange);
		g2d.fillRect(x+3,y,15,5);
		g2d.fillRect(x,y+5,21,8);
		g2d.fillRect(x+4,y+13,13,5);
		g2d.fillRect(x+5,y+18,3,3);
		g2d.fillRect(x+9,y+18,3,3);
		g2d.fillRect(x+13,y+18,3,3);
		if(!blink)
		{
			g2d.fillRect(x,y+13,3,5);
			g2d.fillRect(x+18,y+13,3,5);
		}
		else
		{
			g2d.fillRect(x,y+10,3,5);
			g2d.fillRect(x+18,y+10,3,5);
		
		}
		g2d.setColor(Color.white);
		g2d.fillRect(x+13,y+8,3,3);
		g2d.fillRect(x+5,y+8,3,3);
		 
		 
	}
	
	
		
	 
}
