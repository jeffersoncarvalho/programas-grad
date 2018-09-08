/* file name  : NonBlockingWorkspaceDriver.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Aug 16, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.workspace;

import forrocore.Middleware;


/**
 * A Workspace Driver that implements non-blocking method invocations as the model of computation. 
 * In a non-blocking method invocation, the Link creates and returns a Datatype object <tt>result</tt> and invokes the
 * remote method. The contents of <tt>result</tt> is not set before it is returned by the Link, which means the
 * <tt>result.getContents()</tt> returns <tt>result</tt>.
 * Upon completion of the remote method execution, the result of the remote method invocation is written in 
 * <tt>result</tt>, becoming available to the involker via the <tt>getContents</tt> method.
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public class NonBlockingWorkspaceDriver extends AbstractWorkspaceDriver  {
	
	/**
	 * Creates a non-blocking workspace driver with a specified name and connected to a specified location driver.
	 * This location driver is used to performed the method invocations received by its links.
	 * @param mw TODO
	 */
	public NonBlockingWorkspaceDriver(Middleware mw) {
		super(mw);
	}
}
