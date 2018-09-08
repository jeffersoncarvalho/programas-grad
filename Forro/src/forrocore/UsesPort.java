package forrocore;

import session.datatype.Datatype;
import ccacore.Component;
import ccacore.Port;
import exceptions.CCAException;

/**
 * A uses port.
 * 
 * @author  Ricardo Corrêa (correa@lia.ufc.br), Gisele Azevedo (gisele@lia.ufc.br)
 */
interface UsesPort extends Port, Component {
	
	/**
	 * Invoked by a link to set the result of a previous invocation.
	 */
	public void setResult(Datatype result) throws CCAException;
}