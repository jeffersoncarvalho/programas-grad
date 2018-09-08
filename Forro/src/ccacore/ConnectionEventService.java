package ccacore;

/**
 * Connection event service.
 */        
public interface ConnectionEventService extends Port {
    /** 
     * Sign up to be told about connection activity.
     * connectionEventType must be one of the integer 
     * values defined iN ConnectionEvent. 
     */
    void addConnectionEventListener(EventType et,
                                    ConnectionEventListener cel);
    
    /** 
     * Ignore future ConnectionEvents of the given type.
     * connectionEventType must be one of the integer values 
     * defined in ConnectionEvent. 
     */
    void removeConnectionEventListener(EventType et, 
                                       ConnectionEventListener cel);
}
