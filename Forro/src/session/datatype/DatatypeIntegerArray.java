/**
 * This Package has within a set of classes that implements types used to sent message between classes remotely or not.
 */
package session.datatype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;



/**
 *  Wraps an integer array.
 */
public class DatatypeIntegerArray extends AbstractDatatype implements Datatype {
    /**
	 * @uml.property  name="value" multiplicity="(0 -1)" dimension="1"
	 */
    protected int[] value;

    public DatatypeIntegerArray() {
        super();
    }

	/**
	 * @param value
	 */
	public DatatypeIntegerArray(int[] value) {
		super();
		this.value = value;
	}
	
    /**
	 * @param value  The value to set.
	 * @uml.property  name="value"
	 */
    public void setValue(int[] value) {
        this.value = value;
    }

    /**
	 * @return  Returns the value.
	 * @uml.property  name="value"
	 */
    public int[] getValue() {
        return value;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "[";
		int i;
		for (i = 0; i < value.length - 1; i++) 
			s = s + value[i] + ",";
		s = s + value[i] + "]";
		return s;
	}
	
	/* (non-Javadoc)
	 * @see model.Datatype#writeExternal(model.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(value.length);
		for (int i = 0; i < value.length; i++)
			out.writeInt(value[i]);
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		value = new int[in.readInt()];
		for (int i = 0; i < value.length; i++)
			value[i] = in.readInt();
	}
}
