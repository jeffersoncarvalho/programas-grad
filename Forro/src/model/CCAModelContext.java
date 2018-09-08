/**
 * 
 */
package model;

import ccacore.Component;
import ccacore.ComponentID;
import ccacore.Port;
import ccacore.Services;
import ccacore.TypeMap;
import exceptions.CCAException;

/**
 * @author gisele
 *
 */
public class CCAModelContext implements ModelContext {

	/**
	 * The CCA implementation of this component.
	 */
	Component ccaComp;
	/**
	 * The implementation of the <tt>Services</tt> interface to be used as a parameter of the 
	 * <tt>setServices</tt> method.
	 */
	Services serv = new Services() {

		public Port getPort(String portName) throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		public Port getPortNonblocking(String portName) throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		public void releasePort(String portName) throws CCAException {
			// TODO Auto-generated method stub
			
		}

		public void registerUsesPort(String portName, String type, TypeMap properties) throws CCAException {
			// TODO Auto-generated method stub
			
		}

		public void unregisterUsesPort(String portName) throws CCAException {
			// TODO Auto-generated method stub
			
		}

		public void addProvidesPort(Port inPort, String portName, String type, TypeMap properties) throws CCAException {
			// TODO Auto-generated method stub
			
		}

		public void removeProvidesPort(String portName) throws CCAException {
			// TODO Auto-generated method stub
			
		}

		public ComponentID getComponentID() throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		public TypeMap createTypeMap() throws CCAException {
			// TODO Auto-generated method stub
			return null;
		}

		public TypeMap getPortProperties(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	public CCAModelContext(Component ccaComp) {
		super();
		this.ccaComp = ccaComp;
		ccaComp.setServices(serv);
	}

	/* (non-Javadoc)
	 * @see model.ModelContext#putUsesPort(java.lang.String, model.EnumPort)
	 */
	public boolean putUsesPort(String usesPortName, EnumPort usesPort) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see model.ModelContext#getProvidesPort(java.lang.String, model.BindingProperties)
	 */
	public EnumPort getProvidesPort(String providesPortName,
			BindingProperties bindingProp) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see model.ModelContext#providesPortList()
	 */
	public String[] providesPortList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see model.ModelContext#usesPortList()
	 */
	public String[] usesPortList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see model.ModelContext#operate()
	 */
	public void operate() {
		// TODO Auto-generated method stub

	}

}
