package ccacore;
import exceptions.CCAException;

/**
 *  This interface describes a CCA connection between components.
 *  A connection is made at the users direction
 *  when one component provides a Port that another component
 *  advertises for and uses.  The components are referred to by their
 *  opaque ComponentID references and the Ports are referred to
 *  by their string instance names.
 */    
public interface ConnectionID {
   
    /** 
     *  Get the providing component (callee) ID.
     *  @return ComponentID of the component that has 
     *          provided the Port for this connection. 
     *  @throws CCAException if the underlying connection 
     *            is no longer valid.
     */
    ComponentID getProvider() throws CCAException ;
    
    /** 
     *  Get the using component (caller) ID.
     *  @return ComponentID of the component that is using the provided Port.
     *  @throws CCAException if the underlying connection is no longer valid.
     */
    ComponentID getUser() throws CCAException ; 
    
    /** 
     *  Get the port name in the providing component of this connection.
     *  @return the instance name of the provided Port.
     *  @throws CCAException if the underlying connection is no longer valid.
     */
    String getProviderPortName() throws CCAException ; 
    
    /** 
     *  Get the port name in the using component of this connection.
     *  Return the instance name of the Port registered for use in 
     *  this connection.
     *  @throws CCAException if the underlying connection is no longer valid.
     */
    String getUserPortName() throws CCAException ; 
    
}  
