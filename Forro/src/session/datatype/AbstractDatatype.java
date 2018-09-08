/*
 * Created on May 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package session.datatype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


/**
 * This abstract class provides a synchronized <tt>setContents</tt> method, which
 * can be used by a Link implementation to provide non-blocking remote invocations.
 * The <tt>getContents</tt> provided by this abstract class is also synchronized. 
 * 
 * <p>This datatype is externalized with the following format:<p>
 * <tt>if (contents == null) out.writeUTF("contents.null");</tt><p>
 * <tt>else if (contents == this) out.writeUTF("contents.this");</tt><p>
 * <tt>else out.writeUTF("contents.yes"); out.writeUTF(contents.getClass().getName()); contents.writeExternal(out);</tt>
 * 
 * <p>This class overrides the <tt>equals(obj)</tt> method such as it returns <tt>true</tt> if<p>
 * <tt>obj == this || obj == contents || (contents != this && contents.equals(obj))</tt>
 *
 *  
 * @author correa
 */
public abstract class AbstractDatatype implements Datatype {

	/**
	 * The contents of this datatype.
	 * @uml.property  name="contents"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Datatype contents = this;
	/**
	 * Hash code for these objects.
	 */
	private static int hashCode = 0;
	/**
	 * Mutual exclusion for hashCode
	 */
	private static final Object semaphore = new Object(); 
	/**
	 * The hash code of this object
	 * @uml.property  name="myHashCode"
	 */
	private int myHashCode;
	/**
	 * The datatype factory
	 * @uml.property  name="datatypeFactory"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private transient final DatatypeFactory datatypeFactory = DatatypeFactory.getInstance();
	
	/**
	 * 
	 */
	public AbstractDatatype() {
		super();
		synchronized (semaphore) {
			myHashCode = hashCode++;
		}
	}
	
	/* (non-Javadoc)
	 * @see model.Datatype#getContents()
	 */
	/**
	 * @return  Returns the contents.
	 * @uml.property  name="contents"
	 */
	public synchronized Datatype getContents() {
		return contents;
	}

	/* (non-Javadoc)
	 * @see session.datatype.Datatype#waitContents()
	 */
	public synchronized Datatype waitContents() {
		if (contents == this)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return contents;
	}
	
	/* (non-Javadoc)
	 * @see session.datatype.Datatype#setContents()
	 */
	public synchronized Boolean setContents(Datatype cont) {
		if ((contents != this) && (cont != this))
			return false;
		
		contents = cont;
		notifyAll();
		return true;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (!(obj instanceof Datatype))
			return false;
		
		return (contents != null && contents != this && contents.equals(((Datatype) obj).getContents()));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return myHashCode;
	}
	
	public void writeExternal(ObjectOutput out) throws IOException {
		if (contents == null)
			out.writeUTF("contents.null");
		else if (contents == this)
			out.writeUTF("contents.this");
		else {
			out.writeUTF("contents.yes");
			out.writeUTF(contents.getClass().getName());
			contents.writeExternal(out);
		}
	}

	public void readExternal(ObjectInput in) throws IOException {
		String contentsStr = in.readUTF();
		if (contentsStr.equals("contents.this")) contents = this;
		if (contentsStr.equals("contents.yes")) {
			String contentsClassName = in.readUTF();
			contents = datatypeFactory.newInstance(contentsClassName);
			try {
				contents.readExternal(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
