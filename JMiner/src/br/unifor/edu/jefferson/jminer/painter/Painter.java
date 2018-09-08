/*
 * Created on Mar 11, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jminer.painter;

import java.awt.Color;
import java.awt.Graphics2D;

import br.unifor.edu.jefferson.jminer.util.JMinerConstants;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Painter {
	public static int blink=1;
	public static int signal=0;
	public static int range=8;
	public static void paintMinerNOGOLD(Graphics2D g, int x, int y)
	{
		//g.setColor(Color.black);
		x = x*JMinerConstants.macroPixel;
		y = y*JMinerConstants.macroPixel;
		g.setColor(Color.black);
		g.fillRect(x+7,y+5,7,4);
		g.fillRect(x+9,y+3,3,3);
		g.fillRect(x+5,y+10,11,5);
		g.fillOval(x+4,y+16,3,3);
		g.fillOval(x+9,y+16,3,3);
		g.fillOval(x+14,y+16,3,3);
		//blink
		if(blink == 1)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.red);
		
		g.fillOval(x+10,y+12,2,2);
			
	}
	
	public static void paintMinerHASGOLD(Graphics2D g, int x, int y)
	{
		
		x = x*JMinerConstants.macroPixel;
		y = y*JMinerConstants.macroPixel;
		//g.setColor(Color.black);
		g.setColor(Color.gray);
		g.fillRect(x+7,y+5,7,4);
		g.fillRect(x+9,y+3,3,3);
		g.fillRect(x+5,y+10,11,5);
		g.fillOval(x+4,y+16,3,3);
		g.fillOval(x+9,y+16,3,3);
		g.fillOval(x+14,y+16,3,3);
		//blink
		if(blink == 1)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.red);
		
		g.fillOval(x+10,y+12,2,2);
			
	}
	
	public static void paintControlCenter(Graphics2D g, int x, int y)
	{
		g.setColor(Color.blue);
		x = x*JMinerConstants.macroPixel;
		y = y*JMinerConstants.macroPixel;
		g.fillOval(x+5,y+16,10,3);
		g.fillOval(x+5,y+4,10,7);
		g.fillRect(x+5,y+10,10,7);
		if(blink == 1)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.red);
		
		//g.fillOval(x+3,y+12,2,2);
		g.fillOval(x+7,y+12,2,2);
		g.fillOval(x+11,y+12,2,2);
		//g.fillOval(x+15,y+12,2,2);
	}
	
	public static void paintGold(Graphics2D g, int x, int y)
	{
		x = x*JMinerConstants.macroPixel;
		y = y*JMinerConstants.macroPixel;
		
		g.setColor(Color.yellow);
		g.fill3DRect(x+1,y+16,8,3,true);
		g.fill3DRect(x+11,y+16,8,3,true);
		g.fill3DRect(x+6,y+11,8,3,true);
		
	}
	
	public static void paintSignal(Graphics2D g, int x, int y)
	{
		if(Painter.signal>(Painter.range*2-1)*20)
			Painter.signal =20;
		 
		g.setColor(Color.gray);
		x = x-(( Painter.signal)/2)+9;
		y = y-(( Painter.signal)/2)+10;
		 
		g.drawOval(x,y,Painter.signal ,Painter.signal );
		 
			Painter.signal+=20;
	}
	/**
	 * @param range The range to set.
	 */
	public static void setRange(int range) {
		range = (range>15)?15:range;
		Painter.range = range;
	}
}
