package session.workspace;

import ccacore.ConnectionID;
import ccacore.Port;

/**
 *  Workspace's Port that provides port connections to a Session Driver.  
 * @author gisele
 *
 */
public interface Connector extends Port {

	
	/** Provides the Uses Port for a Session Driver.
	 * @param usesPortName
	 * @param link
	 * @return
	 */
	Port getConnectedUsesPort(String usesPortName, ConnectionID link);
}
