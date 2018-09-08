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
import java.util.Map.Entry;

/**
 * An abstract Datatype implementation of the Map.Entry interface. This class is intended to reduce the effort to implement the EntrySet of a Map backed provides port. <p>To implement a Map.Entry with this abstract class, one must implement two abstract methods, namely <tt>getDatatypeKey()</tt> and <tt>getDatatypeValue()</tt>. In addition, a constructor with no parameters must be provided. When instantiated with this constructor, the mentioned abstract methods should return "empty" Datatype objects, which will be "filled in" with the <tt>readExternal</tt> method.
 * @author  correa
 */
public abstract class DatatypeMapEntry extends AbstractDatatype implements
		Entry, Datatype {

	/**
	 * @uml.property  name="value"
	 * @uml.associationEnd  
	 */
	private Datatype value = null;
	/**
	 * @uml.property  name="key"
	 * @uml.associationEnd  
	 */
	private Datatype key = null;

	/**
	 * 
	 */
	protected DatatypeMapEntry() {
		super();
	}
	
	/**
	 * @return
	 * @uml.property  name="datatypeKey"
	 * @uml.associationEnd  readOnly="true"
	 */
	protected abstract Datatype getDatatypeKey();

	/**
	 * @return
	 * @uml.property  name="datatypeValue"
	 * @uml.associationEnd  readOnly="true"
	 */
	protected abstract Datatype getDatatypeValue();
	
	/* (non-Javadoc)
	 * @see java.util.Map.Entry#getKey()
	 */
	public Object getKey() {
		return key == null ? key = getDatatypeKey() : key;
	}

	/* (non-Javadoc)
	 * @see java.util.Map.Entry#getValue()
	 */
	public Object getValue() {
		return value == null ? value = getDatatypeValue() : value;
	}

	/* (non-Javadoc)
	 * @see java.util.Map.Entry#setValue(java.lang.Object)
	 */
	public Object setValue(Object value) {
		Object ret = getValue();
		this.value = (Datatype) value;
		return ret;
	}

	/* (non-Javadoc)
	 * @see session.datatype.Datatype#writeExternal(session.datatype.DatatypeOutput)
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		((Datatype) getValue()).writeExternal(out);
		((Datatype) getKey()).writeExternal(out);
	}

	/* (non-Javadoc)
	 * @see session.datatype.Datatype#readExternal(session.datatype.DatatypeInput)
	 */
	public void readExternal(ObjectInput in) throws IOException {
		try {
			value.readExternal(in);
			key.readExternal(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
