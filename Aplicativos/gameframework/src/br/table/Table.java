/*
 * Created on 20/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.table;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Table {
	
	 int mat[][] = new int[20][20];
	 
	 public void pintar(int x,int y,int value)
	 {
	 	this.mat[x][y] = value; 
	 }
	/**
	 * @return Returns the mat.
	 */
	public int[][] getMat() {
		return mat;
	}
	 
}
