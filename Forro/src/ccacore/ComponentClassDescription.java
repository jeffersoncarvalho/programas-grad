package ccacore;
import exceptions.CCAException;
/** 
 *  An opaque reference to a Component Class.  
 *  (This interface is expected to grow substantially.)
 */    
public interface ComponentClassDescription { 

    /** 
     *  Returns the class name provided in 
     *   <code>BuilderService.createInstance()</code>
     *   or in
     *   <code>AbstractFramework.getServices()</code>.
     *  <p>
     *  Throws <code>CCAException</code> if <code>ComponentClassDescription</code> is invalid.
     */
    String getComponentClassName() throws CCAException;
    
}  // end interface ComponentClassDescription
