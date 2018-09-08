/**
 * 
 */
package forrocore;

import ccacore.AbstractFramework;
import ccacore.BuilderService;
import ccacore.Component;
import ccacore.ConnectionEventService;

/**
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br), Gisele Azevedo (gisele@lia.ufc.br)
 *
 * @see Component
 * @see BuilderService
 * @see ConnectionEventService 
 */
public interface SessionDriver extends Component, BuilderService, ConnectionEventService, ProbeServices {
	AbstractFramework framework();
	
	
	//public Services getServices1(String Name);
}
