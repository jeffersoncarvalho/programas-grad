/* file name  : LocationDriver.java
 * authors    : Ricardo Corrï¿½a (correa@lia.ufc.br)
 * created    : Apr 3, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.workspace;

import java.util.HashMap;
import java.util.Map;

import obsoleteClasses.ModelDriver;

import model.BindingProperties;
import model.ComponentFactory;
import model.ModelEmulator;
import session.datatype.Datatype;
import ccacore.BuilderService;
import ccacore.Component;
import ccacore.ComponentID;
import ccacore.ConnectionID;
import ccacore.Services;
import ccacore.TypeMap;
import exceptions.CCAException;
import forrocore.AbstractWorkspaceDriver;

/////////// BECOMES PART OF THE FRAMEWORK IMPLEMENTING CLASS FORRO?
/**
 * A location is an available site provided by the middleware, that may be used to run components. Every location
 * has its own <tt>LocationDriver</tt>, which allows the location to interact with the Session Handler, with the
 * Models at this location and with Workspaces containing this location. Every Location Driver is identified by 
 * the name assigned to the corresponding location by the middleware. Location drivers communicate among them
 * via the Session Handler.
 * 
 * <p>Three types of methods are provided by a Location Driver, namely <i>configuration</i>, <i>connecting</i> and
 * <i>model interacting</i> methods. In the implementation of all of these methods, ports, models, workspaces and links
 * are identified by their names. The state of the Location Driver is the state of two types of tables. The first
 * type associates names with the corresponding models and workspaces, indicating the session configuration.
 * The second type indicates the connections when associates ports to links, and links to workspaces. 
 * 
 * <p>Configuration method invocations are originated at the Session Handler and
 * are responsible for starting models at the location, for including the location in a workspace and for
 * creating a link from this location to another location in a workspace. The Session Handler may also
 * probe component implementations and instantiated components at this location.
 * 
 * <p>Connecting methods are also originated at the Session Handler, and consist of requests for links addition
 * or removal, and port bindings. A link cannot be removed if there is a port bound through it.
 * Provides port are simply registered, while uses port are bound to a provides port through a link. This means
 * that modifying a binding involves the uses port only.
 * 
 * <p>Model interactions occur according to the session configuration and connections established among the models
 * by the Session Handler. Since the Session Handler runs concurrently with the models, methods that modify the
 * state of this object, namely configuration and connecting methods, are synchronized, which prevents them from
 * being executed concurrently with model interactions.  
 * 
 * <p>Requests component and workspace driver creations to the factory.
 * 
 * <p>This is a singleton class. 
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public class LocationDriver implements Component {
	/**
	 * Name of this location
	 * @uml.property  name="locName"
	 */
	private String locName;
	/**
	 * Maps names to model components in this location
	 * @uml.property  name="modelMap"
	 * @uml.associationEnd  qualifier="modelName:java.lang.String model.ModelDriver"
	 */
	private Map modelMap;
	/**
	 * Used to create the model components in this location.
	 * @uml.property  name="contextFactory"
	 * @uml.associationEnd  inverse="driver:model.ModelFactory"
	 */
	private ComponentFactory contextFactory;
	/**
	 * This is a singleton class
	 */
	private static LocationDriver instance = null;
	
	/**
	 * Creates a Location Driver at this location.
	 * 
	 * @param lname this location's name
	 */
	private LocationDriver(String lname) {
		super();
		this.locName = lname;
		contextFactory = ComponentFactory.getInstance(this);
		modelMap = new HashMap();
	}

	/**
	 * Retrieves a location drvier.
	 * 
	 * @return the object previously instantiated
	 */
	public static synchronized LocationDriver getInstance() {
		return instance;
	}

	/**
	 * Instantiates a location drvier.
	 * 
	 * @param locName this location's name
	 * 
	 * @return the object created
	 */
	public static synchronized LocationDriver getInstance(String locName) {
		if (instance == null) instance = new LocationDriver(locName);
		return instance;
	}

	// Configuration methods
	
	public synchronized boolean addModel(String modelName, ModelEmulator modelEmu) {
		if (modelMap.containsKey(modelName)) return false;
		
		modelMap.put(modelName, contextFactory.newInstance(modelName));
		if (modelMap.get(modelName) != null)
			System.out.println(modelName + " properly instantiated at " + locName);
		else
			System.out.println(modelName + " not instantiated at " + locName);
		return true;
	}
	
//	public ModelDriver getModel(String modelName) {
//		return (ModelDriver) nameModelMap.get(modelName);
//	}

	public synchronized boolean probe(String implName) {
		return modelMap.containsKey(implName);		
		
	}

	// Connecting methods
		
	/**
	 * @param usesPortName <tt>ModelName.portName</tt>
	 * @param providesPortName <tt>ModelName.portName</tt>
	 * @param wdriver TODO
	 * @param linkName if <tt>null</tt>, then the provides port is assigned directly. Otherwise, the ports are bound 
	 *             through this link 
	 * @param bindingProp valid only if <tt>link</tt> is not <tt>null</tt>
	 * @return
	 */
	public synchronized boolean bindUsesPort(String usesPortName, String providesPortName, AbstractWorkspaceDriver wdriver, String linkName, BindingProperties bindingProp) {
		int modelIndex = usesPortName.lastIndexOf('.');
		String modelName = usesPortName.substring(0, modelIndex);
		ModelDriver mdriver = (ModelDriver) modelMap.get(modelName);
		
		if (linkName == null) {
			int pmodelIndex = providesPortName.lastIndexOf('.');
			String pmodelName = providesPortName.substring(0, pmodelIndex);
			ModelDriver pmdriver = (ModelDriver) modelMap.get(pmodelName);
			return mdriver.bindUsesPort(usesPortName.substring(modelIndex+1), pmdriver.getRegisteredProvidesPort(providesPortName.substring(providesPortName.lastIndexOf('.')+1)));
		}
			
		return mdriver.bindUsesPort(usesPortName.substring(modelIndex+1), providesPortName, wdriver, linkName, bindingProp);
	}

	/**
	 * Gets the views of the model according to the specified array of view names.
	 * @param providesPortName the port name <tt>modelName.portName</tt>
	 * @param bindingProp TODO
	 * @return an array with the requested views, in the same that their names appear in the view names array
	 */
	public synchronized boolean registerProvidesPort(String providesPortName, BindingProperties bindingProp) {
		System.out.println(providesPortName + " At LocationDriver of " + locName);
		
		int modelIndex = providesPortName.lastIndexOf('.');
		String modelName = providesPortName.substring(0, modelIndex);
		ModelDriver driver = (ModelDriver) modelMap.get(modelName);
		
		String portName = providesPortName.substring(modelIndex+1);
		System.out.println(portName + " registered at " + modelName);
		return driver.registerProvidesPort(portName, bindingProp);
	}

	/**
	 * @param modelName
	 * @return
	 */
	public synchronized String[] providesPortList(String modelName) {
		ModelDriver model = (ModelDriver) modelMap.get(modelName);
		return model.providesPortList();
	}

	/**
	 * @param modelName
	 * @return
	 */
	public synchronized String[] usesPortList(String modelName) {
		ModelDriver model = (ModelDriver) modelMap.get(modelName);
		return model.usesPortList();
	}

	// Model interaction methods
	
	/**
	 * This method is invoked by a link transmitting a port method invocation from a (remote) uses port to a (local)
	 * provides port. The port method invoked is specified as a parameter, as well as its arguments.
	 * This method retrieves the destination model name, and re-directs the port method invocation to the destination
	 * model.
	 * 
	 * @param methodName <tt>modelName.portName.methodName</tt>
	 * @param args the port method arguments
	 * 
	 * @return the return of the port method invoked
	 */
	public Datatype invokeAtModel(String methodName, Datatype[] args) {
		int index = methodName.substring(0,methodName.lastIndexOf('.')).lastIndexOf('.');
		ModelDriver model = (ModelDriver) modelMap.get(methodName.substring(0, index));
		return model.invoke(methodName.substring(index+1), args);
	}

	public void setServices(Services services) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Create and destroy <tt>WorkspaceDriver</tt> components.
	 * 
	 * @author Administrador
	 *
	 */
	public class LocationBuilderPort implements BuilderService {

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#connect(ccacore.ComponentID, java.lang.String, ccacore.ComponentID, java.lang.String)
		 */
		public ConnectionID connect(ComponentID user, String usingPortName,
				ComponentID provider, String providingPortName)
				throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#createInstance(java.lang.String, java.lang.String, ccacore.TypeMap)
		 */
		public ComponentID createInstance(String instanceName,
				String className, TypeMap properties) throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#destroyInstance(ccacore.ComponentID, float)
		 */
		public void destroyInstance(ComponentID toDie, float timeout)
				throws CCAException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#disconnect(ccacore.ConnectionID, float)
		 */
		public void disconnect(ConnectionID connID, float timeout)
				throws CCAException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#disconnectAll(ccacore.ComponentID, ccacore.ComponentID, float)
		 */
		public void disconnectAll(ComponentID id1, ComponentID id2,
				float timeout) throws CCAException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getComponentID(java.lang.String)
		 */
		public ComponentID getComponentID(String componentInstanceName)
				throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getComponentIDs()
		 */
		public ComponentID[] getComponentIDs() throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getComponentProperties(ccacore.ComponentID)
		 */
		public TypeMap getComponentProperties(ComponentID cid)
				throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getConnectionIDs(ccacore.ComponentID[])
		 */
		public ConnectionID[] getConnectionIDs(ComponentID[] componentList)
				throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getConnectionProperties(ccacore.ConnectionID)
		 */
		public TypeMap getConnectionProperties(ConnectionID connID)
				throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getDeserialization(java.lang.String)
		 */
		public ComponentID getDeserialization(String s) throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getPortProperties(ccacore.ComponentID, java.lang.String)
		 */
		public TypeMap getPortProperties(ComponentID cid, String portName)
				throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getProvidedPortNames(ccacore.ComponentID)
		 */
		public String[] getProvidedPortNames(ComponentID cid)
				throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#getUsedPortNames(ccacore.ComponentID)
		 */
		public String[] getUsedPortNames(ComponentID cid) throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#setComponentProperties(ccacore.ComponentID, ccacore.TypeMap)
		 */
		public void setComponentProperties(ComponentID cid, TypeMap map)
				throws CCAException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#setConnectionProperties(ccacore.ConnectionID, ccacore.TypeMap)
		 */
		public void setConnectionProperties(ConnectionID connID, TypeMap map)
				throws CCAException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.BuilderService#setPortProperties(ccacore.ComponentID, java.lang.String, ccacore.TypeMap)
		 */
		public void setPortProperties(ComponentID cid, String portName,
				TypeMap map) throws CCAException {
			// TODO Auto-generated method stub

		}

	}


//	/**
//	 * @param methodName <tt>portName.methodName</tt>
//	 * @param args
//	 * @return
//	 */
//	public Datatype invokeAtWorkspace(String methodName, Datatype[] args) {
//		int lastDotIndex = methodName.lastIndexOf('.');
//		String portName = methodName.substring(0, lastDotIndex);
//		String linkName = (String) portLinkMap.get(portName);
//		int widx = linkName.indexOf('.');
//		WorkspaceDriver wdriver = (WorkspaceDriver) nameWorkMap.get(linkName.substring(0, widx));
//		return wdriver.invoke(methodName, args, linkName.substring(widx+1));
//	}
}
