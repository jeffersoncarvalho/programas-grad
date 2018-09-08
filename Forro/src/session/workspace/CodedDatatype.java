/* file name  : ByteArrayDatatype.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Apr 7, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.workspace;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import session.datatype.Datatype;
import session.datatype.DatatypeByteArray;
import session.datatype.DatatypeFactory;

/**
 * A class that codes a Datatype to be transmitted via the middleware.
 * There are two ways to code a Datatype.
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public final class CodedDatatype implements Serializable {
	/**
	 * @uml.property  name="coded"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Datatype coded;
	
	/**
	 * @uml.property  name="datatypeFactory"
	 * @uml.associationEnd  
	 */
	private transient DatatypeFactory datatypeFactory;


	/**
	 * @param data
	 */
	public CodedDatatype(Datatype data) {
		super();
		coded = data;
	}
	
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
	public void code() {
		VisibleByteArrayOutputStream byteArray = new VisibleByteArrayOutputStream();
		try {
			ObjectOutputStream out = new ObjectOutputStream(byteArray);
//			out.flush();
			out.writeUTF(coded.getClass().getName());
			coded.writeExternal(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coded = new DatatypeByteArray(byteArray.getBuf());
		System.out.println("arg coded.");
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
	
	public Datatype uncode(DatatypeFactory f) {
		try {
			datatypeFactory = f;
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(((DatatypeByteArray) coded).getValue()));
			coded = datatypeFactory.newInstance(in.readUTF());
			coded.readExternal(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coded;
	}

	/**
	 * Get the object Coded 
	 * @return coded 
	 */
	public Datatype getCoded() {
		return coded;
	}

	
	
}
