/* file name  : BlockingWorkspaceDriver.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Apr 3, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.workspace.globus;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import exceptions.CCAException;
import forrocore.Link;
import session.datatype.Datatype;
import session.datatype.DatatypeFactory;
import session.datatype.DatatypeObject;
import session.workspace.CodedDatatype;
import session.workspace.LocationDriver;
import session.workspace.RemoteLink;
import forrocore.AbstractWorkspaceDriver;

/**
 * A Workspace Driver that implements blocking method invocations as the model of computation. In a blocking method
 * invocation, the component invoking the method remains blocked until the remote method returns. Upon completion,
 * the result of the blocking method invocation is available to the invoker.
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public class BlockingWorkspaceDriver extends  AbstractWorkspaceDriver  {
	/**
	 * Name of this workspace
	 * @uml.property  name="workName"
	 */
	private String workName;
	/**
	 * Driver of this location.
	 * @uml.property  name="driver"
	 * @uml.associationEnd  readOnly="true"
	 */
	private LocationDriver driver;
	/**
	 * Maps names to links
	 * @uml.property  name="linkMap"
	 */
	private Map linkMap;
	/**
	 * Maps invocation hash codes to byte arrays
	 * @uml.property  name="invocationMap"
	 */
	private Map invocationMap;
	/**
	 * Used to create datatype objects
	 * @uml.property  name="datatypeFactory"
	 * @uml.associationEnd  readOnly="true"
	 */
	private DatatypeFactory datatypeFactory;
	
	/**
	 * @param wname TODO
	 * @param driver TODO
	 */
	public BlockingWorkspaceDriver(String wname) {
		super(wname);
		this.workName = wname;
		linkMap = new HashMap();
		invocationMap = new HashMap();
		datatypeFactory = DatatypeFactory.getInstance();
	}	

//	/**
//	 * @param wname TODO
//	 * @param driver TODO
//	 */
//	public BlockingWorkspaceDriver(String wname, LocationDriver driver) {
//		super(wname);
//		this.workName = wname;
//		this.driver = driver;
//		linkMap = new HashMap();
//		invocationMap = new HashMap();
//		datatypeFactory = DatatypeFactory.getInstance();
//	}
	
	public boolean registerLink(String linkName, String locationName) {
		if (isRegistered(linkName)) return false;
		
		Link link = null;
		boolean registered = true;
		do {
		} while (!registered);
		
		System.out.println("Link " + linkName + " registered to workspace " + workName);
		linkMap.put(linkName, link);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see session.workspace.WorkspaceDriver#isRegistered(java.lang.String)
	 */
	public boolean isRegistered(String linkName) {
		return linkMap.containsKey(linkName);
	}

	/* (non-Javadoc)
	 * @see session.workspace.WorkspaceDriver#requireUses(java.lang.String)
	 */
	public boolean requireUses(String linkName) {
		return true;
	}
		
/*	public void invoke(final String methodName, final Datatype[] args, String linkName) { */
 /*  public Datatype invoke(final String methodName, final Datatype[] args, String linkName) { 
		final Link link = (Link) linkMap.get(linkName);
		if (link == null) return null;
		
		Datatype result = new DatatypeObject(datatypeFactory);
		Datatype[] args1;
		try {
			Integer hashCode = new Integer(result.hashCode());
			invocationMap.put(hashCode, result);

			
			//////////// Modified by Gisele Azevedo - 21/06/2006
			link.invokeAtProvidesPort(null, methodName, null, externalize(args), null);

			
			
			
			synchronized (result) { result.wait(); }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	*/
	
	private CodedDatatype[] externalize(Datatype[] args) throws IOException {
		CodedDatatype[] ret = args != null ? new CodedDatatype[args.length] : null;
		if (ret != null)
			for (int i = 0; i < args.length; i++) {
				ret[i] = externalize(args[i]);
			}
		
		return ret;
	}
	
	private CodedDatatype externalize(Datatype arg) throws IOException {
		CodedDatatype ret = new CodedDatatype(arg);
		ret.code();
		return ret;
	}
	
	private Datatype[] internalize(CodedDatatype[] args) throws IOException {
		Datatype[] ret = args != null ? new Datatype[args.length] : null;
		if (ret != null)
			for (int i = 0; i < args.length; i++) {
				ret[i] = args[i].uncode(datatypeFactory);
			}
		
		return ret;
	}
	
	/**
	 * @author  correa  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
	 */
	private final class AsynchronousGlobusLink extends UnicastRemoteObject implements RemoteLink {
		
		private static final String registeredName = "AsynchronousRMILink";
		
		/**
		 * Link's name
		 */
		private String name;

		protected Object Link;
		
		/**
		 * @param name
		 * @throws RemoteException
		 */
		AsynchronousGlobusLink(String name) throws RemoteException {
			super();
			this.name = name;
		}
		
		/* (non-Javadoc)
		 * @see session.workspace.Link#sendInvocation(java.lang.Integer, java.lang.String, session.workspace.ByteArrayDatatype[])
		 */
	/*
		public void sendInvocation(final int invocationHashCode, final String methodName, final CodedDatatype[] args) {
			Thread invocationThread = new Thread() {
				public void run() {
					try {
						Link link = (Link) linkMap.get(name);
						Datatype invocationRet = driver.invokeAtModel(methodName, internalize(args));
						link.returnToUsesPort(invocationRet == null ? null : externalize(invocationRet));
						
						
						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
			};
			invocationThread.start();
		}
*/		
		/* (non-Javadoc)
		 * @see session.workspace.Link#sendResult(java.lang.Integer, java.lang.String, session.workspace.ByteArrayDatatype)
		 */
		public void sendResult(int invocationHashCode, CodedDatatype result) {
			Datatype data;
			data = (Datatype) invocationMap.get(new Integer(invocationHashCode));
//				Datatype contents = (Datatype) Class.forName(result.getClassName()).newInstance();
			Datatype contents = result.uncode(datatypeFactory);
			System.out.println("Chegando um resultado do tipo " + contents.getClass().getName());
			synchronized (data) {
				data.setContents(contents);
				data.notify();
			}
		}
		
		/* (non-Javadoc)
		 * @see session.workspace.Link#secureInvocation(java.lang.Integer, java.lang.String, java.lang.String, session.workspace.ByteArrayDatatype[], byte[])
		 */
		public void sendInvocation(int invocationHashCode,
				String methodName, CodedDatatype[] args,
				String sessionName, byte[] sessionSig) {
			// TODO Auto-generated method stub
		}
		
		/* (non-Javadoc)
		 * @see session.workspace.Link#insecureInvocation(java.lang.Integer, java.lang.String, session.workspace.ByteArrayDatatype[])
		 */
		public void sendResult(int invocationHashCode,
				CodedDatatype result, String sessionName, byte[] sessionSig) {
			// TODO Auto-generated method stub
		}
		
		/* (non-Javadoc)
		 * @see session.workspace.Link#getName()
		 */
		/**
		 * @return  Returns the name.
		 * @uml.property  name="name"
		 */
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		public void invokeAtProvidesPort(String portName, String methodName, Class[] parameterTypes, Datatype[] args, String invoker) throws CCAException {
			// TODO Auto-generated method stub
			
		}

		public void returnToUsesPort(Datatype data) throws CCAException {
			// TODO Auto-generated method stub
			
		}
	}
}
