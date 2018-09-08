/* file name  : ModelFactory.java
 * authors    : Ricardo Corrï¿½a (correa@lia.ufc.br)
 * created    : Apr 3, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package model;

import java.lang.reflect.InvocationTargetException;
import session.workspace.LocationDriver;
import session.workspace.WorkspaceComponent;
import ccacore.ComponentID;
import java.lang.Integer;

import obsoleteClasses.ModelDriver;
import model.ForroComponent_versao0;


/**
 * A singleton factory that instantiates model components.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
final public class ComponentFactory {
	/**
	 * The driver of the location hosting this factory.
	 * @uml.property  name="driver"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="contextFactory:session.workspace.LocationDriver"
	 */
	private LocationDriver driver;
	/**
	 * An array of the classes of the parameters of a model context instantiation.
	 * @uml.property  name="classParam" multiplicity="(0 -1)" dimension="1"
	 */
	private Class[] classParam = { String.class };
	/**
	 * An array of the parameters of a model context instantiation.
	 * @uml.property  name="param" multiplicity="(0 -1)" dimension="1"
	 */
	private Object[] param = new Object[classParam.length];

	/**
	 * This is a singleton class
	 */
	private static ComponentFactory instance = null;
	
	/**
	 * Maximum number ID of the components in the present workspace
	 */
	private static Integer maxNumberIDComponent = new Integer(0);

	
	
	/**
	 * Creates a new factory connected to a specified location driver.
	 * 
	 * @param driver of the location
	 */
	private ComponentFactory(LocationDriver driver) {
		super();
		this.driver = driver;
	}


	
	/**
	 * Instantiates a specified class.
	 * @param instanceName the implementation's name
	 * @return the object created
	 */
	public static synchronized ComponentFactory getInstance(LocationDriver driver) {
		if (instance == null) instance = new ComponentFactory(driver);
		return instance;
	}	

	
	
	/* (non-Javadoc)
	 * @see session.Factory#newInstance(java.lang.String)
	 */
	public Object newInstance(String instanceName) {
		ModelContext context = null;
		try {
			param[0] = instanceName;
			context = (ModelContext) Class.forName(instanceName).getConstructor(classParam).newInstance(param);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelDriver(context);
	}

	
	
	/**
	 * Create a component ID for a new ForroDriver component.
	 * 
	 * @param componentInstanceName the component implementation's name
	 * @param properties the component implementation's name
	 * 
	 * @return the object ID created
	 */
	public ComponentOID createOID(String componentInstanceName, String workspaceName) {
        ComponentOID compOID;
        Integer numberID;
		
        numberID = getMaxNumberIDComponent();
        
		setMaxNumberIDComponent(new Integer(numberID.intValue() + 1));
		numberID = getMaxNumberIDComponent();
		compOID = new ComponentOID(componentInstanceName, numberID.toString(), workspaceName);
		
		//Create the ID of new component and put the properties
		return compOID; 

	}	
	
	
	
	
	/**
	 * @return the driver
	 */
	public LocationDriver getDriver() {
		return driver;
	}



	/**
	 * @param driver the driver to set
	 */
	public void setDriver(LocationDriver driver) {
		this.driver = driver;
	}



	/**
	 * Create and instantiate a ForroDriver component.
	 * 
	 * @param componentInstanceName the component implementation's name
	 * @param properties the component implementation's name
	 * 
	 * @return the object ID created
	 */
	public ComponentID newForroComponentInstance(String componentInstanceName, ForroMap properties) {
		ComponentOID compOID  = null;
		
		try {
			ModelContext context;
			
			String workspaceName;
			ForroComponent_versao0 forroComponent;
			WorkspaceComponent workspaceComponent;
	
			workspaceName = (String) properties.get("workspace");

			workspaceComponent = new WorkspaceComponent();
			
			//Create the ID of new component and set the properties
			compOID = createOID(componentInstanceName, workspaceName);
		
			param[0] = componentInstanceName;
			
			/* Create the real component */
			context = (ModelContext) Class.forName(componentInstanceName).getConstructor(classParam).newInstance(param);
			
			properties.put("context", context);

			forroComponent = new ForroComponent_versao0(compOID, properties);
			
			//Add the new component in the WorkspaceComponent
			workspaceComponent.addComponent(componentInstanceName, forroComponent);
			
			//Add the new component in the WorkspaceComponent 
			workspaceComponent.addGlobalComponent(componentInstanceName, forroComponent);
			
			
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return compOID;
	}



	/**
	 * Get the maximum number ID of the components in the present workspace
	 * @return the maxNumberIDComponent
	 */
	public static Integer getMaxNumberIDComponent() {
		return maxNumberIDComponent;
	}



	/**
	 * Set the Maximum number ID of the components in the present workspace
	 * @param maxNumberIDComponent the maxNumberIDComponent to set
	 */
	public static void setMaxNumberIDComponent(Integer maxNumberIDComponent) {
		ComponentFactory.maxNumberIDComponent = maxNumberIDComponent;
	}

		
	
}
