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
public class DatatypePrimitiveFloatArray extends AbstractDatatypeArray
		implements Datatype {

	/**
	 * @uml.property  name="array" multiplicity="(0 -1)" dimension="1"
	 */
	private float[] array;
	
	/**
	 * 
	 */
	public DatatypePrimitiveFloatArray() {
		super();
	}
	
	/**
	 * @param fs
	 */
	public DatatypePrimitiveFloatArray(float[] fs) {
		array = fs;
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getPrimitiveFloatArray()
	 */
	public float[] getPrimitiveFloatArray() {
		return array;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "[";
		int i;
		for (i = 0; i < array.length - 1; i++) 
			s = s + array[i] + ",";
		s = s + array[i] + "]";
		return s;
	}
	
	/* (non-Javadoc)
	 * @see model.Datatype#writeExternal(model.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(array.length);
		for (int i = 0; i < array.length; i++) out.writeFloat(array[i]);
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		int length = in.readInt();
		array = new float[length];
		for (int i = 0; i < length; i++) array[i] = in.readFloat();
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getType()
	 */
	public Object getType() {
		return floatArray;
	}

}
