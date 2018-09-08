package ccacore;

/**
 * This is the interface that a component must provide in order to 
 * be notified of <code>ConnectEvents</code>.
 */        
public interface ConnectionEventListener {
    /** 
     * Called on all listeners when a connection is made or broken. 
     */
    void connectionActivity(ConnectionEvent ce);
}
