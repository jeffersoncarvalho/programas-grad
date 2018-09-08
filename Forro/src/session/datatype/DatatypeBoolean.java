/*
 * Created on Jul 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package session.datatype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * A class that encapsulates a <tt>boolean</tt> in a Datatype object. It is used to handle the <tt>hasNextTerm</tt>
 * port method.
 * 
 * @author correa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DatatypeBoolean extends AbstractDatatype implements Datatype {
    /**
	 * @uml.property  name="value"
	 */
    protected boolean value;

    public DatatypeBoolean() {
        super();
    }

	/**
	 * @param value
	 */
	public DatatypeBoolean(boolean value) {
		super();
		this.value = value;
	}
	
    /**
	 * @return  Returns the value.
	 * @uml.property  name="value"
	 */
    public boolean getValue() {
        return value;
    }

    /**
	 * @param value  The value to set.
	 * @uml.property  name="value"
	 */
    public void setValue(boolean value) {
        this.value = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return value == ((DatatypeBoolean) obj).value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "" + value;
	}

	/* (non-Javadoc)
	 * @see session.datatype.Datatype#writeExternal(session.datatype.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeBoolean(value);
	}

	/* (non-Javadoc)
	 * @see session.datatype.Datatype#readExternal(session.datatype.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		value = in.readBoolean();
	}

}
