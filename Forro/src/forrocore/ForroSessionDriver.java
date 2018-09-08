/* project    : forro2.0
 * file name  : AbstractSessionDriverComponent.java
 * authors    : Ricardo Corrï¿½a (correa@lia.ufc.br), Francisco de Carvalho Junior (heron@lia.ufc.br), Gisele Araï¿½jo (gisele@lia.ufc.br)
 * created    : 03/08/2006
 * copyright  : Federal University of Cearï¿½, Brazil
 *
 * modifications:
 *
 */

package forrocore;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import ccacore.AbstractFramework;
import ccacore.Component;
import ccacore.ComponentID;
import ccacore.Port;
import ccacore.Services;
import ccacore.TypeMap;
import exceptions.CCAException;

/**
 * This class provides an implementation of the <tt>SessionDriver</tt> interface  corresponding
 * to a session driver component 
 * and its <tt>BuilderService</tt> provides port for the <tt>Forró</tt> framework.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br), Gisele Azevedo (gisele@lia.ufc.br)
 */
public class ForroSessionDriver extends AbstractConnectionEventBuilderService implements SessionDriver {


	
	
	/**
	 * The name associated with this session.
	 */
	private final String locationName;

	/**
	 * The name associated with this session.
	 */
	private final String sessionName;

	/**
	 * The services associated with this session. The way of the SessionDriver communicates with other one.   
	 */
	private Services services;

	/**
	 * The framework associated with this session.
	 */
	private final AbstractFramework framework;
	

	/**
	 * Creates a session driver with the specified security policy and the specified session name.
	 * This initializes an entry at the handler map with this location name as a
	 * key and the <tt>SignableSessionHandler</tt> implementation as a value.
	 * @param sessionName the session name
	 * @param locationName the location name of the location which the session happens
	 * @param framework the present framework
	 * @throws CCAException 
	 */
	public ForroSessionDriver(String sessionName, String locationName) throws CCAException {
		super();
		this.sessionName = sessionName;
		this.locationName = locationName;
		TypeMap properties = null;
		
		ComponentID ret = registerInstance(sessionName, sessionName+"@"+locationName, this);
		setComponentProperties(ret, properties);
		

		framework = new Forro(sessionName, this);
		System.out.println("------  Forro Instantied. ");
		properties = framework.createTypeMap();
		setServices(framework.getServices(sessionName, getClass().getName(), properties));
		
	}

	/**
	 * Since this is also a <tt>BuilderService</tt> provides port. For this reason, this method
	 * adds itself to the table of provides ports of this component. The type of this port is
	 * set to be the name of this class.
	 * @param services the communication way to manage the provides port
	 * @see ccacore.Component#setServices(ccacore.Services)
	 */
	public void setServices(Services s) {
		this.services = s;
		try {
			this.services.addProvidesPort(this, sessionName, getClass().getName(), getComponentProperties(getComponentID(sessionName)));
		} catch (CCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates a instance of components which will be run in this session. It verifies if the number of properties is correct. 
	 * It decodes the properties and get the arguments and its types. The method is invoked. It launchs the component. 
	 * It sets the component service. It registers the instance and sets the component properties.  
	 * The specified properties must include the parameters to the constructor of the component being
	 * created. These parameters must obey the following format:<br>
	 * <pre>
	 * Key                                Type                    Value
	 * ---                                ----                    -----
		 * TypeMapKeys.ClassListKey           StringArray             an array with the keys of the entries giving
	 *                                                            the classes of the parameters
	 * TypeMapKeys.ArgListKey             StringArray             an array with the keys of the entries giving
	 *                                                            the arguments of the parameters
	 * </pre>
	 * 
	 * @see ccacore.BuilderService#createInstance(java.lang.String, java.lang.String, ccacore.TypeMap)
	 * @param instName the instance name
	 * @param className the class name
	 * @param properties the instance properties 
	 * @throws CCAException 
	 */
	public ComponentID createInstance(final String instName, final String className, TypeMap properties) throws CCAException {
		System.out.println("Creating COMPONENT.... Name: " + className);
		
		Component component = null;
		ComponentID ret = null;
		try {
			    
			
			    if (properties.getBool(TypeMapKeys.IsComponent, false)) {
			    	component = (Component) Class.forName(className).getConstructor().newInstance();
			    } else {
			    	if (properties.getBool(TypeMapKeys.IsLocation, false)) {
			    		String port = properties.getString(TypeMapKeys.LocationPortNameKey, null);
			    		Class[] paramTypes = {Integer.class};
			    		Integer portNumber = Integer.valueOf(port);
				        Object[] args = {new Integer(portNumber)};

				    	component = (Component) Class.forName(className).getConstructor(paramTypes).newInstance(args);
				    	
				    	System.out.println( "New component: " + component);
				        
				    } else {
				    	if (properties.getBool(TypeMapKeys.IsWorkspace, false)) {
				    		Class[] paramTypes = {String.class};
					        Object[] args = {new String(instName) };

					    	component = (Component) Class.forName(className).getConstructor(paramTypes).newInstance(args);
					    	
					    	System.out.println( "New component: " + component);
					        
					    }
				    }
			    }
			    	
				
			    
				ret = registerInstance(instName, sessionName+"@"+locationName+":"+instName+":" + properties.getString(TypeMapKeys.LocationPortNameKey, null), component);
				setComponentProperties(ret, properties);				
				component.setServices(framework.getServices(instName, className, null));
				/* Register any component type in the componentMap */
				ForroDriver.getComponentMap().put(ret, component);
				System.out.println("COMPONENT CREATED!!!!! Name: " + className);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
		
		
	}

	public AbstractFramework framework() {
		return framework;
	}

	@Override
	protected TypeMap createTypeMap() throws CCAException {
		return framework().createTypeMap();
	}

	/**
	 * Gets the location name.
	 * @return session name
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * Gets the session name.
	 * @return session name
	 */
	public String getSessionName() {
		return sessionName;
	}


	public Port lookupPort(String className, String portName) throws CCAException {
		return lookupServices(className).getPort(portName);
	}
	
	public Services lookupServices(String className) throws CCAException {
		Forro framework1 = (Forro) framework; 
		Map servicesMap = framework1.getServicesMap();
		Services svc = (Services) servicesMap.get(className);
		if (svc!=null)
			return svc;

		
		return null;
	}

	
}
