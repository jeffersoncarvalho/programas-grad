package ccacore;

/**
 * Event created when two components are connected.
 */        
public interface ConnectionEvent {
    /** 
     * <p>Returns the integer from those enumerated that describes the event.</p>
     * 
     * <p>
     * The semantics are noted before
     * each member of the enum/static constant. We can add in different
     * types of connect/disconnect as multiports and
     * explicit local/global/sync/async semantics are agreed to in the future.
     * At present we assume that:
     * <ul>
     * <li> All instances in a component cohort (often thought of as a single
     *   "parallel component") receive all the events
     *   and in the same order, but not necessarily globally synchronously.
     *
     * <li> For disconnections, within a process the events are delivered first
     *   to the using component then (if necessary) to the providing
     *   component.
     * 
     * <li> For connections, within a process the events are delivered first
     *   to the providing component then (if necessary) to the using
     *   component.
     * </ul>
     * </p>
     * 
     * <p>
     * Clearly some of the assumptions above may not suit a component
     * instance in which multiple execution threads act on a
     * single instance of the <code>cca.Services</code> object (SMP). The Services
     * specification is ambiguous as to whether such a component is even
     * allowed.
     * </p>
     * <p>
     * When this is clarified, additional members of the enum may arise,
     * in which case the assumptions here apply only to
     * <code>ConnectPending</code>, <code>Connected</code>, <code>DisconnectPending</code>, 
     * <code>Disconnected</code> types.
     */
    EventType getEventType();
    
    /** 
     * Get Properties of the affected Port.
     * Among the standard properties are the name and type info.
     */
    TypeMap getPortInfo();
}

