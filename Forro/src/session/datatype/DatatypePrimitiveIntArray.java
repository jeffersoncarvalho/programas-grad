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
public class DatatypePrimitiveIntArray extends AbstractDatatypeArray implements
		Datatype {

	/**
	 * @uml.property  name="array" multiplicity="(0 -1)" dimension="1"
	 */
	private int[] array;
	
	/**
	 * @param fs
	 */
	public DatatypePrimitiveIntArray(int[] fs) {
		array = fs;
	}

	/**
	 * 
	 */
	public DatatypePrimitiveIntArray() {
		
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getPrimitiveFloatArray()
	 */
	public int[] getPrimitiveIntArray() {
		return array;
	}
	
	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#setPrimitiveIntArray(int[])
	 */
	public void setPrimitiveIntArray(int[] i) {
		array = i;
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
		for (int i = 0; i < array.length; i++) out.writeInt(array[i]);
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		int length = in.readInt();
		array = new int[length];
		for (int i = 0; i < length; i++) array[i] = in.readInt();
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getType()
	 */
	public Object getType() {
		return intArray;
	}

}
