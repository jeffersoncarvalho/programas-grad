/*
 * Created on Jul 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package session.datatype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public final class DatatypeObject extends AbstractDatatype implements Datatype {
		/**
		 * @uml.property  name="datatypeFactory"
		 * @uml.associationEnd  multiplicity="(1 1)"
		 */
		private final DatatypeFactory datatypeFactory;
		/**
		 * @uml.property  name="contents"
		 * @uml.associationEnd  
		 */
		private Datatype contents = null;

		/**
		 * @param datatypeFactory
		 */
		public DatatypeObject(DatatypeFactory datatypeFactory) {
			this.datatypeFactory = datatypeFactory;
		}

		/* (non-Javadoc)
		 * @see model.Datatype#getContents()
		 */
		public synchronized Datatype getContents() {
			return contents;
		}


		/* (non-Javadoc)
		 * @see session.datatype.Datatype#setContents(session.datatype.Datatype)
		 */
		public void setContents(Object cont) {
			contents = (Datatype) cont;
		}
		
		public void writeExternal(ObjectOutput out) throws IOException {
			if (contents != null) {
				out.writeUTF(contents.getClass().getName());
				if (contents != this) contents.writeExternal(out);
			}
			else
				out.writeUTF(" ");
		}

		public void readExternal(ObjectInput in) throws IOException {
			String contentsClassName = in.readUTF();
			if (!contentsClassName.equals(" ")) {
				if (contentsClassName.equals(getClass().getName())) contents = this;
				else {
					System.out.println("Criando datatype contents da classe " + contentsClassName + "(length = " + contentsClassName.length() + ")");
//						contents = (Datatype) Class.forName(contentsClassName).newInstance();
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
	}