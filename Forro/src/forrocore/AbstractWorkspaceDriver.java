/* file name  : WokspaceDriver.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Apr 19, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package forrocore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import session.datatype.Datatype;
import session.datatype.DatatypeFactory;
import ccacore.Port;
import ccacore.Services;
import exceptions.CCAException;

/**
 * A workspace is a set of locations that do not share any memory space, a set of links connecting pairs of
 * locations and a model of computation that drives its behavior.
 * The model of computation specifies the timing characteristics of the computations involving the locations
 * and the links of the workspace. Synchronization, global (virtual) time and global states are aspects
 * related to the model of computation.
 *  
 * <p>Each location of a workspace has its own Workspace Driver. These drivers distributedly implement the model
 * of computation that governs the workspace. The methods of a Workspace Driver allow it to have control over
 * the interactions between locations during a computation. First, every link, to be created, must be registered
 * at the drivers of the locations that define the link. Second, when a method is invoked in a uses port by
 * a component model, this invocation must be re-directed to the workspace driver of the location of the uses
 * port that originated the invocation, which, in its turn, send it to the appropriate link.
 * 
 * <p>A class that implements this interface must provide a constructor with two parameters: a <tt>String</tt>,
 * for the name of the workspace, and a <tt>LocationDriver</tt>. This workspace is invoked by the
 * <tt>addWorksapce</tt> method of the <tt>LocationDriver</tt>, by reflection.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br), Gisele Azevedo (gisele@lia.ufc.br)
 */
public abstract class AbstractWorkspaceDriver implements WorkspaceDriver {
	
	private Services services;

	/**
	 * The name associated with this session.
	 */
	private final String workspaceName;
	/**
	 * Buffers the method invocations for dispatching
	 */
	private final Queue<Invocation> pendingInvocation = new LinkedList<Invocation>();
	/**
	 * Dispatches the method invocations from queue
	 */
	private Thread dispatcher = null;
	/**
	 * Maps the locations of this workspace.
	 */
	private final Map<String, Middleware> locationMap = new HashMap<String, Middleware>();

	
	/**
	 * Keep all registered links in this middleware.
	 * @uml.property  name="registeredLink"
	 */
	private final Map<String, Link> linkMap = new HashMap<String, Link>();;
	



	/**
	 * Constructor. 
	 *  @param workspaceName
	 */
	public AbstractWorkspaceDriver(String workspaceName)
	{
		super();
		this.workspaceName = workspaceName;
	}
	
	
	
	
	/**
	 * The middleware used by this workspace
	 */
	private Middleware middleware  = null; 
	private Object mutex = new Object();
	
	
	/**
	 * Maps invocation hash codes to byte arrays
	 */
	public Map invocationMap;
	
	/**
	 * Used to create Datatype objects
	 */
	private DatatypeFactory datatypeFactory = DatatypeFactory.getInstance();

	
	/**
	 * Creates a new workspace driver with the middleware.
	 * @param middleware the associated middleware
	 */
	public AbstractWorkspaceDriver(Middleware middleware) {
		this.middleware = middleware;
		this.workspaceName = "workspaceLocal";
		
	}
	/**
	 * Creates a new workspace driver with the specified name, location driver and middleware.
	 * The maps are initialized.
	 * @param middleware the associated middleware
	 * @param workspaceName TODO
	 */
	public AbstractWorkspaceDriver(Middleware middleware, String workspaceName) {
		this.middleware = middleware;
		this.workspaceName = workspaceName;
		
		/*
		dispatcher = new Thread() {
			public void run() {
				Invocation invocation = null;
				try {
					boolean notDestroyed;
					do {
						synchronized (pendingInvocation) {
							if (!pendingInvocation.isEmpty())
								invocation = (Invocation) pendingInvocation.poll();
						}
						
						if (invocation != null) {
							new Thread(invocation).start();
							invocation = null;								
						}
						else synchronized (pendingInvocation) {
							pendingInvocation.wait();
						}

						synchronized (mutex) {
							notDestroyed = mutex != null;
						}
					}
					while (notDestroyed);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		dispatcher.start();*/
	}

	
	/**
	 * Get the map of location.
	 * @param 
	 */
	public Map getLocationMap() {
		return locationMap;
	}

	

	
	
	
	/* (non-Javadoc)
	 * @see ccacore.Component#setServices(ccacore.Services)
	 */
	public void setServices(Services s) {
		this.services = s;
	}
	
	/**
	 * This method should be overriden when a special behavior of the workspace is specified.
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret = null;
		try {
			ret = method.invoke(proxy, args);
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
			System.out.println("Erro AQUI!!!!!");
			//System.out.println("Causa: " +  e.getCause());
			e.printStackTrace();
		}
		
		return ret;
	}

	/**
	 * An implementation of the Link interface that uses message passing. The method invocations in this are non-blocking.
	 * @author  correa
	 */
	private abstract class AbstractLink implements Link {
		
		/**
		 * Opposite endpoint
		 */
		private Link rlink;
		
		/**
		 * Creates a link with the specified name. This name is used to register the new link in the workspace
		 * driver. A dispatcher's thread is started to handle the method invocation queue associated with
		 * this link.
		 * 
		 * @throws RemoteException
		 */
		AbstractLink() throws CCAException {
			super();
		}
		
		/* (non-Javadoc)
		 * 
		 * This finalizes the dispatcher thread.
		 * 
		 * @see java.lang.Object#finalize()
		 */
		protected void finalize() throws Throwable {
			dispatcher.interrupt();
		}

		/* (non-Javadoc)
		 * @see session.workspace.Link#connect(session.workspace.Link)
		 */
		private void connect(Link rlink) {
			this.rlink = rlink;
		}

		/* (non-Javadoc)
		 * @see session.workspace.Link#opposite()
		 */
		private Link opposite() {
			return rlink;
		}
	}

	/**
	 * An implementation of the Link interface that uses message passing. The method invocations in this are non-blocking.
	 * @author  correa
	 */
	private final class AsynchronousLink extends AbstractLink {
		
		/**
		 * Creates a link with the specified name. This name is used to register the new link in the workspace
		 * driver. A dispatcher's thread is started to handle the method invocation queue associated with
		 * this link.
		 * 
		 * @throws RemoteException
		 */
		AsynchronousLink() throws CCAException {
			super();
		}
		
		/* (non-Javadoc)
		 * @see session.workspace.Link#sendInvocation(java.lang.Integer, java.lang.String, session.workspace.ByteArrayDatatype[])
		 */
		public void invokeAtProvidesPort(String portName, final String methodName, Class[] parameterTypes, final Datatype[] args, String invoker) {
			synchronized (pendingInvocation) {
				pendingInvocation.offer(new Invocation(portName, methodName, parameterTypes, args, invoker));
				pendingInvocation.notify();
			}
		}

		public void returnToUsesPort(Datatype data) throws CCAException {
			// TODO Auto-generated method stub
			
		}
		
		/**
		 * Sends a Datatype parameter as a result of an invocation received by the <tt>sendInvocation</tt> method. 
		 * Since it throws the <tt>RemoteException</tt>, it can be remotely invoked with Java's RMI protocol.
		 * 
		 * <p>This method should be used to implement non-blocking port method invocations.
		 * @param data the Datatype object corresponding to the result of the specified method
		 */
	//	private void returnToUsesPort(Datatype data) throws CCAException{}

		
	}

	/**
	 * Stores a method invocation in the format required by the queue.
	 * @author  correa
	 */
	private class Invocation implements Runnable {
		/**
		 * The destination port of this invocation
		 */
		private final String portName;
		/**
		 * The method of this invocation
		 */
		private final String methodName;
		/**
		 * The parameter types to identify the method
		 */
		private final Class[] parameterTypes;
		/**
		 * The arguments passed in the invocation
		 */
		private final Datatype[] args;
		/**
		 * Link to send the result of this invocation
		 */
		private final String returnUsesPortName;
		
		/**
		 * @param portName
		 * @param methodName
		 * @param parameterTypes TODO
		 * @param args
		 * @param returnUsesPortName TODO
		 */
		private Invocation(String portName, String methodName, Class[] parameterTypes, Datatype[] args, String returnUsesPortName) {
			super();
			this.portName = portName;
			this.methodName = methodName;
			this.parameterTypes = parameterTypes;
			this.args = args;
			this.returnUsesPortName = returnUsesPortName;
		}

		/**
		 * @return the methodName
		 * @throws CCAException 
		 * @throws NoSuchMethodException 
		 * @throws  
		 */
		private Method getMethod() throws CCAException {
			try {
				return getPort().getClass().getMethod(methodName, parameterTypes);
			} catch (SecurityException e) {
				throw new CCAException();
			} catch (NoSuchMethodException e) {
				throw new CCAException();
			}
		}

		/**
		 * @return the portName
		 * @throws CCAException if services throws an exception
		 */
		private Port getPort() throws CCAException {
			return services.getPort(portName);
		}

		/**
		 * @return the args
		 * @throws CCAException 
		 */
		private Object[] getArgs() throws CCAException {
			return middleware.internalize(args);
		}

		/**
		 * @return the returnLinkName
		 * @throws CCAException if services throws an exception
		 */
		private UsesPort getReturnUsesPort() throws CCAException {
			return (UsesPort) services.getPort(returnUsesPortName);
		}

		public void run() {
			Datatype invocationRet;
			try {
				invocationRet = (Datatype) invoke(getPort(), getMethod(), getArgs());
				if (!getMethod().getReturnType().equals(void.class))
					getReturnUsesPort().setResult(middleware.externalize(invocationRet));
			} catch (CCAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}								
	}

	/**
	 * Adds a asynchronousLink in a workspace
	 * @param link the link name to set
	 */
	public void addAsynchronousLink(String link) throws CCAException  {
		
		linkMap.put(link, new AsynchronousLink());
	}


	/**
	 * Adds a location in a map of a workspace
	 * @param locationName location name
	 * @param locationDriver location driver
	 */
	public void addLocation(String locationName, Middleware locationDriver) throws CCAException  {
		
		locationMap.put(locationName, locationDriver);
	}


	/**
	 * @return the workspaceName
	 */
	public String getWorkspaceName() {
		return workspaceName;
	}


}