/* file name  : RMIHandlerLauncher.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 12, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;

import session.SessionsHandler;
import session.OldSessionDriver;



/**
 * This class launches the Java's RMI server that implements the <tt>SignableSessionHandler</tt> 
 * interface at its location. It must be running when its location is activated by the <i>ForroDriver</i> framework.
 * 
 * @see session.SessionsHandler
 * @see session.rmi.RMICompliant
 * @see session.rmi.RMISessionsHandler
 * 
 * @author Chanderli� Freire (cfreire@lia.ufc.br)
 */
public class RMIHandlerLauncher {
	
	public static void main(String[] args) {
		try {
			SessionsHandler handler = new RMISessionsHandler();
			if (handler != null)
				System.out.println("rmi://" + OldSessionDriver.hostName + "/" + RMICompliant.registeredName + " launched.");
			// TODO remover comentario
			Naming.rebind("rmi://" + OldSessionDriver.hostName + "/" + RMICompliant.registeredName, (Remote) handler);
			System.out.println("rmi://" + OldSessionDriver.hostName + "/" + RMICompliant.registeredName + " registered.");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}