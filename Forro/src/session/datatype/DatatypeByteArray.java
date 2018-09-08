/*
 * Created on Jul 26, 2005
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
public class DatatypeByteArray extends AbstractDatatype implements Datatype {
	/**
	 * @uml.property  name="array" multiplicity="(0 -1)" dimension="1"
	 */
	private byte[] array;
	
	/**
	 * 
	 */
	public DatatypeByteArray() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param array
	 */
	public DatatypeByteArray(byte[] array) {
		super();
		this.array = array;
	}
	
	public void setValue(byte[] value) {
        this.array = value;
    }

    public byte[] getValue() {
        return array;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "" + array;
	}

	/* (non-Javadoc)
	 * @see model.Datatype#writeExternal(model.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(array.length);
		out.write(array);
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		array = new byte[in.readInt()];
		in.read(array);
	}

}
