/* project    : forro2.0
 * file name  : AbstractSessionDriverComponent.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br), Francisco de Carvalho Junior (heron@lia.ufc.br), Gisele Ara�jo (gisele@lia.ufc.br)
 * created    : 03/08/2006
 * copyright  : Federal University of Cear�, Brazil
 *
 * modifications:
 *
 */

package forrocore;

import java.util.HashMap;
import java.util.Map;

import ccacore.BuilderService;
import ccacore.Component;
import ccacore.ComponentID;
import ccacore.ConnectionID;
import ccacore.TypeMap;
import exceptions.CCAException;
import exceptions.CCAExceptionType;

/**
 * This class provides a skeletal implementation of a session driver component to minimize the effort required to implement this
 * type of component. In addition, this class provides specific implementations of session driver and session builder ports. <p>
 *
 * The process of implementing a session driver by extending this class, the programmer needs only to
 * provide an implementation for the <tt>ccacore.Component.setServices</tt> method. Usually, the implementation of this method
 * decides of what type of session driver and session builder ports are used.
 * 
 * <p>In addition to the method <tt>ccacore.Component.setServices</tt>, a concrete implementation 
 * of this class should implement the certification policy.
 * 
 * <p>There is a special type of session driver: the one whose session driver provides port is connected to the <tt>FrontEnd</tt>.
 * In this case, it keeps a map of all workspaces started by this session
 * GUI. Otherwise, it only keeps the workspaces this location belongs to.
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public abstract class AbstractBuilderService implements BuilderService {

	/**
	 * Table of connections indexed by <tt>connectionName</tt> objects. 
	 */
	private final Map<String, ConnectionID> connectionMap = new HashMap<String, ConnectionID>();
	/**
	 * Table of properties connections indexed by <tt>ConnectionID</tt> objects. 
	 */
	private final Map<ConnectionID, TypeMap> connectionPropMap = new HashMap<ConnectionID, TypeMap>();
	
	/**
	 * Maps the components name of this machine to its instance.
	 * @uml.property  name="componentMap"
	 */
	private final Map<String, ComponentID> componentMap = new HashMap<String, ComponentID>();
//	private final Map<ComponentID, TypeMap> componentPropMap = new HashMap<ComponentID, TypeMap>();

	private final Map<ComponentID, Map<String, TypeMap>> portPropMap = new HashMap<ComponentID, Map<String, TypeMap>>();

	/**
	 * Creates a session driver with the specified security policy and the specified session name.
	 * This initializes an entry at the handler map with this location name as a
	 * key and the <tt>SignableSessionHandler</tt> implementation as a value.
	 * @throws CCAException 
	 */
	AbstractBuilderService() {
		
	}

	public abstract ComponentID createInstance(final String instName, final String className, TypeMap properties) throws CCAException;

	protected ComponentID registerInstance(final String instName, final String uniqueSt, final Component comp) throws CCAException {
		ComponentID ret = new ComponentID() {
			private String uniqueString = uniqueSt;
			private String instanceName = instName;
			// this is only for keeping a reference to the component to prevent it from
			// being killed by the garbage collector 
			@SuppressWarnings("unused")
			private Component component = comp;

			public String getInstanceName() throws CCAException {
				return instanceName;
			}

			public String getSerialization() throws CCAException {
				// TODO Auto-generated method stub
				return null;
			}

			public String toUniqueString() {
				return uniqueString;
			}

		};
		componentMap.put(instName, ret);
		return ret;
	}

	
	
	
	/***************************************************************************************/
	/**
	 * Get the list of links.
	 * @return a Link between two component.
	/***************************************************************************************/
	public Map getLinkList() throws CCAException {
		return connectionMap;
	}
	/***************************************************************************************/

	
	
	
	
	
	
	
	public ComponentID getComponentID(String componentInstanceName) throws CCAException {
		ComponentID ret = componentMap.get(componentInstanceName);
		if (ret == null)
			throw new CCAException("Component not defined");
		return ret;
	}
	
	
	
	public void destroyInstance(ComponentID toDie, float timeout) throws CCAException {
		ComponentID registeredToDie = componentMap.remove(toDie.getInstanceName());
		if (!toDie.equals(registeredToDie)) {
			componentMap.put(registeredToDie.getInstanceName(), registeredToDie);			
			throw new CCAException("Invalid component ID");
		}
		long base = System.currentTimeMillis();
		long now = 0;
		long millis = Math.round(timeout * 10e3);
		try {
			for (ConnectionID connectionID : getConnectionIDs(new ComponentID[] { toDie })) {
				long delay = millis - now;
				if (delay <= 0)
					throw new CCAException("Time out exceeded.");
				disconnect(connectionID, delay);
				now = System.currentTimeMillis() - base;
			}
		} catch (CCAException e) {
			componentMap.put(registeredToDie.getInstanceName(), registeredToDie);
			throw e;
		}
	}

	private String connectionName(ConnectionID connID) {
		String ret = null;
		try {
			ret = connID.getUser().getInstanceName()+"."+connID.getUserPortName()+"."+connID.getProvider().getInstanceName()+"."+connID.getProviderPortName();
		} catch (CCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public ConnectionID connect(final ComponentID user, final String usingPortName, final ComponentID provider, final String providingPortName) throws CCAException {
		if (!user.equals(componentMap.get(user.getInstanceName())) || !provider.equals(componentMap.get(provider.getInstanceName())))
			throw new CCAException("Invalid component ID");
		ConnectionID ret = null;
		ret = new ConnectionID() {
			public ComponentID getProvider() throws CCAException {
				return provider;
			}

			public String getProviderPortName() throws CCAException {
				return providingPortName;
			}

			public ComponentID getUser() throws CCAException {
				return user;
			}

			public String getUserPortName() throws CCAException {
				return usingPortName;
			}
		};
		connectionMap.put(connectionName(ret), ret);
		return ret;
	}

	public void setConnectionProperties(ConnectionID connID, TypeMap map) throws CCAException {
		if (!connID.equals(connectionMap.get(connectionName(connID))))
			throw new CCAException("Invalid component ID");
		connectionPropMap.put(connID, map);
	}

	public void disconnect(final ConnectionID connID, final float timeout) throws CCAException {
		ConnectionID registeredConnID = connectionMap.remove(connectionName(connID));
		if (!connID.equals(registeredConnID)) {
			connectionMap.put(connectionName(registeredConnID), registeredConnID);
			throw new CCAException("Invalid ConnectionID");
		}
		connectionPropMap.remove(connID);
	}

	public void disconnectAll(ComponentID id1, ComponentID id2, float timeout) throws CCAException {
		// Remove all the links of the linkMap inthe Workspace component

	}


	/** 
	 *  Get component list.
	 *  @return a ComponentID for each component currently created.
	 */
	public ComponentID[] getComponentIDs() throws CCAException {
		return null;
	}


	/** 
	 *  Get property map for component.
	 *  @return the public properties associated with the component referred to by
	 *  ComponentID. 
	 *  @throws a CCAException if the ComponentID is invalid.
	 */
	public TypeMap getComponentProperties(ComponentID cid) throws CCAException {
		if (!cid.equals(componentMap.get(cid.getInstanceName())))
			throw new CCAException("Invalid component ID");

		return null;
	}

	public ConnectionID[] getConnectionIDs(ComponentID[] componentList) throws CCAException {
		//	Get all ids of the connectionMap in the all Session component
		return null;
	}

	public TypeMap getConnectionProperties(ConnectionID connID) throws CCAException {
		// Get the specific properties of the connectionMap in the Session component
//		TypeMap map  = createTypeMap();
//		map.put("provider", (Object) connID.getProvider());
//		map.put("user", (Object) connID.getUser().getInstanceName());
//		map.put("userPortName", (Object) connID.getUserPortName());
//		map.put("providerPortName", (Object) connID.getProviderPortName());
		return null;
	}

	public ComponentID getDeserialization(String s) throws CCAException {
		//			 
		return null;
	}

	public void setPortProperties(ComponentID cid, String portName, TypeMap map) throws CCAException {
		if (!cid.equals(componentMap.get(cid.getInstanceName())))
			throw new CCAException("Invalid component ID");
		Map<String, TypeMap> compPortMap = portPropMap.get(cid);
		if (map != null) {
			if (compPortMap == null) {
				compPortMap = new HashMap<String, TypeMap>();
				portPropMap.put(cid, compPortMap);
			}
			compPortMap.put(portName, map);
		} else
			if (compPortMap != null)
				compPortMap.remove(portName);
	}

	public TypeMap getPortProperties(ComponentID cid, String portName) throws CCAException {
		if (!cid.equals(componentMap.get(cid.getInstanceName())))
			throw new CCAException("Invalid component ID");
		Map<String, TypeMap> compPortMap = portPropMap.get(cid);
		if (compPortMap == null)
			throw new CCAException(CCAExceptionType.PortNotDefined);
		TypeMap ret = compPortMap.get(portName);
		if (ret == null)
			throw new CCAException(CCAExceptionType.PortNotDefined);
		return ret;
	}


	/** 
	 *  Get the names of Port instances provided by the identified component.
	 *  @param cid the component.
	 *  @throws CCAException if cid refers to an invalid component.
	 */
	public String[] getProvidedPortNames(ComponentID cid) throws CCAException {
		if (!cid.equals(componentMap.get(cid.getInstanceName())))
			throw new CCAException("Invalid component ID");
		
		return null;
	}

	public String[] getUsedPortNames(ComponentID cid) throws CCAException {
		if (!cid.equals(componentMap.get(cid.getInstanceName())))
			throw new CCAException("Invalid component ID");
		return null;
	}

	public void setComponentProperties(ComponentID cid, TypeMap map) throws CCAException {
		if (!cid.equals(componentMap.get(cid.getInstanceName())))
			throw new CCAException("Invalid component ID");
		// TODO Auto-generated method stub

	}

}
