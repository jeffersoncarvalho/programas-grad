package ccacore;
import exceptions.CCAException;


/**
 * An opaque reference to a Component. It
 * is the handle that an application
 * receives upon instantiation of a
 * component.
 */
public interface ComponentID {
  /** 
   * Produce a string that, within the current framework, 
   * that uniquely defines this component reference. 
   * The cca spec has the method toString(...)
   * We don't use that method as it is a standard
   * java method that will get overloaded if redefine
   * the method in this interface
   */
   public String toUniqueString(); 
  
  /** 
   * Returns the instance name provided in 
   * <code>BuilderService.createInstance()</code>
   * or in 
   * <code>AbstractFramework.getServices()</code>.
   * @throws CCAException if <code>ComponentID</code> is invalid
   */
   String getInstanceName() throws CCAException;

  /**
   * Returns a framework specific serialization of the ComponentID.
   * @throws CCAException if <code>ComponentID</code> is
   * invalid.
   */
   String getSerialization() throws CCAException;

  
  
}



