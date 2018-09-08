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
public class DatatypeArray extends AbstractDatatypeArray implements Datatype {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4584674912871900539L;
	/**
	 * The encapsulated array
	 * @uml.property  name="array"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private Datatype[] array;
	
	/**
	 * Creates an empty array. 
	 */
	public DatatypeArray() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Encapsulates the specified array.
	 * 
	 * @param array
	 */
	public DatatypeArray(Datatype[] array) {
		super();
		this.array = array;
	}
	
    public Datatype[] getDatatypeArray() {
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
		for (int i = 0; i < array.length; i++) {
			out.writeUTF(array[i].getClass().getName());
			array[i].writeExternal(out);
		}
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		int length = in.readInt();
		array = new Datatype[length];
		try {
			for (int i = 0; i < length; i++) {
				String className = in.readUTF();
				// TODO usar reposit�rio!!!!
				array[i] = (Datatype) Class.forName(className).newInstance();
				array[i].readExternal(in);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see session.datatype.AbstractDatatypeArray#getType()
	 */
	public Object getType() {
		return datatypeArray;
	}
}
