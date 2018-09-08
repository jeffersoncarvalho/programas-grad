package frontendAdaptor;

import java.rmi.RemoteException;

import exceptions.CCAException;
import forrocore.ForroDriverRMIInterface;

/** 
 *    FrontEndForroAdapter is a class that implements the methods
 *    for a frontend communicates directly with the Forro framework. 
 *     
 */     
public class FrontEndForroAdapter implements ForroDriverRMIInterface {

	/*
	 * Constructor
	 */
	 public FrontEndForroAdapter () {
		 
	 }
//	   /**
//     *   Creates a connection between ports on component user and 
//     *   component provider. Destroying the ConnectionID does not
//     *   cause a disconnection; 
//     *   
//     *   This method does not set the connection properties. For this reason,
//     *   the connection created may be not effectively useful before the
//     *   <tt>setConnectionProperties</tt> is invoked, in which case this 
//     *   method is also responsible for generating <tt>ConnectionEvent</tt>.
//	 * @param values  
//     * param user the component that registers the user port of the connection created
//     * param usingPortName the name of the uses port registered by the user
//     * param provider the component that holds the user port of the connection created
//     * param providingPortName the name of the provides port defined by the user
//     */
//		public ConnectionID connectComponent(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
//		final int locIdx = locationName.indexOf('.');
//		String host[]= null;
//		host[0] = locationName.substring(0, locIdx); 
//
//		
//		// Call remote method	
//		if (!(isLocalCommand(sessionName, locationName))) {
//			try {
//				ForroDriverRMIInterface service= callRemote(host, sessionName, locationName, optionKey, optionValue);
//				service.connectComponent(sessionName, locationName, optionKey, optionValue);	
//			}
//			catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
//		} else { // Creates a connection between ports 
//	
//			// Create a connection between a user port and a provider port
//			String componentProviderName = null;
//			String componentUserName = null;
//			String providesPortName = null;
//			String usesPortName = null;
//			SessionDriver sessionDriver =  ForroDriver.getSessionDriverMap().get(sessionName);	
//			
//			// Find the keys
//			for (int i= 0; i < 4; i ++ ) {
//				if (optionKey[i].equals(ForroDriver.providerComponentNameLabel)) {
//					componentProviderName = optionValue[i];
//				} else if (optionKey[i].equals(ForroDriver.providesPortNameLabel)) {
//					providesPortName = optionValue[i];
//				} else	if (optionKey[i].equals(ForroDriver.userComponentNameLabel)) {
//					componentUserName = optionValue[i];
//				} else	if (optionKey[i].equals(ForroDriver.usesPortNameLabel)) {
//					usesPortName = optionValue[i];
//				}
//
//			}
//			
//			
//			System.out.println("\n Before create connection**********: " + providesPortName + " - " + usesPortName );
//			// Inserir no arquivo o nome da instancia
//			ComponentID componentProviderID = sessionDriver.getComponentID(componentProviderName);
//			ComponentID componentUserID = sessionDriver.getComponentID(componentUserName);
//			final EventType eUses = EventTypes.newEventType(componentUserID, EventType.Connected);
//				
//			ConnectionEventListener conEvt = (ConnectionEventListener)  sessionDriver.lookupServices(sessionName);
//			sessionDriver.addConnectionEventListener(eUses, conEvt); 
//			
//			ConnectionID connection = sessionDriver.connect(componentUserID, usesPortName, componentProviderID, providesPortName);
//			
//			System.out.println("\n After create connection!!!!!!!!!!!*: " + providesPortName + " - " + usesPortName + " - ConnectionID " +
//					connection);
//
//			return connection;
//		}
//		
//		return null;
//	}
//
//	/**
//	*  Creates an instance of a CCA component of the type defined by the 
//	*  string className.  The string classname uniquely defines the
//	*  "type" of the component, e.g. doe.cca.Library.GaussianElmination. 
//	*/		
//	public ComponentID createComponent(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
//		final int locIdx = locationName.indexOf('.');
//		String host[]= null;
//		host[0] = locationName.substring(0, locIdx); 
//
//		
//		// Call remote method	
//		if (!(isLocalCommand(sessionName, locationName))) {
//			try {
//				ForroDriverRMIInterface service = callRemote(host, sessionName, locationName, optionKey, optionValue);
//				service.createComponent(sessionName, locationName, optionKey, optionValue);	
//			}
//			catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
//		} else { // Create the component	
//			
//			String instanceName = null;
//			String componentName = null;
//			String locName = null;
//			SessionDriver sessionDriver =  ForroDriver.getSessionDriverMap().get(sessionName);
//
//			
//			// Find the keys
//			for (int i= 0; i < 3; i ++ ) {
//				if (optionKey[i].equals(ForroDriver.componentNameLabel)) {
//					componentName = optionValue[i];
//				} else if (optionKey[i].equals(ForroDriver.instanceNameLabel)) {
//						instanceName = optionValue[i];
//				} else	if (optionKey[i].equals(ForroDriver.componentLocationNameLabel)) {
//					locName = optionValue[i];
//				}
//
//			}
//			
//			// Inserir no arquivo o nome da instancia
//			ComponentID componentID = sessionDriver.createInstance(instanceName, componentName, null);
//							
//			System.out.println("\n After create component############: " + componentName + " location: " + locName);
//			
//			return componentID;
//		}
//		
//		return null;
//	}
//	
//	
//	/**
//	 *  Creates a link  
//	*/		
//	public Boolean createLink(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
//		
//		final int locIdx = locationName.indexOf('.');
//		String host[]= null;
//		host[0] = locationName.substring(0, locIdx); 
//
//		
//		// Call remote method	
//		if (!(isLocalCommand(sessionName, locationName))) {
//			try {
//				ForroDriverRMIInterface service = callRemote(host, sessionName, locationName, optionKey, optionValue);
//				service.createLink(sessionName, locationName, optionKey, optionValue);	
//			}
//			catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
//		} else { //		 Keep the links names in the link map
//			
//			
//			String workspaceFull = optionValue[0].substring(0, optionValue[0].lastIndexOf(".")); 
//			String link = optionValue[0].substring(optionValue[0].lastIndexOf(".")+1, optionValue[0].length());
//			
//			// Keep the workspaces names in the workspace map
//			if ((optionValue[0].indexOf(".")) != (optionValue[0].lastIndexOf(".")))
//			{
//				try {
//					AbstractWorkspaceDriver workspaceDriver = ForroDriver.getWorkspaceDriverMap().get(workspaceFull);
//					//	Keep the locations names in the location map
//					workspaceDriver.addAsynchronousLink(link);
//					ForroDriver.getLinkMap().put(optionValue[0], workspaceDriver);
//				} catch (CCAException e) {
//					e.printStackTrace();
//				}
//				
//				System.out.println("Link created: " + link + "- Workspace: " +  workspaceFull);
//				return true;
//			}
//			else {
//				// Keep the link names in the link map
//				ForroDriver.getLinkMap().put(optionValue[0], null);
//				System.out.println("Link loaded: " + optionValue[0] + " - Link: " + link);
//				return true;
//			}					
//		}
//		
//		return false;
//	}
//
//
//	/**
//	 *  Creates a location  
//	*/	
//	public Boolean createLocation(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
//		final int locIdx = locationName.indexOf('.');
//		String host[]= null;
//		host[0] = locationName.substring(0, locIdx); 
//
//		
//		// Call remote method	
//		if (!(isLocalCommand(sessionName, locationName))) {
//			try {
//				ForroDriverRMIInterface service = callRemote(host, sessionName, locationName, optionKey, optionValue);
//				service.createLocation(sessionName, locationName, optionKey, optionValue);	
//			}
//			catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
//		} else { 	// Create a middleware and read the port number
//			int locationNameBegin = optionValue[0].indexOf(".",0) + 1;
//			int locationNameEnd = optionValue[0].lastIndexOf(".");
//			String middlewareType = optionValue[0].substring(0, locationNameBegin-1);
//			String location = optionValue[0].substring(locationNameBegin, locationNameEnd);
//			String port = optionValue[0].substring(locationNameEnd+1,optionValue[0].length());
//			
//			System.out.println();
//			
//			// Keep the workspaces names in the workspace map
//			if (middlewareType.equalsIgnoreCase("SocketMiddleware"))
//			{
//				try {
//					Middleware middleware = new SocketMiddleware((new Integer(port)).intValue());
//					//	Keep the locations names in the location map
//					ForroDriver.getLocationMap().put(optionValue[0], middleware);
//					System.out.println("Location created: " + optionValue[0] + "Middleware type: " +  middlewareType + " - Location: " + location + " - Porta: " + port);
//					return true;
//				}
//				catch (IOException e) {
//					e.printStackTrace();
//				}
//				
//			}
//			else {
//				// Keep the locations names in the location map
//				ForroDriver.getLocationMap().put(optionValue[0], null);
//				System.out.println("Location loaded: " + optionValue[0] + " - Location: " + location);
//				return true;
//				
//			}	
//		}	
//		return false;
//	}
//
//	
//	/**
//	 *  Creates a relation between location and workspace  
//	*/	
//	public Boolean createRelationLocationWorkspace(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
//		final int locIdx = locationName.indexOf('.');
//		String host[]= null;
//		host[0] = locationName.substring(0, locIdx); 
//
//		
//		// Call remote method	
//		if (!(isLocalCommand(sessionName, locationName))) {
//			try {
//				ForroDriverRMIInterface service = callRemote(host, sessionName, locationName, optionKey, optionValue);
//				service.createRelationLocationWorkspace(sessionName, locationName, optionKey, optionValue);	
//			}
//			catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
//		} else { 							// Keep the location and workspace names
//			String locationFull = optionValue[0].substring(0, optionValue[0].indexOf("|")); 
//			String workspaceFull = optionValue[0].substring(optionValue[0].indexOf("|")+1, optionValue[0].length());
//			
//			// Keep the workspaces names in the workspace map
//			if (optionValue[0].indexOf("|") > 0)
//			{
//				try {
//					AbstractWorkspaceDriver workspaceDriver = ForroDriver.getWorkspaceDriverMap().get(workspaceFull);
//					//	Keep the locations names in the location map
//                    Middleware locationDriver =  ForroDriver.getLocationMap().get(locationFull);				
//					workspaceDriver.addLocation(locationFull, locationDriver);
//					
//					System.out.println("Relation created. Location: " + locationFull + "- Workspace: " +  workspaceFull);
//					return true;
//				
//				} catch (CCAException e) {
//					e.printStackTrace();
//				}
//				
//			}
//			else {
//				// Shows the relations which had been written in a wrong way. 
//				System.out.println("Relation not loaded: " + optionValue[0]);
//			}						
//
//		}	
//		return false;	
//	}
//
//	/**
//	 *  Creates a workspace  
//	*/	
//	public Boolean createWorkspace(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
//		final int locIdx = locationName.indexOf('.');
//		String host[]= null;
//		host[0] = locationName.substring(0, locIdx); 
//
//		
//		// Call remote method	
//		if (!(isLocalCommand(sessionName, locationName))) {
//			try {
//				ForroDriverRMIInterface service = callRemote(host, sessionName, locationName, optionKey, optionValue);
//				service.createWorkspace(sessionName, locationName, optionKey, optionValue);	
//			}
//			catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
//		} else { // Creates workspace 
//				// Keep the workspaces names in the workspace map
//			if (optionValue[0].equalsIgnoreCase("session.workspace.BlockingWorkspaceDriver"))
//			{
//				AbstractWorkspaceDriver workspaceDriver = new session.workspace.BlockingWorkspaceDriver(optionValue[0]);
//				ForroDriver.getWorkspaceDriverMap().put(optionValue[0], workspaceDriver);
//				System.out.println("Blocking workspace created: " + optionValue[0]);
//			}
//			else
//			{
//				if (optionValue[0].equalsIgnoreCase("session.workspace.globus.BlockingWorkspaceDriver"))
//				{
//					AbstractWorkspaceDriver workspaceDriver = new session.workspace.globus.BlockingWorkspaceDriver(optionValue[0]);
//					ForroDriver.getWorkspaceDriverMap().put(optionValue[0], workspaceDriver);
//					System.out.println("Globus workspace created: " + optionValue[0]);
//				}
//				else
//				{	
//					ForroDriver.getWorkspaceDriverMap().put(optionValue[0], null);
//					System.out.println("workspace loaded: " + optionValue[0]);
//				}
//				return true;
//			}
//			
//		}	
//		return false;	
//	}
//
//	/**
//	 *  Execute a Go command  
//	*/	
//	public Boolean executeGoCommand(String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
//		final int locIdx = locationName.indexOf('.');
//		String host[]= null;
//		host[0] = locationName.substring(0, locIdx); 
//
//		
//		// Call remote method	
//		if (!(isLocalCommand(sessionName, locationName))) {
//			try {
//				ForroDriverRMIInterface service = callRemote(host, sessionName, locationName, optionKey, optionValue);
//				service.executeGoCommand(sessionName, locationName, optionKey, optionValue);	
//			}
//			catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
//		} else { // Execute a go command
//			String componentName = null;
//			String portName = null;
//			
//			
//			// Find the keys
//			for (int i= 0; i < 2; i ++ ) {
//				if (optionKey[i].equals(ForroDriver.componentNameLabel)) {
//					componentName = optionValue[i];
//				} else if (optionKey[i].equals(ForroDriver.portNameLabel)) {
//					portName = optionValue[i];
//				}
//
//			}
//			
//			
//			System.out.println("\n Before GO command n**********: " + componentName);
//			// Inserir no arquivo o nome da instancia
//			
//
//			Port portB = ForroDriver.getSessionDriverMap().get(sessionName).lookupPort(componentName, portName);
//			GoPort goB = (GoPort) portB;
//			System.out.println("After GO command n. **********: " + componentName);
//			System.out.println("\n ---------------RESPONSE ##########################:  ");
//			goB.go();
//			System.out.println("\n ##################################################  ");
//
//
//		
//			
//		}	
//		return false;	
//	}
//
//	public void initialize(String sessionName) throws RemoteException, CCAException {
//		ForroDriver.getForrodriver().logOn(sessionName);
//		
//	}
//	
//	
//	
//    ///////////////////////////////////////////////////////////////	
//	/**
//	 * Call a remote method to execute the commands 
//	 * @param sessionName session name
//	 */
//	public ForroDriverRMIInterface callRemote(String host[], String sessionName, String locationName, String[] optionKey, String[] optionValue) throws RemoteException	{
//		try {
//			// Check for hostname argument
//			if (host.length != 1)		{
//				System.out.println
//				("Syntax error - ForroDriverClient host");
//				System.exit(1);
//			}
//
//			// Assign security manager
//			if (System.getSecurityManager() == null)		{
//				System.setSecurityManager
//				(new RMISecurityManager());
//			}
//
//			// Call registry for ForroDriverRMIInterface
//			ForroDriverRMIInterface service = (ForroDriverRMIInterface) Naming.lookup
//				("rmi://" + host[0] + "/ForroDriverRMIInterface");
//
//            return service;
//			
//		
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (NotBoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}
//	
//	
//	
//	Boolean isLocalCommand(String sessionName, String locationName) {
//		//	If this location is different that the given location name so it is necessary to call remotely the execute method. 
//		String thisLocationName = System.getProperty("java.rmi.server.hostname");
//		
//		//////////////////////////////////
//		// Verifies if the command is valid option
//		//	If this location is different that the given location name so it is necessary to call remotely the execute method. 
//		if ((thisLocationName != locationName) && (locationName != "localhost")) {
//			final int locIdx = locationName.indexOf('.');
//			final String hostName = locationName.substring(0, locIdx);
//			String host[]= null;
//			host[0] = hostName; 
//			return true;
//  		 }
//		  return false;
//		}
//
//	/**
//	 *  Init the repository executing the logOn in the specified session.  
//	*/		
//	public Boolean initRepository(String forroName, String sessionName) throws RemoteException, CCAException {
//		System.out.println("Starting the Server.........");
//		  
//		
//	    try {
//	    	ForroDriver objF = new ForroDriver();
//			ForroDriverRMIInterface stubF = (ForroDriverRMIInterface) UnicastRemoteObject.exportObject(objF, 0);
//		    // Bind the remote object's stub in the registry
//		    Registry registry = LocateRegistry.getRegistry();
//			registry.bind(forroName, stubF);
//		    System.out.println("------  Server ready.");
//	    	// Create the session and log on.
//	    	objF.logOn(sessionName);
//	    	ForroDriver.setCurrentSessionName(sessionName);
//
//		    return true;
//		} catch (AlreadyBoundException e) {
//			e.printStackTrace();
//		}
//
//
//		return false;
//	}
	public void execute(String sessionName, String locationName, String commandName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
		// TODO Auto-generated method stub
		
	}
	public String sayHello() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
	
	
	
