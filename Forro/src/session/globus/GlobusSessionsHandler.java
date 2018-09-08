/* file name  : GlobusSessionLauncher.java
 * authors    : Ricardo Corrï¿½a (correa@lia.ufc.br)
 * created    : Feb 14, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.globus;

import java.rmi.RemoteException;
import java.security.cert.Certificate;

import session.FrameworkDriver;
import session.SessionsHandler;

/**
 * TODO Describe this type.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public class GlobusSessionsHandler implements SessionsHandler, GlobusCompliant {

	/**
	 * 
	 */
	public GlobusSessionsHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see session.AbstractSessionHandler#getHandler(java.lang.String)
	 */
	public FrameworkDriver getRemoteFramework(String locationName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see session.SessionsHandler#insecureLogOn(java.lang.String, java.lang.String)
	 */
	public boolean insecureLogOn(String sdriverClassName, String sessionName) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see session.SessionsHandler#secureLogOn(java.lang.String, java.security.cert.Certificate)
	 */
	public boolean secureLogOn(String sdriverClassName, Certificate sessionCert) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see session.SessionsHandler#invokeAt(java.lang.String, java.lang.Object[], java.lang.String, byte[])
	 */
	public Object invokeAt(String methodName, Object[] args, String sessionName, byte[] sessionSig) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



}
