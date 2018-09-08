/* file name  : Datatype.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Apr 7, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.datatype;

import java.io.Externalizable;

/**
 * TODO Describe this type.
 * @author  Ricardo Corr�a (correa@lia.ufc.br)
 */
public interface Datatype extends Externalizable {
    /**
	 * It returns the object on which the methods are indeed invoked. In most cases, it is this object itself. However, when this datatype is the result of a asynchronous remote method invocation, it is another datatype object.
	 * @return  the contents of this object. If this object has no contents, it returns <tt>null</tt>.
	 * @uml.property  name="contents"
	 * @uml.associationEnd  
	 */
    Datatype getContents();
    Datatype waitContents();
	Boolean setContents(Datatype contents);
}
