/* file name  : ModelContext.java
 * authors    : Ricardo Corrï¿½a (correa@lia.ufc.br)
 * created    : Feb 6, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package model;






/**
 * All <i>Forró</i> models implement this interface. A model is defined by its input parameters, a <tt>solve</tt>
 * method and a number of external views. This interface provides methods for setting the input parameters,
 * solving the model and getting external views.
 * 
 * <p>The methods of every implementation of this interface should return either <tt>Serializable</tt> or <tt>Datatype</tt>
 * objects. In the former case, remote invocation returns its result by value. Otherwise, the result is
 * returned by (remote) reference.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public interface ModelContext {
	/**
	 * Binds an internal uses port, specified by its name, to a provides port of another model represented
	 * by a specified uses port object. This specified uses port object should be created by the model
	 * driver of this component as a result of a request originated at the session handler. This method
	 * is used by the model driver's implementation of its <tt>bindPorts</tt> method. 
	 * 
	 * @param usesPortName internal uses port's name
	 * @param usesPort a uses port object representing a provides port of another model
	 * @return TODO
	 * 
	 * @see obsoleteClasses.ModelDriver
	 * @since April 02, 2005
	 */
	boolean putUsesPort(String usesPortName, EnumPort usesPort);
	/**
	 * Gets a view of the model according to a specified view name.
	 * The port returned depends on the <tt>startNew</tt> method of the specified
	 * binding properties.
	 * 
	 * @param providesPortName the view name
	 * @param bindingProp the binding properties
	 * 
	 * @return a port providing the requested view.
	 */
	EnumPort getProvidesPort(String providesPortName, BindingProperties bindingProp);
	/**
	 * Lists the provides ports that can be bound at this active implementation.
	 * 
	 * @return
	 */
	String[] providesPortList();
	/**
	 * Lists the uses ports that can be bound at this active implementation.
	 * 
	 * @return
	 */
	String[] usesPortList();
	/**
	 *    Executes a set of operations.
	 *  
	 */
	void operate();
}
