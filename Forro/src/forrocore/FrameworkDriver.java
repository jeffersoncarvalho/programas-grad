/* project    : forro2.0
 * file name  : FrameworkDriver.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br), Francisco de Carvalho Junior (heron@lia.ufc.br), Gisele Ara�jo (gisele@lia.ufc.br)
 * created    : 03/08/2006
 * copyright  : Federal University of Cear�, Brazil
 *
 * modifications:
 *
 */

package forrocore;

import java.rmi.Remote;
import java.rmi.RemoteException;

import exceptions.CCAException;


/**
 * A special object that runs at each location responsible for launching the framework at this location,
 * for logging on the sessions and for executing the end user commands. It is not itself a component.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 *
 */
public interface FrameworkDriver extends Remote {
	/**
	 * Logon a specified session with its name. It starts the corresponding session driver.
	 * @param sessionName the name of this session
	 * 
	 * @throws RemoteException TODO
	 */
	void logOn(String sessionName) throws RemoteException;
	void logOff(String sessionName) throws RemoteException;
	/**
	 * Adds a location to the environment of the specified session. The added location must have been 
	 * registered itself.
	 * 
	 * @param sessionName the session name
	 * @param locationName the location name
	 * @param registeredName the name the framework driver of the specified location has been registered
	 * 
	 * @throws RemoteException TODO
	 */
	void addLocation(String sessionName, String locationName, String registeredName) throws RemoteException;
    /**
     * Executes the specified end user command with the specified keys and values.
     * 
     * @param sessionName in which the command is to be executed
     * @param locationName where to execute the command. If it is remote, this object re-directs it.
     * @param commandName the command to be executed
     * @param optionKey an array with the keys of the specified command
     * @param optionValue an array with the value of each specified key.
     * @throws RemoteException TODO
     * @throws CCAException TODO
     */
	void execute(String sessionName, String locationName, String commandName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException; 
}
