/*
 * Created on 20/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import br.table.Table;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PainelDeDesenho extends JPanel {

	private Table table;
	/**
	 * 
	 */
	public PainelDeDesenho(Table table) {
		super.setPreferredSize(new Dimension(400,400)); 
		this.table = table;
		// TODO Auto-generated constructor stub
	}
	public void paint(Graphics g)
	{
		 super.paint(g);
 		//gradear
 	      for(int i = 0; i<20; i++)
 	       for(int j = 0; j<20; j++)
    	  	 g.drawRect(i*20,j*20,20,20);

		 //matriz de desenho
		 int mat[][] = this.table.getMat();
		 for(int i=0;i<mat.length;i++)
		 	for(int j=0;j<mat[i].length;j++)
		 	{
		 		switch (mat[i][j]) {
				case 1:
					//pinte
					g.fillOval(i*20,j*20,20,20);
					break;
				default:
					break;
				}
		 	}
	}
}
