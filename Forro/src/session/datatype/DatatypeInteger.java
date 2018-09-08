package session.datatype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;



/**
 *  Wraps an integer type. A class that encapsulates an <tt>int</tt> in a Datatype object. 
 * It is used to handle the <tt>startIterator</tt> port method.
 *
 */
public class DatatypeInteger extends AbstractDatatype implements Datatype {
    /**
	 * Value of this object
	 * @uml.property  name="value"
	 */
    protected int value;

    public DatatypeInteger() {
        super();
    }

    /**
	 * @param i
	 */
	public DatatypeInteger(int i) {
		value = i;
	}

	/**
	 * @return  Returns the value.
	 * @uml.property  name="value"
	 */
	public int getValue() {
        return value;
    }

    /**
	 * @param value  The value to set.
	 * @uml.property  name="value"
	 */
    public void setValue(int value) {
        this.value = value;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return value == ((DatatypeInteger) obj).value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(value=" + value + ", hashCode=" + hashCode() + ")";
	}

	/* (non-Javadoc)
	 * @see model.Datatype#writeExternal(model.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(value);
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		value = in.readInt();
	}
}
