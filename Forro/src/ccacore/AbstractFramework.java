package ccacore;
import exceptions.CCAException;

/** 
 *  This is an interface presented by a CCA-compliant framework to access its 
 *  application framing capabilities. Most of the manipulation of the 
 *  underlying framework is expected to be be done with the 
 *  gov.cca.BuilderService Port. This class exists as a sort of bootstrap 
 *  to get a Services object necessary to retrieve Port's, including 
 *  BuilderService, from the underlying framework. How the interface and 
 *  the underlying framework is created is entirely unspecified and is up 
 *  to the devices of the programmer and the framework provider.
 *
 *  <p>Example</p>
 *  <p>
 *  Here it is assumed that an instance of AbstractFramework
 *  is created in the main() from some hypothetical implementation.
 *  The idea is to allow a complete swap of framework choice by 
 *  changing out the specified implementation class of a framework.
 *  </p>
 *
 *  <code><pre>
 *  // java
 *  main() {
 *    cca.reference.Framework fwkimpl = new cca.reference.Framework();
 *    // change fwkimpl above to use different cca implementations when
 *    // AbstractFramework becomes part of the standard.
 *    gov.cca.AbstractFramework fwk = (gov.cca.AbstractFramework)fwkimpl;
 *    gov.cca.Services svc = 
 *     fwk.getServices("instance0","AppDriver",null);
 *    // From here on, access all services, components, etc
 *    // through svc.
 *    ...
 *    // when done
 *    fwk.releaseServices(svc);
 *    fwk.shutdownFramework();
 *  }
 *
 *  // c++
 *  int functionName() {
 *    ::gov::sandia::ccafe::FrameworkDriver fwkimpl;
 *    ::gov::cca::AbstractFrameworkPtr fwk;
 *
 *    fwk = fwkimpl.getStandardFramework();
 *    ::gov::cca::Services_Interface * svc = 0;
 *    svc = fwk->getServices("instance0","AppDriver",0);
 *    // From here on, access all services, components, etc
 *    // through svc.
 *    ...
 *    // when done
 *    fwk->releaseServices(svc);
 *    svc = 0;
 *    fwk->shutdownFramework();
 *
 *    // at scope exit, all memory is automatically cleaned up.
 *  }
 *  </pre></code>
 */
public interface AbstractFramework {

    /** 
     *  Create an empty TypeMap. Presumably this would be used in 
     *  an ensuing call to <code>getServices()</code>. The "normal" method of
     *  creating typemaps is found in the <code>Services</code> interface. It
     *  is duplicated here to break the "chicken and egg" problem.
     */

    TypeMap createTypeMap() throws CCAException;
    

    /** 
     * Retrieve a Services handle to the underlying framework. 
     * This interface effectively causes the calling program to 
     * appear as the image of a component inside the framework.
     * This method may be called any number of times
     * with different arguments, creating a new component image 
     * each time. 
     * The only proper method to destroy a Services obtained 
     * from this interface is to pass it to releaseServices.
     * 
     * @param selfInstanceName the Component instance name,
     * as it will appear in the framework.
     * 
     * @param selfClassName the Component type of the 
     * calling program, as it will appear in the framework. 
     * 
     * @param selfProperties (which can be null) the properties 
     * of the component image to appear. 
     * 
     * @throws CCAException in the event that selfInstanceName 
     * is already in use by another component.
     * 
     * @return  A Services object that pertains to the
     *          image of the this component. This is identical
     *          to the object passed into Component.setServices() 
     *          when a component is created.
     */
    Services getServices(String selfInstanceName, String selfClassName, 
                         TypeMap selfProperties) throws CCAException ;


    /** 
     * Inform framework that the <code>Services</code> handle is no longer needed by the 
     * caller and that the reference to its component image is to be
     * deleted from the context of the underlying framework. This invalidates
     * any <code>ComponentID</code>'s or <code>ConnectionID</code>'s associated 
     * with the given <code>Services</code>' component image. 
     * 
     * @param svc The result of getServices earlier obtained.
     * 
     * @throws CCAException if the <code>Services</code>
     *         handle has already been released or is otherwise rendered invalid 
     *         or was not obtained from <code>getServices()</code>.
     */
    void releaseServices(Services svc) throws CCAException ;


    /** 
     * Tell the framework it is no longer needed and to clean up after itself. 
     *  @throws CCAException if the framework has already been shutdown.
     */  
    void shutdownFramework() throws CCAException;
    
    /** 
     * Creates a new framework instance based on the same underlying 
     * framework implementation. This does not copy the existing 
     * framework, nor are any of the user-instantiated components in
     * the original framework available in the newly created 
     * <code>AbstractFramework</code>. 
     * 
     * @throws CCAException when one of the following conditions occur:
     * 
     * (1)the AbstractFramework previously had shutdownFramework() called on it, or 
     * (2)the underlying framework implementation does not permit creation 
     * of another instance.  
     */
    AbstractFramework createEmptyFramework() throws CCAException;
    
}  // end interface AbstractFramework
