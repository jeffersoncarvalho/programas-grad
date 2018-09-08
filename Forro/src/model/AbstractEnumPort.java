/* file name  : AbstractPort.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 24, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import session.datatype.Datatype;
import session.datatype.DatatypeFactory;
import session.datatype.DatatypeInteger;



/**
 * This class provides a skeletal implementation of the <tt>Port</tt>
 * interface to minimize the effort required to implement this
 * interface. It provides the iterator methods that allow the enumeration of the terms of the view
 * represented by this port. In addition, this abstract class handles all iterators created.
 * A concrete implementation must provide the <tt>startIterator</tt>, <tt>size</tt> and <tt>toArray</tt> methods.
 * In particular, the <tt>startIterator</tt> method establishes the packed iterator implementation.
 * 
 * <p>The <tt>isEmpty()</tt> method is implemented using the <tt>size()</tt> method. A concrete implementation
 * may override this method in order to increase efficiency.
 * 
 * <p>The methods <tt>addTerm(Datatype key, Datatype term)</tt>, <tt>getTerm()</tt>, <tt>getTerm(Datatype key)</tt>,
 * <tt>tailTerms(Datatype d)</tt> throw the <tt>UnsupportedOperationException</tt>. 
 * 
 * @author Ricardo Correa (correa@lia.ufc.br)
 */
public abstract class AbstractEnumPort extends AbstractForroPort implements EnumPort {

	/**
	 * Iterators waiting for the non-blocking request to terminate
	 * @uml.property  name="pendingIteratorMap"
	 * @uml.associationEnd  qualifier="iteratorId:session.datatype.Datatype java.util.Iterator"
	 */
	private Map pendingIteratorMap = new HashMap();
	/**
	 * @uml.property  name="datatypeFactory"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private DatatypeFactory datatypeFactory = DatatypeFactory.getInstance();
	/**
	 * The map for the active packed iterators. The entries compare based on the integer value associated with each iterator.
	 * @uml.property  name="iteratorMap"
	 * @uml.associationEnd  qualifier="iteratorId:session.datatype.Datatype java.util.Iterator"
	 */
	private Map iteratorMap = new HashMap() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		final class IntegerMapElement {
			private final Datatype k;

			private IntegerMapElement(Object k) {
				super();
				this.k = (Datatype) k;
			}

			public boolean equals(Object obj) {
				return obj == this || k.equals(((IntegerMapElement) obj).k); 
			}

			public int hashCode() {
				return ((DatatypeInteger) k.getContents()).getValue();
			}
		}

		public Object put(Object k, Object v) {
			return super.put(new IntegerMapElement(k), v);
		}
		
		public Object get(Object k) {
			return super.get(new IntegerMapElement(k));
		}
		
		public boolean containsKey(Object k) {
			return super.containsKey(new IntegerMapElement(k));
		}
		
		public Object remove(Object key) {
			return super.remove(new IntegerMapElement(key));
		}
	};

	/**
	 * The properties of the binding of this port
	 * @uml.property  name="bindingProp"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private BindingProperties bindingProp;
	/**
	 * @uml.property  name="blockSize"
	 */
	private int blockSize;
	
	/**
	 * Creates the abstract port connected to a specified model driver.
	 * @param name the port's name
	 * @param bindingProp TODO
	 * @param invoker the model invoker
	 */
	public AbstractEnumPort(String name, BindingProperties bindingProp) {
		super(name);
		this.bindingProp = bindingProp;
		this.blockSize = bindingProp.getPackSize();
	}
	
	/**
	 * Creates the abstract port connected to a specified model driver.
	 * @param name the port's name
	 * @param bindingProp TODO
	 * @param invoker the model invoker
	 */
	public BindingProperties getBindingProperties() {
		return this.bindingProp;
	}

	
	/* (non-Javadoc)
	 * @see session.Port#iterator()
	 */
	protected Datatype addIterator(Datatype itKey, Iterator it) {
		synchronized (iteratorMap) {
			if (itKey.getContents() == itKey)
				pendingIteratorMap.put(itKey, it);
			else
				iteratorMap.put(itKey, it);
		}
		return itKey;
	}

	private Iterator retrieveIterator(Datatype iteratorId) {
		Iterator iter;
		
		synchronized (iteratorMap) {
			if (pendingIteratorMap.containsKey(iteratorId)) {
				while (iteratorId.getContents() == iteratorId);
				iter = (Iterator) pendingIteratorMap.remove(iteratorId);
				iteratorMap.put(iteratorId, iter);
			}
			else iter = (Iterator) iteratorMap.get(iteratorId); 
		}
		return iter;
	}
	
	private void removeIterator(Datatype iteratorId) {
		synchronized (iteratorMap) {
			iteratorMap.remove(iteratorId);
			datatypeFactory.recycle(iteratorId);
		}
	}
	
	/* (non-Javadoc)
	 * An iterator is not released by the garbage collector unless this method returns <tt>false</tt>.
	 * @see session.Port#hasNext(int)
	 */
	public boolean hasNextTerm(Datatype iteratorId) {
		boolean ret;
		Iterator it = retrieveIterator(iteratorId);
		
		if (it == null)
			return false;
		
		ret = it.hasNext();
		if (!ret)
			removeIterator(iteratorId);
		
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see session.Port#next(int)
	 */
	public Datatype nextTerm(Datatype iteratorId) {
		return (Datatype) retrieveIterator(iteratorId).next();
	}
}
