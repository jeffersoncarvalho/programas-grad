/*
 * Created on 31/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.util;

import br.jefferson.jinvaders.gui.GameCanvas;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SingletonObject {
	
	private static GameCanvas gc;
	
	
	/**
	 * @param gc The gc to set.
	 */
	public static void setGc(GameCanvas gc) {
		SingletonObject.gc = gc;
	}
	
	public synchronized static void repaint()
	{
		if(gc!=null)
			SingletonObject.gc.repaint();
	}
	
	 
	
	

}
