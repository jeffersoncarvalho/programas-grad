package model;

import ccacore.ComponentID;
import exceptions.CCAException;

/**
 * A ComponentID that identifies a component with a string using
 * the following format:
 * <p>
 * name[length-1]....name[0].instanceName
 * </p>
 * 
 * @author gisele
 *
 */
public final class StringArrayComponentID implements ComponentID {

	private String instanceName;
	private String uniqueName;
	
	public StringArrayComponentID(String instanceName, String[] name) {
		super();
		this.instanceName = instanceName;
		uniqueName = instanceName;
		for (int i = name.length - 1; i >= 0; i--)
			uniqueName = name[i]+"."+uniqueName;
	}

	public String toUniqueString() {
		return uniqueName;
	}

	public String getInstanceName() throws CCAException {
		return instanceName;
	}

	public String getSerialization() throws CCAException {
		// TODO Auto-generated method stub
		return null;
	}

}
