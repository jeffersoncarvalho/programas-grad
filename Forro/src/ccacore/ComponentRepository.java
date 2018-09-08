package ccacore;
import exceptions.CCAException;

/** 
 *  ComponentRepository is a Port implemented by a CCA compliant framework 
 *  or other component to expose in a standard way:<ol>
 *  <li>immediately instantiable component classes.
 *  <li>component class property maps. 
 *  <li>the operations used to obtain more component classes at runtime.
 *  </ol>
 *   
 */

public interface ComponentRepository  extends Port {
    /** 
     *  Collect the currently obtainable class name strings from
     *  factories known to the builder and the from the
     *  already instantiated components.
     *  @return The list of class description, which may be empty, that are
     *   known a priori to contain valid values for the className
     *  argument of createInstance. 
     *  @throws CCAException in the event of error.
     */
    ComponentClassDescription[] getAvailableComponentClasses() 
        throws CCAException ;
    
} // end of cca.ports.ComponentRepository