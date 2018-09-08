/*
 * Created on Jul 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package model;

import java.io.Serializable;

/**
 * @author correa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ModelEmulator extends Serializable {
	public void dispose();
	/**
	 * 
	 */
	public void show();

	/**
	 * @param nodeSet
	 * @return
	 */
	public int[] getEmulatedPrimitiveIntArray(String arrayName);

	public int[][] getEmulatedPrimitiveIntMatrix(String matrixName);
	
	/**
	 * @param elementMatSet
	 * @return
	 */
	public float[][] getEmulatedFloatMatrix(String matrixName);

	/**
	 * @param independentVector
	 * @return
	 */
	public float[] getEmulatedFloatArray(String arrayName);
}
