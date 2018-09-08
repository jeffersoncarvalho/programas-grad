package forrocore;

import session.datatype.Datatype;
import ccacore.Services;
import exceptions.CCAException;

/**
 * A uses port.
 * @author  Ricardo Corr�a (correa@lia.ufc.br)
 */
abstract class AbstractUsesPort implements UsesPort {
	
	private Services services;
	
	private String name;
	
	private String linkPortName;
	
	/**
	 * Creates a new uses port with a specified name and connected
	 * to a specified provides port through the specified link.
	 * @param name the port's name
	 */
	AbstractUsesPort(String name) {
		this.name = name;
		linkPortName = name+"LinkPort";
	}

	public void setServices(Services s) {
		this.services = s;
		try {
			s.addProvidesPort(this, name, getClass().getName(), s.createTypeMap());
			s.registerUsesPort(linkPortName, Link.class.getName(), s.createTypeMap());
		} catch (CCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected Link getLinkPort() {
		Link ret = null;
		try {
			ret = (Link) services.getPortNonblocking(linkPortName);
		} catch (CCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Invoked by a link to set the result of a previous invocation.
	 */
	public void setResult() {
		
	}
	
	/**
	 * Invoke a method through a link and waits until the result is available,
	 * blocking this port.
	 * 
	 * @return the result of the invocation
	 */
	protected Datatype blockingInvocation() {
		return null;
	}
	
	/**
	 * Invoke a method through a link and waits until the result is available,
	 * without blocking this port.
	 * 
	 * @return the result of the invocation
	 */
	protected Datatype nonBlockingInvocation() {
		return null;
	}
}