package ccacore;
import exceptions.CCAException;



/** 
 *    BuilderService is a Port implemented by a CCA compliant framework for
 * the purpose of composing components into applications in a standard way.
 * It is meant to expose the Component creation and composition functionality
 * without the specific framework implementation. This interface is expected 
 * to be useful for rapid application development in a scripting language. 
 * Other uses are generic application development environments for CCA 
 * applications. 
 * <p>Each of the fundamental component architecture pieces
 *    (instances of Component, Port, and Connection) may have
 *    an associated TypeMap of properties managed by the framework.
 *    The standardized keys in the properties of a Port are documented
 *    in Services.getPortProperties().
 *    The standardized keys in the properties of a Component and Connection
 *    are documented below.
 *  </p>
 *  <p>For connection, thus far:
 *    <pre>
 *    Key BuilderService        value           meaning
 *    cca.isInUse boolean         true if there have been more successful
 *                               getPort than releasePort calls for the
 *                               connection at the the time 
 *                               properties were fetched.
 *   </pre>
 *   </p>
 *  <P>For component, thus far:
 *   <pre>
 *    Key                 value           meaning
 *    cca.className      String          component type
 *   </pre>
 *  </p>
 */       
public interface BuilderService extends Port {
	
	
    /**
     *  Creates an instance of a CCA component of the type defined by the 
     *  string className.  The string classname uniquely defines the
     *  "type" of the component, e.g.
     *      doe.cca.Library.GaussianElmination. 
     * @param instanceName the name given to the instance. It may be empty (zero length) in which case
     *  the instanceName will be assigned to the component automatically.
     * @param className the class that implements the component
     * @param properties that define the instance. Usually, these are the parameters of the ComponentID implementation and
     *                   className.
     *  @throws CCAException If the Component className is unknown, or if the
     *          instanceName has already been used, a CCAException is thrown.
     *  @return A ComponentID corresponding to the created component. Destroying
     *          the returned ID does not destroy the component; 
     *          see destroyInstance instead.
     */
    ComponentID createInstance(String instanceName, String className, 
                                       TypeMap properties) 
        throws CCAException ; 
    
    /** 
     *  Get component list.
     *  @return a ComponentID for each component currently created.
     */
    ComponentID[] getComponentIDs() throws CCAException ; 
    
    /** 
     *  Get property map for component.
     *  @return the public properties associated with the component referred to by
     *  ComponentID. 
     *  @throws a CCAException if the ComponentID is invalid.
     */
    TypeMap getComponentProperties( ComponentID cid) 
        throws CCAException ;
    
    /**
     *  Causes the framework implementation to associate the given properties 
     *  with the component designated by cid. 
     *  @throws CCAException if cid is invalid or if there is an attempted
     *  change to a property locked by the framework implementation.
     */
    void setComponentProperties( ComponentID cid,  TypeMap map) 
        throws CCAException ;
    
    /** Get component id from stringified reference.
     *    @return a ComponentID from the string produced by 
     *  ComponentID.getSerialization(). 
     *    @throws CCAException if the string does not represent the appropriate 
     *   serialization of a ComponentID for the underlying framework.
     */
    ComponentID getDeserialization(String s) throws CCAException ;
    
    /** Get id from name by which it was created.
     *  @return a ComponentID from the instance name of the component
     *  produced by ComponentID.getInstanceName().
     *  @throws CCAException if there is no component matching the 
     *  given componentInstanceName.
     */
    ComponentID getComponentID(String componentInstanceName) 
        throws CCAException ;
    
    /** 
     *  Eliminate the Component instance, from the scope of the framework.
     *  @param toDie the component to be removed.
     *  @param timeout the allowable wait; 0 means up to the framework.
     *  @throws CCAException if toDie refers to an invalid component, or
     *  if the operation takes longer than timeout seconds.
     */
    void destroyInstance( ComponentID toDie,  float timeout ) 
        throws CCAException ;
    
    /** 
     *  Get the names of Port instances provided by the identified component.
     *  @param cid the component.
     *  @throws CCAException if cid refers to an invalid component.
     */
    String[] getProvidedPortNames( ComponentID cid) 
        throws CCAException ; 
    
    /** 
     *  Get the names of Port instances used by the identified component.
     *  @param cid the component.
     *  @throws CCAException if cid refers to an invalid component. 
     */
    String[] getUsedPortNames( ComponentID cid) 
        throws CCAException ; 
    
    /** 
     *  Fetch map of Port properties exposed by the framework.
     *  @return the public properties pertaining to the Port instance 
     *    portname on the component referred to by cid. 
     *  @throws CCAException when any one of the following conditions occur:<ul>
     *    <li>portname is not a registered Port on the component indicated by cid,
     *    <li>cid refers to an invalid component. </ul>
     */ 
    TypeMap getPortProperties( ComponentID cid, String portName) 
        throws CCAException ;
            

    /** 
     *  Associates the properties given in map with the Port indicated by 
     *  portname. The component must have a Port known by portname.
     *  @throws CCAException if either cid or portname are
     *  invalid, or if this a changed property is locked by 
     *   the underlying framework or component.
     */
    void setPortProperties( ComponentID cid, String portName, 
                            TypeMap map) throws CCAException ;
    
    /**
     *   Creates a connection between ports on component user and 
     *   component provider. Destroying the ConnectionID does not
     *   cause a disconnection; for that, see disconnect().<p>
     *   
     *   This method does not set the connection properties. For this reason,
     *   the connection created may be not effectively useful before the
     *   <tt>setConnectionProperties</tt> is invoked, in which case this 
     *   method is also responsible for generating <tt>ConnectionEvent</tt>.
     *   
     * @param user the component that registers the user port of the connection created
     * @param usingPortName the name of the uses port registered by the user
     * @param provider the component that holds the user port of the connection created
     * @param providingPortName the name of the provides port defined by the user

     * @throws CCAException when any one of the following conditions occur:<ul>
     *   <li>If either user or provider refer to an invalid component,
     *   <li>If either usingPortName or providingPortName refer to a 
     *   nonexistent Port on their respective component,
     *   <li>If other-- In reality there are a lot of things that can go wrong 
     *   with this operation, especially if the underlying connections 
     *   involve networking.</ul>
     */
    ConnectionID connect(ComponentID user, 
                                 String usingPortName, 
                                 ComponentID provider, 
                                 String providingPortName) 
        throws CCAException ; 
    
    /** Returns a list of connections as an array of 
     *  handles. This will return all connections involving components 
     *  in the given componentList of ComponentIDs. This
     *  means that ConnectionID's will be returned even if only one 
     *  of the participants in the connection appears in componentList.
     * 
     *  @throws CCAException if any component  componentList is invalid.
     */
     ConnectionID[] getConnectionIDs(ComponentID[] componentList) 
        throws CCAException ; 
    
    /**
     *   Fetch property map of a connection.
     *   @returns the properties for the given connection.
     *   @throws CCAException if connID is invalid.
     */
    TypeMap getConnectionProperties(ConnectionID connID) 
        throws CCAException ;
    
    /** Associates the properties with the connection.
     *   @param map the source of the properties.
     *   @param connID connection to receive property values.
     *   @throws CCAException if connID is invalid, or if this changes 
     *   a property locked by the underlying framework.
     */
    void setConnectionProperties( ConnectionID connID,  TypeMap map) 
        throws CCAException ;
    
    /** Disconnect the connection indicated by connID before the indicated
     *     timeout in secs. Upon successful completion, connID and the connection
     *     it represents become invalid. 
     *     @param timeout the time in seconds to wait for a connection to close; 0
     *     means to use the framework implementation default.
     *     @throws CCAException when any one of the following conditions occur: <ul>
     *     <li>id refers to an invalid ConnectionID,
     *     <li>timeout is exceeded, after which, if id was valid before 
     * disconnect() was invoked, it remains valid
     * </ul>
     * 
     */
    void disconnect( ConnectionID connID,  float timeout) 
        throws CCAException ; 
    
    /** Remove all connections between components id1 and id2 within 
     *   the period of timeout secs. If id2 is null, then all connections 
     *   to id1 are removed (with the period of timeout secs).
     *   @throws CCAException when any one of the following conditions occur:<ul>
     *    <li>id1 or id2 refer to an invalid ComponentID (other than id2 == null),
     *    <li>The timeout period is exceeded before the disconnections can be made. 
     *    </ul>
     */
    void disconnectAll( ComponentID id1,  ComponentID id2, 
                        float timeout) throws CCAException ; 
    
} // end interface BuilderService
