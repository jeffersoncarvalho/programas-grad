/* file name  : ModelDriver.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 6, 2005
 * copyright  : 
 *
 * modifications:
 *
 */

package obsoleteClasses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import model.BindingProperties;
import model.EnumPort;
import model.ModelContext;

import ccacore.Services;
import session.datatype.Datatype;
import session.datatype.DatatypeArray;
import session.datatype.DatatypeBoolean;
import session.datatype.DatatypeFactory;
import session.datatype.DatatypeInteger;
import forrocore.AbstractWorkspaceDriver;
import forrocore.AbstractUsesPort;

/**
 * All <i>ForroDriver</i> model component has an object of this class. A model is defined by its input parameters, a <tt>solve</tt>
 * method and a number of external views. This interface provides methods for setting the input parameters,
 * solving the model and getting external views.
 * natenate
 * <p>The methods of every implementation of this interface should return either <tt>Serializable</tt> or <tt>Datatype</tt>
 * objects. In the former case, remote invocation returns its result by value. Otherwise, the result is
 * returned by (remote) reference.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public class ModelDriver {
	
	/**
	 * The model component name 
	 * @uml.property  name="modelName"
	 */
	
	private String modelName;
	/**
	 * Context of this model component.
	 * @uml.property  name="context"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ModelContext context;
	/**
	 * Maps names to provides ports
	 * @uml.property   name="providesPortMap"
	 * @uml.associationEnd   qualifier="providesPortName:java.lang.String model.PortCCA"
	 */
	private Map providesPortMap;
	/**
	 * @uml.property  name="datatypeFactory"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private DatatypeFactory datatypeFactory = DatatypeFactory.getInstance();
	
	
	/**
	 * @param context
	 */
	public ModelDriver(ModelContext context) {
		super();
		this.context = context;
		modelName = context.getClass().getName();
		providesPortMap = new HashMap();
	}
	
	/**
	 * Bind a uses port of this model described by a specified port name to the specified provides port. 
	 * This method is invoked by the location driver.
	 * @param usesPortName uses port name
	 * @param providesPortName <tt>modelName.portName</tt>
	 * @param wdriver TODO
	 * @param linkName name of the link through which the port is bound
	 * @param bindingProp TODO
	 * 
	 * @return <tt>true</tt> if the uses is properly bound
	 */
	public boolean bindUsesPort(String usesPortName, String providesPortName, AbstractWorkspaceDriver wdriver, String linkName, BindingProperties bindingProp) {
		EnumPort port = new AbstractUsesPort(providesPortName, wdriver, linkName, bindingProp);
		return context.putUsesPort(usesPortName, port);
	}

	/**
	 * Assign a specified provides port directly to a specified uses port of this model.
	 * 
	 * @param usesPortName
	 * @param port
	 * @return
	 */
	public boolean bindUsesPort(String usesPortName, EnumPort providesPort) {
		if (!context.putUsesPort(usesPortName, providesPort)) return false;
		
		return true;
	}
		
	/**
	 * Gets the views of the model according to the specified array of view names.
	 * @param providesPortName the array of view names
	 * @param bindingProp TODO
	 * 
	 * @return an array with the requested views, in the same that their names appear in the view names array
	 */
	public boolean registerProvidesPort(String providesPortName, BindingProperties bindingProp) {
		EnumPort port = context.getProvidesPort(providesPortName, bindingProp);
		providesPortMap.put(providesPortName, port);
		return true;		
	}

	/**
	 * @param providesPortName
	 * @return
	 */
	public EnumPort getRegisteredProvidesPort(String providesPortName) {
		Object p = providesPortMap.get(providesPortName);
		return p == null ? null : (EnumPort) p;
	}

	/**
	 * Returns the model context of this model component.
	 * @return  the model context
	 * @uml.property  name="context"
	 */
	ModelContext getContext() {
		return context;
	}
	/**
	 * Lists the provides ports that can be bound at this active implementation.
	 * 
	 * @return
	 */
	public String[] providesPortList() {
		return context.providesPortList();
	}
	/**
	 * Lists the uses ports that can be bound at this active implementation.
	 * 
	 * @return
	 */
	public String[] usesPortList() {
		return context.usesPortList();
	}

	/**
	 * This method performs conversion from and to Datatype.
	 * @param methodName
	 * @param args
	 * @return
	 */
	public synchronized Datatype invoke(final String methodName, final Datatype[] args) {
		int index = methodName.lastIndexOf('.');
		String portName = methodName.substring(0,index);
		EnumPort port = (EnumPort) providesPortMap.get(portName);
		
		if (port == null)
			System.out.println("Port " + portName + " is not registered");
		
		Class[] argClasses = null;
		if (args != null) {
			argClasses = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				// every port method gets Datatype args only
				argClasses[i] = Datatype.class;
//				argClasses[i] = args[i].getClass();
			}
		}
		
		Datatype data = null;
		Object ret;
		try {
			Method portMethod = port.getClass().getMethod(methodName.substring(index+1), argClasses); 
			ret = portMethod.invoke(port, args);
			if (ret instanceof Datatype[]) data = new DatatypeArray((Datatype[]) ret);
			else if (ret instanceof Integer) data = new DatatypeInteger(((Integer) ret).intValue());
			else if (ret instanceof Boolean) data = new DatatypeBoolean(((Boolean) ret).booleanValue());
			else data = (Datatype) ret;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}
