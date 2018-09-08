/* file name  : BlockingWorkspaceDriver.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Apr 3, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.workspace;

import forrocore.AbstractWorkspaceDriver;
import session.datatype.Datatype;

/**
 * A Workspace Driver that implements blocking method invocations as the model of computation. In a blocking method
 * invocation, the component invoking the method remains blocked until the remote method returns. Upon completion,
 * the result of the blocking method invocation is available to the invoker.
 * 
 * @author Ricardo Correa (correa@lia.ufc.br)
 */
public class BlockingWorkspaceDriver extends AbstractWorkspaceDriver  {
	

	/**
	 * Constructor. It assigns the name of the workspace.
	 * @param workspaceName workspace name
	 */
	public BlockingWorkspaceDriver(String workspaceName) {
		super(workspaceName);
	}	
	public Datatype nonBlockingInvocation(final String methodName, final Datatype[] args, String linkName) {
		//Datatype result = super.nonBlockingInvocation(methodName, args, linkName);
		//while (result.getContents() == result);
		
//		return result;
		return null;
	}
	
}
