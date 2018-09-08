/* file name  : ProvidesPort.java
 * authors    : Ricardo Corrï¿½a (correa@lia.ufc.br)
 * created    : Apr 2, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package model;

import java.util.Iterator;
import session.datatype.Datatype;
import session.datatype.DatatypeArray;
import session.datatype.DatatypeFactory;
import session.datatype.DatatypeInteger;



/**
 * A skeletal implementation of the <tt>Port</tt> interface to minimize the effort required to implement
 * provides ports. This includes a private inner class implementing a packed iterator and an implementation
 * of the <tt>startIterator</tt> method. 
 * A class that extends this one must implement the <tt>toArray</tt>
 * method of the <tt>Port</tt> interface, and the <tt>iterator</tt> abstract method of this class.<p>
 * 
 * The iterator implemented by this object is backed by the iterator returned by the <tt>iterator()</tt> abstract 
 * method.
 * 
 * <p>Every concrete implementation of this class must be public.
 * 
 * @see model.AbstractEnumPort
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public abstract class ProvidesPort extends AbstractEnumPort implements EnumPort {
	/**
	 * The model driver of this model component
	 * @uml.property  name="blockSize"
	 */
	private int blockSize;
	/**
	 * Number of invocations to <tt>startIterator</tt>
	 * @uml.property  name="nIterators"
	 */
	private int nIterators = 0;
	/**
	 * @uml.property  name="datatypeFactory"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private DatatypeFactory datatypeFactory = DatatypeFactory.getInstance();
	/**
	 * Creates a provides port with a specified name and connected
	 * to a specified model driver.
	 * 
	 * @param name the port's name
	 * @param bindingProp the size of each package
	 * @param invoker the model invoker
	 */
	public ProvidesPort(String name, BindingProperties bindingProp) {
		super(name, bindingProp);
		this.blockSize = bindingProp.getPackSize();
	}

	/* (non-Javadoc)
	 * @see session.Port#isUses()
	 */
	public boolean isUses() {
		return false;
	}

	/* (non-Javadoc)
	 * @see session.Port#isProvides()
	 */
	public boolean isProvides() {
		return true;
	}

	/**
	 * Returns an iterator that gives the view of the model component associated with this port.
	 * 
	 * @return the view iterator
	 */
	protected abstract Iterator iterator();
	
	/* (non-Javadoc)
	 * @see session.Port#iterator()
	 */
	public Datatype startIterator() {
		Datatype itKey = datatypeFactory.newInstance(new DatatypeInteger(nIterators++));
		return addIterator(itKey, new PackIterator(iterator(), blockSize)).getContents();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getClass().getName();
	}
	/**
	 * Iterator that packs elements returned by a specified underlying iterator in packs of a specified size. Each pack is return by the <tt>next</tt> method within an array. The elements of this array are obtained from the underlying iterator using its <tt>next</tt> iteratively. <p> The <tt>remove</tt> is not provided, throwing an exception if invoked. <p>
	 * @author  Ricardo Correa (correa@lia.ufc.br)
	 */
	private class PackIterator implements Iterator {
		/**
		 * The underlying iterator
		 */
		private Iterator it;
		/**
		 * The size of each pack returned by the <tt>next</tt> method
		 */
		private int blockSize;
		/**
		 * The pack
		 * @uml.property  name="pack"
		 * @uml.associationEnd  multiplicity="(0 -1)"
		 */
		private Datatype[] pack;
		/**
		 * Number of elements to be iterated
		 */
		private int remainingSize;
		/**
		 * The size of the current pack
		 */
		private int packSize; 
		
		/**
		 * Creates a packed iterator with the specified underlying iterator and pack size.
		 * 
		 * @param it the underlying iterator
		 * @param blockSize the size of each pack
		 */
		PackIterator(Iterator it, int blockSize) {
			super();
			this.it = it;
			this.blockSize = blockSize;
			remainingSize = size();
			packSize = this.blockSize < remainingSize ? this.blockSize : remainingSize;
			pack = new Datatype[packSize];
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return ProvidesPort.this+"PackIterator";
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return it.hasNext();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		public Object next() {
			if (remainingSize < packSize) {
				packSize = remainingSize;
				pack = new Datatype[packSize];				
			}
			int i;
			for (i = 0; (i < packSize) && it.hasNext(); i++)
				pack[i] = (Datatype) it.next();
			remainingSize -= i;
			return new DatatypeArray(pack);
		}
	}
}
