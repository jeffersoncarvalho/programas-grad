package session.datatype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;



/**
 *  Wraps a float type.
 *
 */
public class DatatypeFloat extends AbstractDatatype implements Datatype {
    /**
	 * @uml.property  name="value"
	 */
    protected float value;

    public DatatypeFloat() {
        super();
    }

    /**
	 * @param f
	 */
	public DatatypeFloat(float f) {
		value = f;
	}

	/**
	 * @return  Returns the value.
	 * @uml.property  name="value"
	 */
	public float getValue() {
        return value;
    }

    /**
	 * @param value  The value to set.
	 * @uml.property  name="value"
	 */
    public void setValue(float value) {
        this.value = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return value == ((DatatypeFloat) obj).value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "" + value;
	}

	/* (non-Javadoc)
	 * @see model.Datatype#writeExternal(model.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeFloat(value);
	}

	/* (non-Javadoc)
	 * @see model.Datatype#readExternal(model.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		value = in.readFloat();
	}
}
