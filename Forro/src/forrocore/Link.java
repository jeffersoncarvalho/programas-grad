/* file name  : Link.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 15, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package forrocore;

import session.datatype.Datatype;
import ccacore.Port;
import exceptions.CCAException;



/**
 * A server-side entity containing a method that dispatches calls to the remote object implementation.     
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public interface Link extends Port {
	/**
	 * Invokes a specified method of a service associated with a specified user. Since it throws the 
	 * <tt>RemoteException</tt>, it can be remotely invoked with Java's RMI protocol.
	 * 
	 * <p>This method applies the security policy to check the session identity.
	 * @param portName TODO
	 * @param methodName name of the method being invoked <tt>modelName.portName.methodName</tt>
	 * @param parameterTypes TODO
	 * @param args arguments to be passed to the specified method
	 * @param invoker TODO
	 */
	void invokeAtProvidesPort(String portName, String methodName, Class[] parameterTypes, Datatype[] args, String invoker)  throws CCAException;

	/**
	 * Sends a Datatype parameter as a result of an invocation received by the <tt>sendInvocation</tt> method. 
	 * Since it throws the <tt>RemoteException</tt>, it can be remotely invoked with Java's RMI protocol.
	 * 
	 * <p>This method should be used to implement non-blocking port method invocations.
	 * @param data the Datatype object corresponding to the result of the specified method
	 */
	void returnToUsesPort(Datatype data) throws CCAException;
}
