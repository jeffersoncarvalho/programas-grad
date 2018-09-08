/* file name  : Port.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 16, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package model;

import session.datatype.Datatype;





/**
 * Interface used to define classes that manipulate simulation data which are exchanged between models. 
 * Every <tt>Datatype</tt> object is always passed by reference. The <code>Datatype</code> interface serves to identify 
 * objects whose methods may be invoked from a non-local simulation session.  Any object that is a remote object must 
 * directly or indirectly implement this interface. Only those methods specified in a "remote interface", an interface that 
 * extends <code>Datatype</code> are available remotely. <p>
 * 
 * A port should provide a constructor that specifies the number of 
 * terms of each block of the iterator. A zero value for the number of blocks indicates that no blocks will be created by the 
 * iterator.  A negative value indicates that the number of terms in a block is computed automatically. Otherwise, a positive 
 * value indicates that the iteration is performed by blocks.  The <tt>nextTerm</tt> iterates in this block while it has a term 
 * to be iterated. <p>In addition, this interface defines a couple of methods to   <p>Implementation classes can implement any 
 * number of remote interfaces and can extend other remote implementation classes. <i>Forr�</i> provides some convenience classes 
 * that remote object implementations can extend which facilitate remote object creation.  These classes are 
 * <code>AbstractDatatype</code>.
 * 
 * @author  Ricardo Corrêa (correa@lia.ufc.br)
 */
public interface EnumPort extends ForroPort {
	
    /**
     * Returns an array containing all of the elements in this collection.  If
     * the collection makes any guarantees as to what order its elements are
     * returned by its iterator, this method must return the elements in the
     * same order.<p>
     *
     * The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.<p>
     *
     * This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return a Datatype object whose <tt>getContents</tt> method returns an <tt>AbstractDatatypeArray</tt> 
     *         containing all of the elements in this collection
     */
    Datatype toArray();
    /**
     * The implementation of this method depends on the result of <tt>isUses</tt>. If it returns <tt>true</tt>,
     * then the implementation should get the values according to the data transfer policy of the link
     * associated with the binding of this port. Otherwise, the implementation should send the values
     * according to the mentioned data transfer policy. This means that the <tt>next</tt> method returns
     * arrays of elements.
     * 
     * @return an identifier for the iterator of the view of a model corresponding to this port.
     */
    Datatype startIterator();
    /**
     * Iterates on this set view. If a block exists and is not already completely iterated, then the next term
     * is picked up in this block. Otherwise, the next term comes from the model component.
     * 
     * @param iteratorId the identification of a previously started iterator
     * @return if the invocation <tt>iteratorId.equals(i)</tt> returns <tt>true</tt> for some identification <tt>i</tt>, then 
     *         returns the next element on the iterator <tt>i</tt>; otherwise returns <tt>null</tt>
     */
    Datatype nextTerm(Datatype iteratorId);
    /**
     * If there is a block, checks for a next term in the block. If it return <tt>false</tt> in this case, the
     * block is released. If there is no block, checks for a next term in the model component.
     * 
     * <p>The non-blocking behavior is given by the packed iteration principle
     * 
     * @param iteratorId the identification of a previously started iterator
     * 
     * @return if the invocation <tt>iteratorId.equals(i)</tt> returns <tt>true</tt> for some <tt>i</tt>, then 
     *         returns whether the iterator <tt>i</tt> has a next element; otherwise returns <tt>null</tt>
     */
    boolean hasNextTerm(Datatype iteratorId);
    /**
     * Gets a term associated with a specified key.
     * 
     * @param key of the map view.
     * 
     * @return the value associated with the specified key.
     */
    Datatype getTerm(Datatype key);
	/**
	 * Gets a term with a default property.
	 * @return  	the term chosen according to a default property specified by the implementation of this interface
	 * @uml.property  name="term"
	 * @uml.associationEnd  
	 */
	Datatype getTerm();
	/**
	 *    Inserts a specified term associated with a specified key.
	 *    
	 *    @param key the key associated to the term to be inserted
	 *    @param term	a term to be inserted
	 *    	 to this
	 *  
	 */
	void addTerm(Datatype key, Datatype term);
    /**
     * The number of terms returned by the iterator.
     * 
     * @return the size of this view
     */
    int size();
	/**
	 *    Indicates if there are at least one term in this view.
	 *    
	 *    @return	<tt>true</tt> if there are no
	 *    	 terms in this and
	 *    	 <tt>false</tt> otherwise
	 *  
	 */
	boolean isEmpty();
	/**
	 *    Keeps all terms that are greater than the specified value according to the order defined on the
	 * terms of this view.
	 *    
	 *    @param d	the value used for the pruning
	 *    	 comparison. It must implement the <tt>Comparable</tt> interface.
	 *  
	 */
	void tailTerms(Datatype d);
}
