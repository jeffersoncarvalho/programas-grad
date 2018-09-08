

package ccacore;
import exceptions.CCAException;

public interface Services {
  
  
  
  /** 
   * Fetch a previously registered Port (defined by either 
   * addProvidePort or (more typically) registerUsesPort).  
   * @return Will return the Port (possibly waiting forever while
   * attempting to acquire it) or throw an exception. Does not return
   * NULL, even in the case where no connection has been made. 
   * If a Port is returned,
   * there is then a contract that the port will remain valid for use
   * by the caller until the port is released via releasePort(), or a 
   * Disconnect Event is successfully dispatched to the caller,
   * or a runtime exception (such as network failure) occurs during 
   * invocation of some function in the Port. 
   * <p>
   * Subtle interpretation: If the Component is not listening for
   * Disconnect events, then the framework has no clean way to
   * break the connection until after the component calls releasePort.
   * </p>
   * <p>The framework may go through some machinations to obtain
   *    the port, possibly involving an interactive user or network 
   *    queries, before giving up and throwing an exception.
   * </p>
   * 
   * @param portName The previously registered or provide port which
   *         the component now wants to use.
   * @exception CCAException with the following types: NotConnected, PortNotDefined, 
   *                NetworkError, OutOfMemory.
   */
  public Port getPort(String portName) throws CCAException;
  
  
  
  
  /**
   * Get a previously registered Port (defined by
   * either addProvide or registerUses) and return that
   * Port if it is available immediately (already connected
   * without further connection machinations).
   * There is an contract that the
   * port will remain valid per the description of getPort.
   * @return The named port, if it exists and is connected or self-provided,
   *            or NULL if it is registered and is not yet connected. Does not
   *            return if the Port is neither registered nor provided, but rather
   *            throws an exception.
   * @param portName registered or provided port that
   *           the component now wants to use.
   * @exception CCAException with the following types: PortNotDefined, OutOfMemory.
   */
  public Port getPortNonblocking(String portName) throws CCAException;
  
  /** 
   * Notifies the framework that this component is finished 
   * using the previously fetched Port that is named.     
   * The releasePort() method calls should be paired with 
   * getPort() method calls; however, an extra call to releasePort()
   * for the same name may (is not required to) generate an exception.
   * Calls to release ports which are not defined or have never be fetched
   * with one of the getPort functions generate exceptions.
   * @param portName The name of a port.
   * @exception CCAException with the following types: PortNotDefined, PortNotInUse.
   */
   public void releasePort(String portName) throws CCAException;
  
  /** 
    * Register a request for a Port that will be retrieved subsequently 
    * with a call to getPort().
    * @param portName A string uniquely describing this port.  This string
    * must be unique for this component, over both uses and provides ports.
    * @param type A string desribing the type of this port.
    * @param properties A TypeMap describing optional properties
    * associated with this port. This can be a null pointer, which
    * indicates an empty list of properties.  Properties may be
    * obtained from createTypeMap or any other source.  The properties
    * be copied into the framework, and subsequent changes to the
    * properties object will have no effect on the properties
    * associated with this port.
    * In these properties, all frameworks recognize at least the
    * following keys and values in implementing registerUsesPort:
    * <pre>
    * key:              standard values (in string form)     default
    * "MAX_CONNECTIONS" any nonnegative integer, "unlimited".   1
    * "MIN_CONNECTIONS" any integer > 0.                        0
    * "ABLE_TO_PROXY"   "true", "false"                      "false"
    * </pre>
    * The component is not expected to work if the framework
    * has not satisfied the connection requirements.
    * The framework is allowed to return an error if it
    * is incapable of meeting the connection requirements,
    * e.g. it does not implement multiple uses ports.
    * The caller of registerUsesPort is not obligated to define
    * these properties. If left undefined, the default listed above is
    *       assumed.
    * @exception CCAException with the following types: PortAlreadyDefined, OutOfMemory.
    */
   void registerUsesPort(String portName, 
                         String type, 
                         TypeMap properties) throws CCAException ;

  /** 
    * Notify the framework that a Port, previously registered by this
    * component but currently not in use, is no longer desired. 
    * Unregistering a port that is currently 
    * in use (i.e. an unreleased getPort() being outstanding) 
    * is an error.
    * @param name The name of a registered Port.
    * @exception CCAException with the following types: UsesPortNotReleased, PortNotDefined.
    */
   public void unregisterUsesPort(String portName) throws CCAException;
  
  /** 
    * Exposes a Port from this component to the framework.  
    * This Port is now available for the framework to connect 
    * to other components. 
    * @param inPort An abstract interface (tagged with CCA-ness
    *      by inheriting from gov.cca.Port) the framework will
    *      make available to other components.
    * 
    * @param portName string uniquely describing this port.  This string
    * must be unique for this component, over both uses and provides ports.
    * 
    * @param type string describing the type (class) of this port.
    * 
    * @param properties A TypeMap describing optional properties
    * associated with this port. This can be a null pointer, which
    * indicates an empty list of properties.  Properties may be
    * obtained from createTypeMap or any other source.  The properties
    * be copied into the framework, and subsequent changes to the
    * properties object will have no effect on the properties
    * associated with this port.
    * In these properties, all frameworks recognize at least the
    * following keys and values in implementing registerUsesPort:
    * <pre>
    * key:              standard values (in string form)     default
    * "MAX_CONNECTIONS" any nonnegative integer, "unlimited".   1
    * "MIN_CONNECTIONS" any integer > 0.                        0
    * "ABLE_TO_PROXY"   "true", "false"                      "false"
    * </pre>
    * The component is not expected to work if the framework
    * has not satisfied the connection requirements.
    * The framework is allowed to return an error if it
    * is incapable of meeting the connection requirements,
    * e.g. it does not implement multiple uses ports.
    * The caller of addProvidesPort is not obligated to define
    * these properties. If left undefined, the default listed above is
    * assumed.
    * @exception CCAException with the following types: PortAlreadyDefined, OutOfMemory.
    */
    public void addProvidesPort(Port inPort, 
                        String portName, 
                        String type, 
                        TypeMap properties) throws CCAException ;
  
  /** 
    * Notifies the framework that a previously exposed Port is no longer 
    * available for use. The Port being removed must exist
    * until this call returns, or a CCAException may occur.
    * @param name The name of a provided Port.
    * @exception PortNotDefined. In general, the framework will not dictate 
    * when the component chooses to stop offering services.
    */
    public void removeProvidesPort(String portName) throws CCAException;

  /** 
    * Get a reference to the component to which this 
    * Services object belongs. 
    */
   public ComponentID  getComponentID() throws CCAException;


  /** 
   * Creates a TypeMap, potentially to be used in subsequent
   * calls to describe a Port.  Initially, this map is empty.
   */
  TypeMap createTypeMap() throws CCAException;


  
  /** 
   * Returns the complete list of the properties for a Port.  This
   * includes the properties defined when the port was registered
   * (these properties can be modified by the framework), two special
   * properties "cca.portName" and "cca.portType", and any other
   * properties that the framework wishes to disclose to the component.
   * The framework may also choose to provide only the subset of input
   * properties (i.e. from addProvidesPort/registerUsesPort) that it
   * will honor.      */
  TypeMap getPortProperties(String name) ;





  
  
  

}
  



