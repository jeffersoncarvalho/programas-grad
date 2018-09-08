/* project    : forro2.0
 * file name  : ForroDriver.java
 * authors    : Ricardo Corrï¿½a (correa@lia.ufc.br), Francisco de Carvalho Junior (heron@lia.ufc.br), Gisele Araï¿½jo (gisele@lia.ufc.br)
 * created    : 03/08/2006
 * copyright  : Federal University of Cearï¿½, Brazil
 *
 * modifications:
 *
 */

package forrocore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ccacore.AbstractFramework;
import ccacore.ComponentID;
import ccacore.ConnectionEvent;
import ccacore.ConnectionEventListener;
import ccacore.EventType;
import ccacore.Port;
import ccacore.Services;
import ccacore.Type;
import ccacore.TypeMap;
import exceptions.CCAException;
import exceptions.CCAExceptionType;
import exceptions.TypeMismatchException;



/**
 * This is the implementation of the <tt>AbstractForro</tt> interface for the <tt>Forró</tt> framework.
 * An instance of this class is associate with a session, and is responsible for providing the corresponding
 * session driver with the component services. The services provided share the port tables of the components
 * and are all connection event listeners. This feature is at the basis of the connection event based 
 * implementation of port connections.
 * 
 * <p>For the purpose implementation of port connections, every request for a services object should be 
 * associated with a component implementing the <tt>ConnectionEventService</tt> interface. This is because this 
 * class adds every services object created to the list of connection event listeners of its component. In 
 * addition, every port connection performed by the session driver associated with an object of this class needs
 * to invoke the <tt>connectionActivity</tt> of its services object.
 * 
 * <p>Another role played by this class is the creation of instances of the <tt>TypeMap</tt> interface.
 * Every <tt>TypeMap</tt> created contains an entry with <tt>TypeMapKeys.SessionNameKey</tt> as key and
 * the name of the session associated with this framework as value. This guarantees that the session name
 * is a common property of all components and ports of a given session.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br), Gisele Azevedo (gisele@lia.ufc.br)
 */
public /*final */ class Forro implements AbstractFramework {
	
	/**
	 * The name of the session associated with this framework instance.
	 * It is included in every <tt>TypeMap</tt>.
	 */
	private String sessionName;
	
	
	/**
	 * The driver of the session associated with this framework instance.
	 */
	private SessionDriver sessionDriver = null;
	
	/**
	 * The map indexed by the components' names, and valued with the services provided.
	 */
	private final Map<String, Services> componentMap = new HashMap<String, Services>();
	
	
	private static final Map<String, ForroServices> servicesMap = new HashMap<String, ForroServices>();


	/**
	 * Creates a new <tt>Forro</tt> framework instance associated with a specified session.
	 * 
	 * @param sessionName the name of the specified session
	 * @param sessionDriver the driver of the specified session
	 */
	public Forro(String sessionName, SessionDriver sessionDriver) {
		
		this.sessionName = sessionName;
		this.sessionDriver = sessionDriver;
		
//		try {
			   //ComponentID ret = sessionDriver.registerInstance(sessionName, sessionName+"@"/* + locationName*/, sessionDriver);
  //   			Services s = getServices(sessionName, sessionDriver.getClass().getName(), createTypeMap());
//////			sessionDriver.setServices(s);
//////			componentMap.put(sessionName, s);
////			
//		} catch (CCAException e) {
////			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	/**
	 * Creates a new <tt>Forro</tt> framework instance.
	 */
	public Forro() {
		this.sessionName = "EmptyFramework.session";
	}
	
	
	/**
	 * This method is not provided by <tt>Forro</tt>. It throws an <tt>UnsupportedOperationException</tt>.
	 * 
	 * @see ccacore.AbstractFramework#createEmptyFramework()
	 */
	public AbstractFramework createEmptyFramework() throws CCAException {
		throw new UnsupportedOperationException();
	}


	/**
	 * Creates a <tt>TypeMap</tt> object with an entry with <tt>TypeMapKeys.SessionNameKey</tt> as key and
	 * the name of the session associated with this framework as value. This guarantees that the session name
	 * is a common property of all components and ports of a given session.
	 * 
	 * @see ccacore.AbstractFramework#createTypeMap()
	 */
	public TypeMap createTypeMap() throws CCAException {
		TypeMap ret = new ForroMap();
		ret.putString(TypeMapKeys.SessionNameKey, sessionName);
		return ret;
	}

	/**
	 * For the purpose of implementation of port connections, the specified component should implements
	 * the <tt>ConnectionEventService</tt> interface. This is because this 
	 * class adds every services object created to the list of connection event listeners of its component. In 
	 * addition, every port connection performed by the session driver associated with an object of this class needs
	 * to invoke the <tt>connectionActivity</tt> of its services object.
	 * 	 
	 * @see ccacore.AbstractFramework#getServices(java.lang.String, java.lang.String, ccacore.TypeMap)
	 */
	public Services getServices(String selfInstanceName, String selfClassName, TypeMap selfProperties) throws CCAException {
		
		ComponentID compID = sessionDriver.getComponentID(selfInstanceName);
		ForroServices ret = new ForroServices(compID);
		
		servicesMap.put(selfInstanceName, ret);
		
		Services old = componentMap.put(selfInstanceName, ret);
		if (old != null) {
			componentMap.put(selfInstanceName, old);
			throw new CCAException("Instance name already in use.");
		}
		sessionDriver.addConnectionEventListener(EventTypes.newEventType(compID, EventType.Connected), ret);
		sessionDriver.addConnectionEventListener(EventTypes.newEventType(compID, EventType.Disconnected), ret);
		return ret;
	}
	
	

	
	private void releaseComponent(Services svc) {
		ComponentID componentID;
		try {
			componentID = svc.getComponentID();
			sessionDriver.destroyInstance(componentID, 0);
			String[] providedPortNames = sessionDriver.getProvidedPortNames(componentID);
			for (String providedPortName : providedPortNames)
				svc.removeProvidesPort(providedPortName);
			String[] usedPortNames = sessionDriver.getUsedPortNames(componentID);
			for (String usedPortName : usedPortNames)
				svc.unregisterUsesPort(usedPortName);		
		} catch (CCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void releaseServices(Services svc) throws CCAException {
		if (componentMap.remove(svc.getComponentID().getInstanceName()) != svc) {
			componentMap.put(svc.getComponentID().getInstanceName(), svc);
			throw new CCAException();
		}
		releaseComponent(svc);
	}

	public void shutdownFramework() throws CCAException {
		for (Iterator<String> it = componentMap.keySet().iterator(); it.hasNext();) {
			releaseComponent(componentMap.get(it.next()));
			it.remove();
		}
	}

	/**
	 * This class acts as a map of ports associated with a component of a given session at a given
	 * location. Adding and removing ports to this
	 * map is performed according to the <tt>Services</tt> interface. The key of each entry
	 * of this map is a string containing the name of a port.
	 * Two values are assigned to each
	 * port, namely the port itself and its type. Additional properties can also be assigned
	 * to a port with a <tt>TypeMap</tt>.
	 * 
	 * @author Ricardo CorrÃªa (correa@lia.ufc.br)
	 *
	 */
	private class ForroServices implements Services, ConnectionEventListener {

		/**
		 * The map between Provides complete port names and port values.
		 * Both session name and location name are implicit.
		 */
		private final Map<String, Port> providesPortMap;

		/**
		 * The map between Uses port names and port values.
		 * Both session name and location name are implicit.
		 */
		private final Map<String, Port> usesPortMap;

		/**
		 * The reference of the component for which this object is defined. 
		 */
		private ComponentID componentID;

		/**
		 * @param component
		 */
		protected ForroServices(ComponentID component, boolean dummy) {
			this.componentID = component;
			providesPortMap = null;
			usesPortMap = null;
		}

		/**
		 * @param componentID
		 */
		ForroServices(ComponentID componentID) {
			this.componentID = componentID;
			providesPortMap = Collections.synchronizedMap(new HashMap<String, Port>());
			usesPortMap = Collections.synchronizedMap(new HashMap<String, Port>());
		}

		public void addProvidesPort(Port inPort, String portName, String type, TypeMap properties) throws CCAException {
			/* Verifies if the port already exists */
			if (providesPortMap.containsKey(portName) || usesPortMap.containsKey(portName))
				throw new CCAException(CCAExceptionType.PortAlreadyDefined);
			providesPortMap.put(portName, inPort);
			if (properties == null)
				properties = createTypeMap();
			properties.putString(TypeMapKeys.PortTypeKey, type);
			properties.putBool(TypeMapKeys.IsProvidesPortKey, true);
			sessionDriver.setPortProperties(componentID, portName, properties);
		}

		public TypeMap createTypeMap() throws CCAException {
			return Forro.this.createTypeMap();
		}

		public ComponentID getComponentID() throws CCAException {
			return componentID;
		}

		public Port getPort(String portName) throws CCAException {
			Port port = null;
			
			// possibly waiting forever while
			// attempting to acquire it
			while (port == null)
				port = getPortNonblocking(portName);

			return port;
		}

		public Port getPortNonblocking(String portName) throws CCAException {
			Port port = null;

			/* Search the port name in the provides port list providesPortMap and set the port in the portValue*/ 
			port = providesPortMap.get(portName);

			/* Search the port name in the uses port list usesPortMap and set the port in the portValue*/ 
			if (port == null)
			{
				port = usesPortMap.get(portName);

				
				
			}
				


			/* Verify if the port exists and also if the port implementation exists */ 
			if (port == null)
				throw new CCAException(CCAExceptionType.PortNotDefined);
			sessionDriver.getPortProperties(componentID, portName).putBool(TypeMapKeys.PortInUseKey, true);
			return port;				
		}

		public void releasePort(String portName) throws CCAException {
			Port port = usesPortMap.get(portName);
			if (port == null)
				throw new CCAException(CCAExceptionType.PortNotDefined);
			if (!sessionDriver.getPortProperties(componentID, portName).hasKey(TypeMapKeys.PortInUseKey))
				throw new CCAException(CCAExceptionType.PortNotInUse);
			sessionDriver.getPortProperties(componentID, portName).remove(TypeMapKeys.PortInUseKey);
		}

		public TypeMap getPortProperties(String portName) {
			Port port = providesPortMap.get(portName);
			if (port == null)
				port = usesPortMap.get(portName);
			if (port == null)
				return null;
			TypeMap ret = null;
			try {
				ret = sessionDriver.getPortProperties(componentID, portName);
			} catch (CCAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ret;
		}

		public void registerUsesPort(String portName, String type, TypeMap properties) throws CCAException {
			/* Verifies if the port already exists */
			if (providesPortMap.containsKey(portName) || usesPortMap.containsKey(portName))
				throw new CCAException(CCAExceptionType.PortAlreadyDefined);
			usesPortMap.put(portName, null);
			if (properties == null)
				properties = createTypeMap();
			properties.putString(TypeMapKeys.PortTypeKey, type);
			properties.putBool(TypeMapKeys.IsProvidesPortKey, false);
			properties.putBool(TypeMapKeys.IsUsesPortKey, true);
			sessionDriver.setPortProperties(componentID, portName, properties);

			System.out.println("SessionName:   " + ((ForroSessionDriver)sessionDriver).getSessionName());
			
		}

		public void removeProvidesPort(String portName) throws CCAException {
			Port port = providesPortMap.remove(portName);
			/* Verifies if the port exists */
			if (port == null) 
				throw new CCAException(CCAExceptionType.PortNotDefined);
			sessionDriver.setPortProperties(componentID, portName, null);
		}

		public void unregisterUsesPort(String portName) throws CCAException {
			Port port = usesPortMap.remove(portName);

			if (port == null)
				throw new CCAException(CCAExceptionType.PortNotDefined);
			if (sessionDriver.getPortProperties(componentID, portName).hasKey(TypeMapKeys.PortInUseKey)) {
				usesPortMap.put(portName, port);
				throw new CCAException(CCAExceptionType.UsesPortNotReleased);
			}
			sessionDriver.setPortProperties(componentID, portName, null);
			
		}

		public void connectionActivity(ConnectionEvent ce) {
			try {
				String portName = ce.getPortInfo().getString(TypeMapKeys.UsesPortNameKey, "");
				Port port = usesPortMap.get(portName);
				EventType eType = ce.getEventType();
				if (EventTypes.hasType(eType, EventType.Connected)) {
					if (port != null)
						throw new CCAException("Port already connected.");
					String provider = ce.getPortInfo().getString(TypeMapKeys.ProviderNameKey, "");
					String providesPortName = ce.getPortInfo().getString(TypeMapKeys.ProvidesPortNameKey, "");
					usesPortMap.put(portName, componentMap.get(provider).getPortNonblocking(providesPortName));
				}
				else if (EventTypes.hasType(eType, EventType.Disconnected)) {
					if (port == null)
						throw new CCAException(CCAExceptionType.PortNotConnected);
					usesPortMap.put(portName, null);
				}
			} catch (TypeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CCAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author Gisele Azevedo
	 *
	 */
	private class ForroMap implements TypeMap  {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private final Map<String, Object> map = new HashMap<String, Object>();

		/**
		 * 
		 */
		public ForroMap() {
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#cloneEmpty()
		 */
		public TypeMap cloneEmpty() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#cloneTypeMap()
		 */
		public TypeMap cloneTypeMap() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getAllKeys(ccacore.Type)
		 */
		public String getAllKeys(Type t) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getBool(java.lang.String, boolean)
		 */
		public boolean getBool(String key, boolean dflt) throws TypeMismatchException {
			final Object value = map.get(key);
			if (value == null)
				return dflt;
			if (Types.isBoolean(value))
				return ((Boolean) value).booleanValue();
			throw new TypeMismatchException() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Type getActualType() {
					return Types.getType(value.getClass());
				}

				@Override
				public Type getRequestedType() {
					return Types.getBooleanType();
				}

			};
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getBoolArray(java.lang.String, boolean[])
		 */
		public boolean[] getBoolArray(String key, boolean[] dflt)
		throws TypeMismatchException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getDouble(java.lang.String, double)
		 */
		public double getDouble(String key, double dflt)
		throws TypeMismatchException {
			// TODO Auto-generated method stub
			return 0;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getDoubleArray(java.lang.String, double[])
		 */
		public double[] getDoubleArray(String key, double[] dflt)
		throws TypeMismatchException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getFloat(java.lang.String, float)
		 */
		public float getFloat(String key, float dflt) throws TypeMismatchException {
			// TODO Auto-generated method stub
			return 0;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getFloatArray(java.lang.String, float[])
		 */
		public float[] getFloatArray(String key, float[] dflt)
		throws TypeMismatchException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getInt(java.lang.String, int)
		 */
		public int getInt(String key, int dflt) throws TypeMismatchException {
			// TODO Auto-generated method stub
			return 0;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getIntArray(java.lang.String, int[])
		 */
		public int[] getIntArray(String key, int[] dflt)
		throws TypeMismatchException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getLong(java.lang.String, long)
		 */
		public long getLong(String key, long dflt) throws TypeMismatchException {
			// TODO Auto-generated method stub
			return 0;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getLongArray(java.lang.String, long[])
		 */
		public long[] getLongArray(String key, long[] dflt)
		throws TypeMismatchException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getString(java.lang.String, java.lang.String)
		 */
		public String getString(String key, String dflt) throws TypeMismatchException {
			final Object value = map.get(key);
			if (value == null)
				return dflt;
			if (Types.isString(value))
				return ((String) value);
			throw new TypeMismatchException() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Type getActualType() {
					return Types.getType(value.getClass());
				}

				@Override
				public Type getRequestedType() {
					return Types.getStringType();
				}
			};
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#getStringArray(java.lang.String, java.lang.String[])
		 */
		public String[] getStringArray(String key, String[] dflt)
		throws TypeMismatchException {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#hasKey(java.lang.String)
		 */
		public boolean hasKey(String key) {
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putBool(java.lang.String, boolean)
		 */
		public void putBool(String key, boolean value) throws TypeMismatchException {
			map.put(key, value);

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putBoolArray(java.lang.String, boolean)
		 */
		public void putBoolArray(String key, boolean value)
		throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putDouble(java.lang.String, double)
		 */
		public void putDouble(String key, double value)
		throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putDoubleArray(java.lang.String, double)
		 */
		public void putDoubleArray(String key, double value)
		throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putFloat(java.lang.String, float)
		 */
		public void putFloat(String key, float value) throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putFloatArray(java.lang.String, float)
		 */
		public void putFloatArray(String key, float value)
		throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putInt(java.lang.String, int)
		 */
		public void putInt(String key, int value) throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putIntArray(java.lang.String, int)
		 */
		public void putIntArray(String key, int value) throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putLong(java.lang.String, long)
		 */
		public void putLong(String key, long value) throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putLongArray(java.lang.String, long)
		 */
		public void putLongArray(String key, long value)
		throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putString(java.lang.String, java.lang.String)
		 */
		public void putString(String key, String value) throws TypeMismatchException {
			map.put(key, value);

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#putStringArray(java.lang.String, java.lang.String)
		 */
		public void putStringArray(String key, String value)
		throws TypeMismatchException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#remove(java.lang.String)
		 */
		public void remove(String key) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#typeOf(java.lang.String)
		 */
		public Type typeOf(String key) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see ccacore.TypeMap#typeOf(java.lang.String)
		 */
		public Type hashMap(String key) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * @return the servicesMap
	 */
	public  Map<String, ForroServices> getServicesMap() {
		return servicesMap;
	}
}
