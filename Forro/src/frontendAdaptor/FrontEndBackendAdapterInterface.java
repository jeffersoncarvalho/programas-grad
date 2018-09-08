package frontendAdaptor;

import java.rmi.RemoteException;

import ccacore.ComponentID;
import ccacore.ConnectionID;
import exceptions.CCAException;


/** 
 *    FrontEndBackendAdapterInterface is an interface  
 * Other uses are generic application development environments for CCA 
 * applications. 
 * <p>Each of the fundamental component architecture pieces
 *    (instances of Component, Port, and Connection) may have
 *    an associated TypeMap of properties managed by the framework.
 *    The standardized keys in the properties of a Port are documented
 *    in Services.getPortProperties().
 *    The standardized keys in the properties of a Component and Connection
 *    are documented below.
 *  </p>
 *  <p>For connection, thus far:
 *    <pre>
 *    Key BuilderService        value           meaning
 *    cca.isInUse boolean         true if there have been more successful
 *                               getPort than releasePort calls for the
 *                               connection at the the time 
 *                               properties were fetched.
 *   </pre>
 *   </p>
 *  <P>For component, thus far:
 *   <pre>
 *    Key                 value           meaning
 *    cca.className      String          component type
 *   </pre>
 *  </p>
 */       

public interface FrontEndBackendAdapterInterface {
	
    /**
     *  Creates a Component.
     */
    void initialize(String sessionName) throws RemoteException, CCAException;

		
    /**
     *  Creates a Component.
     */
    ComponentID createComponent(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException;

    /**
     *  Connect a port Uses with a port Provides.
     */
    ConnectionID connectComponent(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException;

    /**
     *  Executes a Go command.
     */
    Boolean executeGoCommand(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException;

    /**
     *  Creates a workspace.
     */
    Boolean createWorkspace(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException;
    
    /**
     *  Creates a location.
     */
    Boolean createLocation(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException;

    /**
     *  Creates a link.
     */
    Boolean createLink(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException;

    /**
     *  Creates a relation between location and workspace.
     */
    Boolean createRelationLocationWorkspace(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException;
	    
    /**
     *  Creates a relation between location and workspace.
     * @param forroName TODO
     * @param sessionName TODO
     */
    Boolean initRepository(String forroName, String sessionName) throws RemoteException, CCAException;
	    
	    
	} // end interface FrontEndBackendAdapterInterface

