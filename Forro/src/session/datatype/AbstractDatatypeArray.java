/*
 * Created on May 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package session.datatype;




/**
 * An abstract class to encapsulate an array in a Datatype object. A concrete class extending this abstract class is used to handle the return of the <tt>toArray</tt> port method. Such a concrete class must overhide one of the <tt>get<type>Array</tt> methods. In this abstract implementation, all of these methods throw the <tt>UnsupportedOperationException</tt>.
 * @author  correa
 */
public abstract class AbstractDatatypeArray extends AbstractDatatype implements Datatype {
	public static final Object intArray = new Object();
	public static final Object intMatrix = new Object();
	public static final Object floatArray = new Object();
	public static final Object floatMatrix = new Object();
	public static final Object datatypeArray = new Object();
	
	/**
	 * @uml.property  name="type"
	 */
	public abstract Object getType();
	
	public float[] getPrimitiveFloatArray() {
		throw new UnsupportedOperationException();
	}

	public float[][] getPrimitiveFloatMatrix() {
		throw new UnsupportedOperationException();
	}

	public int[] getPrimitiveIntArray() {
		throw new UnsupportedOperationException();
	}

	public int[][] getPrimitiveIntMatrix() {
		throw new UnsupportedOperationException();
	}

	public Datatype[] getDatatypeArray() {
		throw new UnsupportedOperationException();
	}

	public void setPrimitiveFloatArray(float[] f) {
		throw new UnsupportedOperationException();
	}

	public void setPrimitiveFloatMatrix(float[][] m) {
		throw new UnsupportedOperationException();
	}

	public void setPrimitiveIntArray(int[] i) {
		throw new UnsupportedOperationException();
	}

	public void setPrimitiveIntMatrix(int[][] i) {
		throw new UnsupportedOperationException();
	}

	public void setDatatypeArray(Datatype[] d) {
		throw new UnsupportedOperationException();
	}
	
	
}