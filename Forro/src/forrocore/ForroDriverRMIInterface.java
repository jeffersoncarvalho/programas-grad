/**
 * 
 */
package forrocore;

import java.rmi.Remote;
import java.rmi.RemoteException;

import exceptions.CCAException;

/**
 * Interface for a RMI service that makes the "execute" calls
 * @author Gisele 
 *
 */
public interface ForroDriverRMIInterface extends Remote  {
	
	/**
	 * Execute the commands 
	 * @param sessionName session name
	 * @throws CCAException TODO
	 */
	public void execute(String sessionName, String locationName, String commandName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException;

	/**
	 * Test the RMI 
	 * @param 
	 */
	public String sayHello() throws RemoteException;


}
