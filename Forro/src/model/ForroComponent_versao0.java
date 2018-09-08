/**
 * 
 */
package model;

import java.util.Map;

import ccacore.Component;
import ccacore.Port;
import ccacore.Services;
import model.ComponentOID;


/**
 * @author gisele
 *
*/
public class ForroComponent_versao0 implements Component {
	
	ModelContext forroComp;
	Map properties;
	ComponentOID compOID;
	
	
	



	/**
	 * @param compOID
	 * @param properties
	 */
	public ForroComponent_versao0(ComponentOID compOID, Map properties) {
		super();
		this.properties = properties;
		this.compOID = compOID;
	}



	/**
	 * Constructor
	 * @param forroComp
	 * @param properties
	 * @param compOID
	 */
	public ForroComponent_versao0(ModelContext forroComp, Map properties, ComponentOID compOID) {
		super();
		this.forroComp = forroComp;
		this.properties = properties;
		this.compOID = compOID;
	}

	
	
	public ForroComponent_versao0(ModelContext forroComp) {
		super();
		this.forroComp = forroComp;
	}

	
	public ForroComponent_versao0() {
		super();
	}
	
	
	/* (non-Javadoc)
	 * @see ccacore.Component#setServices(ccacore.Services)
	 */
	public void setServices(Services services) {
		String[] providesPortList = forroComp.providesPortList();
		Port[] providesPort = new Port[providesPortList.length];
		for (int i = 0; i < providesPortList.length; i++)
			providesPort[i] = forroComp.getProvidesPort(providesPortList[i], null);
		
		String[] usesPortList = forroComp.usesPortList();
	}



	/**
	 * @return the compOID
	 */
	public ComponentOID getCompOID() {
		return compOID;
	}



	/**
	 * @param compOID the compOID to set
	 */
	public void setCompOID(ComponentOID compOID) {
		this.compOID = compOID;
	}



	/**
	 * @return the properties
	 */
	public Map getProperties() {
		return properties;
	}



	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map properties) {
		this.properties = properties;
	}

}
