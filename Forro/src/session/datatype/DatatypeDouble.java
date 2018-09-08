/*
 * Created on Jul 22, 2005
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
public class DatatypeDouble extends AbstractDatatype implements Datatype {
    /**
	 * @uml.property  name="value"
	 */
    protected double value;

    public DatatypeDouble() {
        super();
    }

    /**
	 * @param f
	 */
	public DatatypeDouble(float f) {
		value = f;
	}

	/**
	 * @return  Returns the value.
	 * @uml.property  name="value"
	 */
	public double getValue() {
        return value;
    }

    /**
	 * @param value  The value to set.
	 * @uml.property  name="value"
	 */
    public void setValue(double value) {
        this.value = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return value == ((DatatypeDouble) obj).value;
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
		out.writeDouble(value);
	}

	/* (non-Javadoc)
	 * @see session.datatype.Datatype#readExternal(session.datatype.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		value = in.readDouble();
	}

}
