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
public class DatatypePrimitiveFloatMatrix extends AbstractDatatypeArray
		implements Datatype {

	/**
	 * @uml.property  name="matrix" multiplicity="(0 -1)" dimension="2"
	 */
	float[][] matrix;
	
	/**
	 * @param map
	 */
	public DatatypePrimitiveFloatMatrix(float[][] map) {
		matrix = map;
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getPrimitiveFloatArray()
	 */
	public float[][] getPrimitiveFloatMatrix() {
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
				out.writeFloat(matrix[i][j]);
		}
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		int length = in.readInt();
		matrix = new float[length][];
		for (int i = 0; i < length; i++) {
			length = in.readInt();
			matrix[i] = new float[length];
			for (int j = 0; j < length; j++)
				matrix[i][j] = in.readFloat();	
		}
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getType()
	 */
	public Object getType() {
		return floatMatrix;
	}

}
