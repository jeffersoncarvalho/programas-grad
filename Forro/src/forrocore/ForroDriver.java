/**
 * 
 */
package forrocore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import ccacore.Component;
import ccacore.ComponentID;
import ccacore.ConnectionEventListener;
import ccacore.ConnectionID;
import ccacore.EventType;
import ccacore.GoPort;
import ccacore.Port;
import ccacore.TypeMap;
import exceptions.CCAException;
import frontendAdaptor.FrontAdaptor;
import frontendAdaptor.FrontEndForroAdapter;
import frontendAdaptor.FrontAdaptor.FrontEndType;



/**
 * A Java's RMI implementation of the <tt>FrameworkDriver</tt> interface.
  * @author Ricardo Correa (correa@lia.ufc.br)
 *
 */
public class ForroDriver  /*extends UnicastRemoteObject*/ implements FrameworkDriver, ForroDriverRMIInterface, Serializable  {

	/**
	 * The location name where this instance is located.
	 */
	private static String thisLocationName;

	/**
	 * This instance of ForroDriver.
	 */
	private static ForroDriver forrodriver;

	/**
	 * This instance of FrontEndForroAdapter.
	 */
	private static FrontEndForroAdapter frontEndForroAdapter;

	/**
	 * The map indexed by the workspaces' names and workspaces.
	 */
	private static  Map<String, ComponentID> workspaceDriverMap = new HashMap<String, ComponentID>();

	/**
	 * The map indexed by the locations' names, and valued with the framework drivers.
	 */
	private static final Map<String, ComponentID> locationMap = new HashMap<String, ComponentID>();

	/**
	 * The map indexed by the sessions' names, and valued with the session drivers.
	 */
	private static final Map<String, SessionDriver> sessionDriverMap = new HashMap<String, SessionDriver>();

	/**
	 * The map indexed by the sessions' names, and valued with the session drivers.
	 */
	private static final Map<String, ComponentID> linkMap = new HashMap<String, ComponentID>();
	
	/**
	 * The map indexed by the locations' names, and valued with the framework drivers.
	 */
	private static final Map<String, ForroDriver> frameworkDriverMap = new HashMap<String, ForroDriver>();
	
	
	/**
	 * The map indexed by the sessions' names, and valued with the session drivers.
	 */
	private static final Map<ComponentID, Component> componentMap = new HashMap<ComponentID, Component>();


	/**
	 * The current Session Name
	 */
	private static  SessionDriver currentSessionDriver;

	
	private ForroDriverRMIInterface rmiService;
	
	public enum Command {
		createComponentCommand, createWorkspaceCommand, createLocationCommand , 
		createLinkCommand, createAddPortUsesCommand, createRegistryPortProvidesCommand,
		createRelationLocationWorkspaceCommand, connectCommand, goCommand, repositoryInitCommand
	}
	
	public static final String instanceNameKey = "-i";
	public static final String classNameKey = "-c";
	public static final String instanceNameLabel = "instanceName";
	public static final String componentNameLabel = "componentName";
	public static final String locationNameLabel = "locationName";
	public static final String locationRelationNameLabel = "locationName";
	public static final String workspaceNameLabel = "workspaceName";
	public static final String workspaceTypeLabel = "workspaceType";
	public static final String middlewareNameLabel = "middlewareName";
	public static final String linkNameLabel = "linkName";
	public static final String providerComponentNameLabel = "providerComponentName";
	public static final String userComponentNameLabel = "userComponentName";
	public static final String usesPortNameLabel = "usesPortName";
	public static final String providesPortNameLabel = "providesPortName";
	public static final String goCommandLabel = "go";
	public static final String goCommandName = "goCommand";
	public static final String commandLabel = "command";
	public static final String portNameLabel = "portName";
	
	
	

	/**
	 * Constructor with parameter
	 * @param thisLocationName
	 */
	public ForroDriver(final String thisLocationName) throws RemoteException {
		super();
		this.thisLocationName = thisLocationName;
		frameworkDriverMap.put(thisLocationName, this);
	}

	
	/**
	 * Constructor without parameter
	 * @param thisLocationName
	 */
	public ForroDriver() throws RemoteException {
		super();
		this.thisLocationName = "localhost";// System.getProperty("java.rmi.server.hostname");
		frameworkDriverMap.put(thisLocationName, this);
	}
	


	
	
	/**
	 * Executes the LogOn in a specfied session
	 * @param sessionName session name
	 */
	public void logOn(String sessionName) {
		try {			
			// Atraves do ForroDriver; o usuario faz logOn na location A, em seguida, ele acrescenta a Location B, 
			// o que faz o ForroDriver de A se conectar com o ForroDriver de B na sessão em questao.
			// OK para lancar as locations, mas, at� este ponto, nenhum componente foi criado. O logon deve 
			// incluir a criacao da sessao, e uma SessionDriver correspondente deve ser criada a cada location que 
			// � lan�ada.

			if (sessionDriverMap.containsKey(sessionName)){
				throw new CCAException("Session already exists."); 
			}

			// initialize the session entry with an empty list of active models
			ForroSessionDriver sessionDriver = new ForroSessionDriver(sessionName, thisLocationName);
			sessionDriver.registerInstance(sessionName, sessionName+"@"+thisLocationName+":"+sessionName, sessionDriver);
			ForroDriver.setCurrentSessionDriver(sessionDriver);
			System.out.println("------ SessionDriver  instantied. Session Name: " + sessionName);
					
			
			sessionDriverMap.put(sessionName, sessionDriver);
			System.out.println(" ------  Session inserted in the Session Map. ***********");
			
		
			
		} catch (final IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void logOff(String sessionName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void addLocation(String sessionName, String locationName, String registeredName) throws RemoteException {
//		FrameworkDriver dest = getRemoteFramework(locationName, registeredName);
		ForroDriver dest = getRemoteFramework(locationName, registeredName);
		dest.logOn(sessionName);
		frameworkDriverMap.put(locationName, dest);
		locationMap.put(locationName,null);
	}

	
	/**
	 * Test the RMI 
	 * @param 
	 */
	public String sayHello() throws RemoteException
	{
		
		return "Hello...";
	}
	
	

	/**
	 * Execute the commands 
	 * @param sessionName session name
	 */
	public void execute(String sessionName, String locationName, String commandName, String[] optionKey, String[] optionValue) throws RemoteException, CCAException {
	//	O programa do Jefferson deve ser usado para interpretar os comandos e chamar o metodo ForroDriver.execute. 
	//	Eu esperava que o main e o logon ficassem a cargo do ForroDriver. 
   	//	 4. ForroDriver        Atraves do ForroDriver; o usuario faz logOn na location A, em seguida, 
	// ele acrescenta a Location B, o que faz o ForroDriver de A se conectar com o ForroDriver de B 
	// na sessao em questao. OK para lan�ar as locations, mas, ate este ponto, nenhum componente foi criado. 
	// O logon deve incluir a cria��o da sess�o, e uma SessionDriver correspondente deve ser criada a cada
	// location que eh lancada.
		
		
	//	int value = -1;
		String thisLocationName = null;
		

		//////////////////////////////////
		// Verifies if the command is valid option
		try {
			Command command = Command.valueOf(commandName);
		
			SessionDriver sessionDriver =  sessionDriverMap.get(sessionName);
			/* Verifies if the session driver exists */
			if (sessionDriver==null) {
				/* Verifies if the session driver does not exist and it does not a Repository Init Command */
				if (!commandName.equalsIgnoreCase("repositoryInitCommand"))
					throw new CCAException("Session " + sessionName + "not found.");
			}
				
		   if (command == ForroDriver.Command.repositoryInitCommand)
		   {
			   this.thisLocationName = locationName;
		   }
			   
		   thisLocationName = this.getThisLocationName();
		   
			//	If this location is different that the given location name so it is necessary to call remotely the execute method. 
				if (!(locationName.equalsIgnoreCase("localhost")) && (!thisLocationName.equalsIgnoreCase(locationName))) {			
						try {
							// Call remote method
							callRemoteExecute(locationName, sessionName, locationName, commandName, optionKey, optionValue);
						} catch (Exception e) {
							e.printStackTrace();
					}
				
			} else
			{
				String instanceName = null;
				String componentName = null;
				String locName = null;				
				String componentProviderName = null;
				String componentUserName = null;
				String providesPortName = null;
				String usesPortName = null;
				

				/* Execute the specified command */
				switch (command)
				{
				     
					// Creates instance 
					case createComponentCommand:
						instanceName = null;
						componentName = null;
						locName = null;
						
						// Find the keys
						for (int i= 0; i < 3; i ++ ) {
							if (optionKey[i].equals(ForroDriver.componentNameLabel)) {
								componentName = optionValue[i];
							} else if (optionKey[i].equals(ForroDriver.instanceNameLabel)) {
									instanceName = optionValue[i];
							} else	if (optionKey[i].equals(ForroDriver.locationNameLabel)) {
								locName = optionValue[i];
							}

						}
						
						TypeMap properties = sessionDriver.framework().createTypeMap();
						properties.putBool(TypeMapKeys.IsComponent, true);						
						properties.putBool(TypeMapKeys.IsLocation, false);						
						properties.putBool(TypeMapKeys.IsWorkspace, false);						
						properties.putBool(TypeMapKeys.IsLink, false);						
						
						// Create the component in the ForroServices
						ComponentID componentID = sessionDriver.createInstance(instanceName, componentName, properties);
										
						System.out.println("\n After create component############: " + componentName + " location: " + locName + " componentID: " + componentID);
						
						break;
						
						// Creates a connection between ports 
					case connectCommand:
						// Create a connection between a user port and a provider port
						componentProviderName = null;
						componentUserName = null;
						providesPortName = null;
						usesPortName = null;
						
						
						// Find the keys
						for (int i= 0; i < 4; i ++ ) {
							if (optionKey[i].equals(providerComponentNameLabel)) {
								componentProviderName = optionValue[i];
							} else if (optionKey[i].equals(providesPortNameLabel)) {
								providesPortName = optionValue[i];
							} else	if (optionKey[i].equals(userComponentNameLabel)) {
								componentUserName = optionValue[i];
							} else	if (optionKey[i].equals(usesPortNameLabel)) {
								usesPortName = optionValue[i];
							}
						}
							
						System.out.println("\n Before create connection**********: " + providesPortName + " - " + usesPortName );
						
						ComponentID componentProviderID = sessionDriver.getComponentID(componentProviderName);
						ComponentID componentUserID = sessionDriver.getComponentID(componentUserName);
						final EventType eUses = EventTypes.newEventType(componentUserID, EventType.Connected);
							
						ConnectionEventListener conEvt = (ConnectionEventListener)  sessionDriver.lookupServices(sessionName);
						sessionDriver.addConnectionEventListener(eUses, conEvt); 
						
						
						
						//sessionDriver.framework().getServices(selfInstanceName, selfClassName, selfProperties));
						ConnectionID connection = sessionDriver.connect(componentUserID, usesPortName, componentProviderID, providesPortName);
						
						System.out.println("\n After create connection!!!!!!!!!!!*: " + providesPortName + " - " + usesPortName + " - ConnectionID " +
								connection);
			
						break;

						
						// Creates a connection between ports 
					case goCommand:
						componentName = null;
						String portName = null;
						
						
						// Find the keys
						for (int i= 0; i < 2; i ++ ) {
							if (optionKey[i].equals(ForroDriver.componentNameLabel)) {
								componentName = optionValue[i];
							} else if (optionKey[i].equals(ForroDriver.portNameLabel)) {
								portName = optionValue[i];
							}

						}
						
						System.out.println("\n Before GO command n**********: " + componentName);

						Port portB = ForroDriver.getSessionDriverMap().get(sessionName).lookupPort(componentName, portName);
						GoPort goB = (GoPort) portB;
						System.out.println("After GO command n. **********: " + componentName);
						System.out.println("\n ---------------RESPONSE ##########################:  ");
						goB.go();
						System.out.println("\n ##################################################  ");
						break;

						
					// Creates workspace 
					case createWorkspaceCommand:
						// Keep the workspaces names in the workspace map

						String workspaceClassType = null;
						String workspaceName = null;
						locationName = null;
														
							
						// Find the keys
						for (int i= 0; i < 3; i ++ ) {
							if (optionKey[i].equals(ForroDriver.workspaceTypeLabel)) {
								workspaceClassType = optionValue[i];
							} else {
								if (optionKey[i].equals(ForroDriver.workspaceNameLabel)) {
									workspaceName = optionValue[i];
								} else {
										if (optionKey[i].equals(ForroDriver.locationNameLabel)) {
											locationName = optionValue[i];
										}
								}
							}								
						}						
						
						TypeMap propertiesWorkspace = sessionDriver.framework().createTypeMap();
						propertiesWorkspace.putBool(TypeMapKeys.IsComponent, false);						
						propertiesWorkspace.putBool(TypeMapKeys.IsLocation, false);						
						propertiesWorkspace.putBool(TypeMapKeys.IsWorkspace, true);						
						propertiesWorkspace.putBool(TypeMapKeys.IsLink, false);						

						// Create the component WorkspaceDriver in the Forro Services
						ComponentID compID = sessionDriver.createInstance(workspaceNameLabel, workspaceClassType, propertiesWorkspace);
				
						workspaceDriverMap.put(workspaceName, compID);
						System.out.println(" Type: " + workspaceClassType + " Name: " + workspaceName + " Location: " + locationName);
						break;
						

					// Creates location 
					case createLocationCommand:
						// Create a middleware and read the port number
						
						locationName = null;
						String middlewareName = null;
						String port = null;
							
						
						// Find the keys
						for (int i= 0; i < 3; i ++ ) {
							if (optionKey[i].equals(ForroDriver.locationNameLabel)) {
								locationName = optionValue[i];
							} else if (optionKey[i].equals(ForroDriver.middlewareNameLabel)) {
								middlewareName = optionValue[i];
							} else if (optionKey[i].equals(ForroDriver.portNameLabel)) {
								port = optionValue[i];
							}
						}						
						
		
						TypeMap propertiesLocation = sessionDriver.framework().createTypeMap();
						propertiesLocation.putBool(TypeMapKeys.IsComponent, false);						
						propertiesLocation.putBool(TypeMapKeys.IsLocation, true);						
						propertiesLocation.putBool(TypeMapKeys.IsWorkspace, false);						
						propertiesLocation.putBool(TypeMapKeys.IsLink, false);						
						propertiesLocation.putString(TypeMapKeys.LocationPortNameKey, port);						


						// Create the component Middleware (locationDriver) in the specified location
						ComponentID locationID = sessionDriver.createInstance(locationName, middlewareName, propertiesLocation);
						locationMap.put(locationName, locationID);
						System.out.println("Create location: " + locationName + " - middlewareName: " + middlewareName + " - port: " + port);

						break;
						
					// Creates link 
					case createLinkCommand:
						// Keep the links names in the link map
						workspaceName = null; 
						String linkName = null;
						locationName = null;
						

						// Find the keys
						for (int i= 0; i < 3; i ++ ) {
							if (optionKey[i].equals(ForroDriver.linkNameLabel)) {
								linkName = optionValue[i];
							}  else if (optionKey[i].equals(ForroDriver.workspaceNameLabel)) {
								workspaceName = optionValue[i];
							} else if (optionKey[i].equals(ForroDriver.locationNameLabel)) {
								locationName = optionValue[i];
							}
						}						
						
						// Keep the workspaces names in the workspace map
						try {
								ComponentID workspaceComponentID = workspaceDriverMap.get(workspaceName);
								AbstractWorkspaceDriver workspaceDriver = (AbstractWorkspaceDriver) ForroDriver.getComponentMap().get(workspaceComponentID);
								if (workspaceDriver!=null)
								{
									//	Keep the locations names in the location map
									workspaceDriver.addAsynchronousLink(linkName);
									linkMap.put(linkName, workspaceComponentID);
								}
								else {
									throw new CCAException(" The workspace " + workspaceName + " does not exist."); 
								}
							} catch (CCAException e) {
								e.printStackTrace();
							}
							
							System.out.println("\n Create link: " + linkName + " - in the workspace: " + workspaceName + " - location: " + locationName);
			
						break;
						
						// Creates relation location workspace 
					case createRelationLocationWorkspaceCommand:
						// Keep the location and workspace names
						workspaceName = null; 
						String locationRelationName = null;
						locationName = null;
						

						// Find the keys
						for (int i= 0; i < 3; i ++ ) {
							if (optionKey[i].equals(ForroDriver.locationNameLabel)) 
								locationName = optionValue[i];
							if (optionKey[i].equals(ForroDriver.workspaceNameLabel)) 
								workspaceName = optionValue[i];
							if (optionKey[i].equals(ForroDriver.locationRelationNameLabel)) 
								locationRelationName = optionValue[i];
							
						}						

						
						
						// Keep the workspaces names in the workspace map
						try {
							ComponentID workspaceComponentID = workspaceDriverMap.get(workspaceName);
							AbstractWorkspaceDriver workspaceDriver = (AbstractWorkspaceDriver) ForroDriver.getComponentMap().get(workspaceComponentID);
							//	Keep the locations names in the location map
							if (workspaceDriver==null)
							{
								throw new CCAException(" The workspace " + workspaceName + " does not exist."); 
							}
							
							ComponentID locationComponentID = locationMap.get(locationRelationName);
							Middleware locationDriver = (Middleware) ForroDriver.getComponentMap().get(locationComponentID);

							if (locationDriver==null)
							{
								throw new CCAException(" The location " + locationRelationName + " does not exist."); 
							}

							
							workspaceDriver.addLocation(locationRelationName, locationDriver);
							System.out.println("Relation created. Location: " + locationRelationName + "- Workspace: " +  workspaceName);
														
						} catch (CCAException e) {
							e.printStackTrace();
						}
							
						break;
						
						// Initialize the repository
						case repositoryInitCommand:
							this.thisLocationName = locationName;
							logOn(sessionName);
							break;
					
					default :
						System.out.println ("Invalid option");
						break;
					}
			}
		}  catch (final IllegalArgumentException  e) {
			// TODO Auto-generated catch block
			System.err.println ("The specified command does no exist with the specified name, or the specified class object does not represent an command.");			
		}  catch (final NullPointerException   e) {
			// TODO Auto-generated catch block
			System.err.println ("The command name is null.");	
			e.printStackTrace();
		}  
		
		
	}
 
	/**
	 * It uses Java's RMI.
	 */
		private ForroDriver getRemoteFramework(String locationName, String registeredName) {
			ForroDriver handler = null;

		try {
			System.out.println("getting rmi://" + locationName + "/" + registeredName + "...");
			final Remote r = Naming.lookup("rmi://" + locationName + "/" + registeredName);
			handler = (ForroDriver) r; 
		} catch (final MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return handler;
	}

		
		
		

	/**
	 * Shows a configuration menu.
	 * @param sessionName TODO
	 */
    private static void menu(ForroDriver forroDriver, String sessionName) throws IOException {
 
    	BufferedReader r = new BufferedReader(
    	        new InputStreamReader(System.in));
    	String s=null;
    	
    	
    	
    	// Create the session and log on.
    	forroDriver.logOn(sessionName);
    	
    	/* Shows the menu until that a right option be choosen. */
    	do{ 
    		System.out.println("\n Configuration by (f)ile or (k)eybord: ");
    		s = r.readLine() ;
            if (s.equalsIgnoreCase("f")){
        		System.out.println("\n Type the file (indicating the path): ");
        		s = r.readLine() ;
        		
        		try {
        			System.out.println("\n\nReading the file.................................................... \nFilename:" + s + ".............\n");
        			
         				FileReader fd = new FileReader(s);
        				BufferedReader br = new BufferedReader(fd);
        				String lin;
        				int counterNumberLines = 0;
        				int counterNumberWorkspaces = 0;
        				int counterNumberLocations = 0;
        				int counterNumberLinks = 0;
        				int counterNumberRelationsLocationWorkspace = 0;
					    String optionKey[] = new String[4];  
					    String optionValue[] = new String[4];
					    int counterComponents = 0;
					    int counterPortConnections = 0;
					    String lastCommand = null;
					    
					    
        				while ((lin = br.readLine()) != null)
        				{
        					
        					// Interpret the file to read the file�s information  and load into the system
        					//If counterNumberLines==0 means the information of number of workspaces
        					if (counterNumberLines==0)
        					{
        						// Keep the number of workspaces
        						counterNumberWorkspaces = (new Integer(lin)).intValue();
        						System.out.println("The Number of workspaces is: " + counterNumberWorkspaces);
        					}
        					else
        					{
        						if (counterNumberLines<=counterNumberWorkspaces)
        						{
        							optionKey[0] = "workspaceName";
        							optionValue[0] = lin;

        							// Keep the workspaces names in the workspace map        							
        						//	forroDriver.execute(sessionName, "localhost", "createWorkspaceCommand", optionKey, optionValue);
        							
        						}
        						else 
        						{
        								if (counterNumberLines==counterNumberWorkspaces + 1)
        								{
        	        						// Keep the number of locations
        	        						counterNumberLocations = (new Integer(lin)).intValue();
        	        						System.out.println("The Number of locations is: " + counterNumberLocations);
        								}
        								else {
        	        						if ((counterNumberLines>counterNumberWorkspaces+1)&& (counterNumberLines<counterNumberWorkspaces+counterNumberLocations+2))
        	        						{
        	        							optionKey[0] = "locationName";
        	        							optionValue[0] = lin;

        	        							// Keep the locations names in the location map
        	        						//	forroDriver.execute(sessionName, "localhost", "createLocationCommand", optionKey, optionValue);

        	        						}
        	        						else {
                								if (counterNumberLines==counterNumberWorkspaces + counterNumberLocations + 2)
                								{
                	        						// Keep the number of locations
                	        						counterNumberLinks = (new Integer(lin)).intValue();
                	        						System.out.println("The Number of links is: " + counterNumberLinks);
                								}
                								else {
                									
                									
                									if ((counterNumberLines>counterNumberWorkspaces + counterNumberLocations + 2)&& (counterNumberLines<counterNumberWorkspaces+counterNumberLocations+ counterNumberLinks+3))
                	        						{
                	        							optionKey[0] = "linkName";
                	        							optionValue[0] = lin;

                	        							// Keep the links names in the link map
                	        						//	forroDriver.execute(sessionName, "localhost", "createLinkCommand", optionKey, optionValue);

                	        						}
                	        						else {
                        								if (counterNumberLines==counterNumberWorkspaces+counterNumberLocations+ counterNumberLinks + 3)
                        								{
                        	        						// Keep the number of relations
                        	        						counterNumberRelationsLocationWorkspace = (new Integer(lin)).intValue();
                        	        						System.out.println("The Number of relations is: " + counterNumberRelationsLocationWorkspace);
                        								} 
                        								else {

                            								///////////////////////  Novo para incluir componentes e portas
                        									if ((counterNumberLines > counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + 3) && (counterNumberLines < counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + counterNumberRelationsLocationWorkspace + 4))
                        									{
                            									optionKey[0] = "relationLocationWorkspaceName";
                        	        							optionValue[0] = lin;

                        	        							// Keep the links names in the link map
                        	        							//forroDriver.execute(sessionName, "localhost", "createRelationLocationWorkspaceCommand", optionKey, optionValue);                    	        							
                        									}
                        									else {
                        										if (counterNumberLines == counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + counterNumberRelationsLocationWorkspace + 4)
                                								{
                                	        						// Keep the number of locations
                                	        						counterComponents = (new Integer(lin)).intValue();
                                	        						System.out.println("The Number of components is: " + counterComponents);
                                								}	
                        										else {
                        											if ((counterNumberLines > counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + counterNumberRelationsLocationWorkspace + 4) && (counterNumberLines < counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + counterNumberRelationsLocationWorkspace + counterComponents + 5))
                                									{
                                    									optionKey[0] = componentNameLabel;                                	        			
                                    									optionKey[1] = instanceNameLabel;
                                    									optionKey[2] = locationNameLabel;
                                                 									
                                    								
                                    									

                                	        							String locationFull = lin.substring(0, lin.indexOf("|"));
                                	        							String componentFullName = lin.substring(lin.indexOf("|")+1, lin.length());
                                 	        							//componentMap.put(componentName, optionValue[0]);
                                	        							
                                	        							optionValue[0] = componentFullName;
                                	        							optionValue[1] = componentFullName;                                	        							
                                	        							optionValue[2] = locationFull;
                                	        							
                                	        							// Keep the links names in the link map
                                	        							forroDriver.execute(sessionName, "localhost", "createComponentCommand", optionKey, optionValue);                    	        							
                                									}  else {
                                										if (counterNumberLines == (counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + counterNumberRelationsLocationWorkspace + counterComponents + 5))
                                        								{
                                        	        						// Keep the number of relation between ports
                                        	        						counterPortConnections = (new Integer(lin)).intValue();
                                        	        						System.out.println("The Number of port conections is: " + counterPortConnections);
                                        								}	
                                										else {
                                											if ((counterNumberLines > counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + counterNumberRelationsLocationWorkspace + counterComponents + 5) && (counterNumberLines < counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + counterNumberRelationsLocationWorkspace + counterComponents + counterPortConnections+ 6))
                                        									{
                                            									optionKey[0] = providerComponentNameLabel;                                	        			
                                            									optionKey[1] = providesPortNameLabel;
                                            									optionKey[2] = userComponentNameLabel;
                                            									optionKey[3] = usesPortNameLabel;
                                            									

                                        	        							String providerPortFull = lin.substring(0, lin.indexOf("|"));
                                        	        							String userPortFull = lin.substring(lin.indexOf("|")+1, lin.length());
                                        	        							String componentProviderName = providerPortFull.substring(0, providerPortFull.indexOf(";"));
                                        	        							String componentUserName = userPortFull.substring(0, userPortFull.indexOf(";"));
                                        	        							String portProvidesName = providerPortFull.substring(providerPortFull.indexOf(";")+1, providerPortFull.length());
                                        	        							String portUsesName = userPortFull.substring(userPortFull.indexOf(";")+1, userPortFull.length());
                                        	        							
                                        	        							
                                        	        							
                                        	        							optionValue[0] = componentProviderName;
                                        	        							optionValue[1] = portProvidesName;                                	        							
                                        	        							optionValue[2] = componentUserName;
                                        	        							optionValue[3] = portUsesName;
                                        	        							
                                        	        							// Connect two ports
                                        	        							forroDriver.execute(sessionName, "localhost", "connectCommand", optionKey, optionValue);                    	        							
                                        									} else {
		                                										if (counterNumberLines==counterNumberWorkspaces + counterNumberLocations + counterNumberLinks + counterNumberRelationsLocationWorkspace + counterComponents + counterPortConnections+ 6)
		                                        								{
		                                        	        						// Command Go
	                                            									optionKey[0] = commandLabel;                                	        			
	                                            									optionKey[1] = componentNameLabel;
	                                            									

	                                        	        							lastCommand = lin.substring(0, lin.indexOf("|"));
	                                        	        							String componentName = lin.substring(lin.indexOf("|")+1, lin.length());
	                                        	        							
	                                        	        							
	                                        	        							
	                                        	        							optionValue[0] = lastCommand;
	                                        	        							optionValue[1] = componentName;                                	        							

		                                        	        						if (lastCommand.equals(goCommandLabel))
		                                        	        							forroDriver.execute(sessionName, "localhost", goCommandName, optionKey, optionValue);
		                                        	        						else 
		                                        	        						  System.out.println("The command not recognized: " + lastCommand);
		                                        								}	
                                        									}
                                        									}
                        											}
                        											
                        											
                        										}
                        									}
                        								}
                									
                	        						}
                									
                								}
        	        							
        	        						}
        								}
        						}
        								
        					}
        					counterNumberLines++;
        				}
        				
        				
        				System.out.println("\n------------- File finished........\n\n");     			
       			} catch ( IOException e) {
        			System.out.print(e.getMessage());
        			} catch (CCAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		break;
            }
            else if (s.equalsIgnoreCase("k")){
        		s = r.readLine() ;
        		int i = (new Integer(s)).intValue() ;  
        		System.out.println(i) ; 
	
            }
            
            	
    		
    		
    	} while ((s.equalsIgnoreCase("f")) || (s.equalsIgnoreCase("k")));
    	
		


    	
    }
	
    public void initForro(){
    	
    	ForroDriverRMIInterface stubF;
    	
		try {
			stubF = (ForroDriverRMIInterface) UnicastRemoteObject.exportObject(this, 0);
			System.out.println("criando...");
			Registry registry = LocateRegistry.createRegistry(10001);
			
			registry.bind("ForroDriver", stubF);
	    	
	    	FrontAdaptor frontEnd = new FrontAdaptor(FrontEndType.ParserCCACaffeine, this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	
    }
	
    public void initForroAncientVersion(){
    	
    	if (System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		try {
			ForroDriverRMIInterface forro = new ForroDriver();
			Naming.rebind("ForroDriver", forro);
			System.out.println("Remote object bound to 'ForroDriver'.");
			
			FrontAdaptor frontEnd = new FrontAdaptor(FrontEndType.ParserCCACaffeine, this);
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException)e;
			System.out.println("" + e);
		}
		
		
    	
    }
    
    public ForroDriverRMIInterface getRMIService(String host){
    	ForroDriverRMIInterface service = null;
    	Registry registry;
		try {
			registry = LocateRegistry.getRegistry(host,10001);
			service = (ForroDriverRMIInterface)registry.lookup("ForroDriver");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return service;
    	
    }
	/* It starts the application */
	public static void main(String[] args) throws Exception {

		//////////////////////////////////////////////////////////////////////////				 
		// RMI Server registry
		//////////////////////////////////////////////////////////////////////////
		
		System.out.println("------ Program started.");
	    ForroDriver forroDriver = new ForroDriver();
	    //forroDriver.initForro();
		forroDriver.initForroAncientVersion();
	    
	    
	    //desconsiderar
	    /*try 
		{
			
			// Assign security manager
			if (System.getSecurityManager() == null)		{
				System.setSecurityManager(new RMISecurityManager());
			}
			
			System.out.println("Starting the Server.........");
			  
//			ForroDriver objF = new ForroDriver();
//			ForroDriverRMIInterface stubF = (ForroDriverRMIInterface) UnicastRemoteObject.exportObject(objF, 0);
//			ForroDriverRMIInterface stubF = (ForroDriverRMIInterface) UnicastRemoteObject.exportObject(this, 0);
			ForroDriver forroDriver = new ForroDriver();
			Naming.bind("ForroDriver", forroDriver);
		    // Bind the remote object's stub in the registry
//		    Registry registry = LocateRegistry.getRegistry();
//		    registry.bind("ForroDriver", stubF);
		    System.out.println("------  Server ready.");

		    // Creates a forro driver
		   // ForroDriver.setForrodriver(objF);
		    
//		    FrontEndForroAdapter frontAdapter = new FrontEndForroAdapter();
//		    ForroDriver.setFrontEndForroAdapter(frontAdapter);
		    
		    // Call the menu
		    FrontAdaptor frontEnd = new FrontAdaptor(FrontEndType.ParserCCACaffeine, forroDriver);
		    //menu(objF, "localSession");
  
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		} 
*/			
			
			
//		//////////////////////////////////////////////////////////////////////////				 
//		// RMI Client registry
//		//////////////////////////////////////////////////////////////////////////
//		try {
////		    Registry registry = LocateRegistry.getRegistry();
//		    System.out.println("Retrieving the registry............");
//		    ForroDriverRMIInterface stub = (ForroDriverRMIInterface) Naming.lookup("ForroDriver");
//		    String response = stub.sayHello();
//		    System.out.println("------- response oh the cliente RMI: " + response + ".");
//		} catch (Exception e) {
//		    System.err.println("Client exception: " + e.toString());
//		    e.printStackTrace();
//		} 


			
			
			
			
	}


	
	
	
     ///////////////////////////////////////////////////////////////	
	/**
	 * Call a remote method to execute the commands 
	 * @param sessionName session name
	 */
	public void callRemoteExecute(String hostName, String sessionName, String locationName, String commandName, String[] optionKey, String[] optionValue) throws RemoteException, Exception	{
		try {
			// Check for hostname argument
			if (hostName.length() < 1)		{
				System.out.println
				("Syntax error - ForroDriverClient host");
				System.exit(1);
			}

			// Assign security manager
			if (System.getSecurityManager() == null)		{
				System.setSecurityManager
				(new RMISecurityManager());
			}

			// Call registry for ForroDriverRMIInterface
			ForroDriverRMIInterface service = (ForroDriverRMIInterface) Naming.lookup
				("rmi://" + hostName + "/ForroDriver");
			//ForroDriverRMIInterface service = this.getRMIService(hostName);
			


			// Call remote method
			service.execute(sessionName, locationName, commandName, optionKey, optionValue);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * @return the sessionDriverMap
	 */
	public static Map<String, SessionDriver> getSessionDriverMap() {
		return sessionDriverMap;
	}




	
	/**
	 * @return the workspaceDriverMap
	 */
	public static Map<String, ComponentID> getWorkspaceDriverMap() {
		return workspaceDriverMap;
	}


	/**
	 * @return the linkMap
	 */
	public static Map<String, ComponentID> getLinkMap() {
		return linkMap;
	}


	/**
	 * @return the locationMap
	 */
	public static Map<String, ComponentID> getLocationMap() {
		return locationMap;
	}









	/**
	 * @return the forrodriver
	 */
//	public static ForroDriver getForrodriver() {
//		return forrodriver;
//	}
//

	/**
	 * @param forrodriver the forrodriver to set
	 */
	public static void setForrodriver(ForroDriver forrodriver) {
		ForroDriver.forrodriver = forrodriver;
	}


	/**
	 * @return the frontEndForroAdapter
	 */
	public static FrontEndForroAdapter getFrontEndForroAdapter() {
		return frontEndForroAdapter;
	}


	/**
	 * @param frontEndForroAdapter the frontEndForroAdapter to set
	 */
	public static void setFrontEndForroAdapter(
			FrontEndForroAdapter frontEndForroAdapter) {
		ForroDriver.frontEndForroAdapter = frontEndForroAdapter;
	}


	/**
	 * @return the currentSessionDriver
	 */
	public static SessionDriver getCurrentSessionDriver() {
		return currentSessionDriver;
	}


	/**
	 * @param currentSessionDriver the currentSessionDriver to set
	 */
	public static void setCurrentSessionDriver(SessionDriver currentSessionDriver) {
		ForroDriver.currentSessionDriver = currentSessionDriver;
	}


	/**
	 * @return the componentMap
	 */
	public static Map<ComponentID, Component> getComponentMap() {
		return componentMap;
	}


	public String getThisLocationName() {
		return thisLocationName;
	}




	/* (non-Javadoc)
	 * @see session.SessionHandler#startNewLink(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 
	private boolean startLink(String linkName, String originName, String destinationName) {
		final int workIdx = linkName.indexOf('.');
		final String workName = linkName.substring(0, workIdx);

		// if the workspace name is in the map, retrieve it
		final AbstractWorkspaceDriver wdriver = (AbstractWorkspaceDriver) workspaceMap.get(workName);

		if (wdriver == null)
			return false;

		try {
			if (originName.equals(hostName)) {
				if (!destinationName.equals(hostName)) {
					final FrameworkDriver destHandler = (FrameworkDriver) handlerMap.get(destinationName);

					if (destHandler == null) return false;

					destHandler.invokeAt("startLink", new Object[] { linkName, originName, destinationName }, sessionName, null);
				}

				System.out.print("Adding " + linkName + " to " + workName + "...");

				return wdriver.registerLink(linkName.substring(workIdx+1), destinationName);
			}

			if (destinationName.equals(hostName)) 
				return wdriver.registerLink(linkName.substring(workIdx+1), originName);

			final FrameworkDriver origHandler = (FrameworkDriver) handlerMap.get(originName);

			// otherwise, throws an exception
			if (origHandler == null) return false;

			// ask the handler to launch the model defined by the class name
			return ((Boolean) origHandler.invokeAt("startLink", new Object[] { linkName, originName, destinationName }, sessionName, null)).booleanValue();
		} catch (final RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
*/
	/* (non-Javadoc)
	 * @see session.SessionHandler#startNewWorkspace(java.lang.String)
	 
	private boolean startWorkspace(String locationName, String workName, String workImplName, String middleImplName) {
		if (locationName.equals(hostName)) {
			if (workspaceMap == null) workspaceMap = new HashMap();
			else if (workspaceMap.containsKey(workName)) return false;

			workspaceMap.put(workName, AbstractWorkspaceDriver.newInstance(workImplName, middleImplName));
			return true;
		} 

		boolean ret = false;
		final FrameworkDriver handler = (FrameworkDriver) handlerMap.get(locationName);

		// otherwise, throws an exception
		if (handler != null) {
			// ask the handler to launch the model defined by the class name
			final Object[] args = { locationName, workName, workImplName, middleImplName };
			try {
				ret = ((Boolean) handler.invokeAt("startWorkspace", args, sessionName, null)).booleanValue();
			} catch (final RemoteException e) {
				e.printStackTrace();
			} catch (final IllegalArgumentException e) {
				e.printStackTrace();
			} catch (final SecurityException e) {
				e.printStackTrace();
			}
		}

		return ret;

	}
	*/
	
	
 
	
	
}
