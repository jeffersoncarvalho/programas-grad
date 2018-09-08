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
public class DatatypeDoubleArray extends AbstractDatatype implements Datatype {
    /**
	 * @uml.property  name="value" multiplicity="(0 -1)" dimension="1"
	 */
    protected double[] value;

    public DatatypeDoubleArray() {
        super();
    }

    /**
	 * @param value  The value to set.
	 * @uml.property  name="value"
	 */
    public void setValue(double[] value) {
        this.value = value;
    }

    /**
	 * @return  Returns the value.
	 * @uml.property  name="value"
	 */
    public double[] getValue() {
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
	 * @see session.datatype.Datatype#writeExternal(session.datatype.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(value.length);
		for (int i = 0; i < value.length; i++)
			out.writeDouble(value[i]);
	}

	/* (non-Javadoc)
	 * @see session.datatype.Datatype#readExternal(session.datatype.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		value = new double[in.readInt()];
		for (int i = 0; i < value.length; i++)
			value[i] = in.readDouble();
	}

}
