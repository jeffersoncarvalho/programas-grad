/*
 * Created on 09/02/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.unifor.edu.jefferson.jpacman.util;

import br.unifor.edu.jefferson.jpacman.gui.DrawPanel;

/**
 * @author jefferson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Observer {
	
	private DrawPanel drawPanel;
	
	public Observer(DrawPanel drawPanel)
	{
		this.drawPanel = drawPanel;
	}
	
	public void updateDrawPanel()
	{
		this.drawPanel.repaint();
	}

}
