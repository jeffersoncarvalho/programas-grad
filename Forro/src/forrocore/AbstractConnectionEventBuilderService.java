/* project    : forro2.0
 * file name  : AbstractSessionDriverComponent.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br), Francisco de Carvalho Junior (heron@lia.ufc.br), Gisele Ara�jo (gisele@lia.ufc.br)
 * created    : 03/08/2006
 * copyright  : Federal University of Cear�, Brazil
 *
 * modifications:
 *
 */

package forrocore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import ccacore.ComponentID;
import ccacore.ConnectionEvent;
import ccacore.ConnectionEventListener;
import ccacore.ConnectionEventService;
import ccacore.ConnectionID;
import ccacore.EventType;
import ccacore.TypeMap;
import exceptions.CCAException;

/**
 * This class provides a skeletal implementation of a session driver component to minimize the effort required to implement this
 * type of component. In addition, this class provides specific implementations of session driver and session builder ports. <p>
 *
 * The process of implementing a session driver by extending this class, the programmer needs only to
 * provide an implementation for the <tt>ccacore.Component.setServices</tt> method. Usually, the implementation of this method
 * decides of what type of session driver and session builder ports are used.
 * 
 * <p>In addition to the method <tt>ccacore.Component.setServices</tt>, a concrete implementation 
 * of this class should implement the certification policy.
 * 
 * <p>There is a special type of session driver: the one whose session driver provides port is connected to the <tt>FrontEnd</tt>.
 * In this case, it keeps a map of all workspaces started by this session
 * GUI. Otherwise, it only keeps the workspaces this location belongs to.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public abstract class AbstractConnectionEventBuilderService extends AbstractBuilderService implements ConnectionEventService {
	
	private final Map1<EventType, Set<ConnectionEventListener>> listenerMap = new HashMap1<EventType, Set<ConnectionEventListener>>();
	
	/**
	 */
	AbstractConnectionEventBuilderService() {
		super();
	}

	protected abstract TypeMap createTypeMap() throws CCAException;
	
	public ConnectionID connect(final ComponentID user, final String usingPortName, final ComponentID provider, final String providingPortName) throws CCAException {
		ConnectionID ret = super.connect(user, usingPortName, provider, providingPortName);
		final EventType e = EventTypes.newEventType(user, EventType.Connected);
//		Set<ConnectionEventListener> listConnectionEventListener = (Set<ConnectionEventListener>) listenerMap.get(e);
		Set listConnectionEventListener = (Set) listenerMap.get(e);
		 
		
		ConnectionEventListener l;
//		for (ConnectionEventListener l : listConnectionEventListener)
		
		 Iterator iter = listConnectionEventListener.iterator ();	
		 
		 while (  iter.hasNext (  )   )		
		 {
			    l = (ConnectionEventListener) iter.next ();
				l.connectionActivity(new ConnectionEvent() {

					public EventType getEventType() {
						return e;
					}

					public TypeMap getPortInfo() {
						TypeMap info = null;
						try {
							info = createTypeMap();
							info.putString(TypeMapKeys.UsesPortNameKey, usingPortName);
							info.putString(TypeMapKeys.ProviderNameKey, provider.getInstanceName());
							info.putString(TypeMapKeys.ProvidesPortNameKey, providingPortName);
						} catch (CCAException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return info;
					}
					
				});
		 }
		return ret;
	}

	public void disconnect(final ConnectionID connID, final float timeout) throws CCAException {
		super.disconnect(connID, timeout);
		
		Thread eThread = new Thread() {
			final EventType e = EventTypes.newEventType(connID.getUser(), EventType.Disconnected);
			@Override
			public void run() {
				for (ConnectionEventListener l : listenerMap.get(e))
					l.connectionActivity(new ConnectionEvent() {

						public EventType getEventType() {
							return e;
						}

						public TypeMap getPortInfo() {
							TypeMap info = null;
							try {
								info = createTypeMap();
								info.putString(TypeMapKeys.UsesPortNameKey, connID.getUserPortName());
							} catch (CCAException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return info;
						}

					});
			}
		};
		eThread.start();
		try {
			double millis = timeout * 10e3;
			eThread.join(Math.round(millis));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (eThread.isAlive()) {
			eThread.interrupt();
			throw new CCAException("Timeout exceeded. Connection status is undetermined.");
		}
	}

	/* (non-Javadoc)
	 * @see ccacore.ConnectionEventService#addConnectionEventListener(ccacore.EventType, ccacore.ConnectionEventListener)
	 */
	public void addConnectionEventListener(EventType et, ConnectionEventListener cel) {
		
		Set<ConnectionEventListener> listeners = null;
		listeners = listenerMap.get(et);
		
		if (listeners == null) {
	        listeners = new HashSet<ConnectionEventListener>();
	        listeners.add(cel);
	        listenerMap.put(et, listeners);
	    }
		else {
			//for (ConnectionEventListener l : listeners)
				listeners.add(cel);
			listenerMap.put(et, listeners);
			
		}

		


		
	}

	/* (non-Javadoc)
	 * @see ccacore.ConnectionEventService#removeConnectionEventListener(ccacore.EventType, ccacore.ConnectionEventListener)
	 */
	public void removeConnectionEventListener(EventType et, ConnectionEventListener cel) {
		listenerMap.get(et).remove(cel);
	}
	
	
	
	interface Map1<EventType, Set>
	{
	    public void put(EventType ev, Set listEvt);
	 
	    public Set get(EventType ev);
	}
	 
	class HashMap1<EventType, Set> implements Map1<EventType, Set>
	{
	    java.util.HashMap<EventType, Set> map = new java.util.HashMap<EventType, Set>();
	 
	    public void put(EventType ev, Set listEvt)
	    {
	        map.put(ev, listEvt);
	    }
	 
	    public  Set get(EventType ev)
	    {
	        return map.get(ev);
	    }
	}
	 
	 
	


}
