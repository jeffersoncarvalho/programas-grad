package model;

import ccacore.ComponentID;
import exceptions.CCAException;

/**
 * @author Gisele Azevedo
 *
 */
public class ComponentOID implements ComponentID {

	
	/* Component name */
	private String componentName;
	
	/* Component session */
	private String session;

	/* Workspace name of the component*/
	private String location;


	/**
	 * Construtor
	 * @param componentName
	 * @param session
	 * @param location
	 */
	public ComponentOID(String componentName, String id, String workspace) {
		super();
		this.componentName = componentName;
		this.session = id;
		this.location = workspace;
	}
	/**
	 * Construtor
	 */
	public ComponentOID() {
	}

	/**
	 * Construtor
	 * @param componentName
	 */
	public ComponentOID(String name) {
		super();
		this.componentName = name;
	}
	
	public String getInstanceName() throws CCAException {
		return null;
	}

	public String getSerialization() throws CCAException {
		return null;
	}

	public String toUniqueString() {
		return null;
	}

	/**
	 * Get the OID componentName
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * Set the OID componentName
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String name) {
		this.componentName = name;
	}
	/**
	 * Get the component local ID
	 * @return the session
	 */
	public String getSession() {
		return session;
	}
	/**
	 * Set the component local ID
	 * @param session the session to set
	 */
	public void setSession(String id) {
		this.session = id;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String workspace) {
		this.location = workspace;
	}


}
