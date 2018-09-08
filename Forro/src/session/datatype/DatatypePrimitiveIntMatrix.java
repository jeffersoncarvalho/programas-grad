/*
 * Created on Aug 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package session.datatype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author correa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DatatypePrimitiveIntMatrix extends AbstractDatatypeArray
		implements Datatype {

	/**
	 * @uml.property  name="matrix" multiplicity="(0 -1)" dimension="2"
	 */
	int[][] matrix;
	
	/**
	 * @param map
	 */
	public DatatypePrimitiveIntMatrix(int[][] map) {
		matrix = map;
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getPrimitiveFloatArray()
	 */
	public int[][] getPrimitiveIntMatrix() {
		return matrix;
	}
	
	/* (non-Javadoc)
	 * @see model.Datatype#writeExternal(model.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(matrix.length);
		for (int i = 0; i < matrix.length; i++) {
			out.writeInt(matrix[i].length);			
			for (int j = 0; j < matrix[i].length; j++)
				out.writeInt(matrix[i][j]);
		}
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		int length = in.readInt();
		matrix = new int[length][];
		for (int i = 0; i < length; i++) {
			length = in.readInt();
			matrix[i] = new int[length];
			for (int j = 0; j < length; j++)
				matrix[i][j] = in.readInt();	
		}
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getType()
	 */
	public Object getType() {
		return intMatrix;
	}

}
