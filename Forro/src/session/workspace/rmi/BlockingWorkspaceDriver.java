                                      /* file name  : BlockingWorkspaceDriver.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Apr 3, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.workspace.rmi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import forrocore.Link;
import session.OldSessionDriver;
import session.datatype.Datatype;
import session.datatype.DatatypeFactory;
import session.datatype.DatatypeObject;
import session.workspace.CodedDatatype;
import session.workspace.LocationDriver;
import session.workspace.RemoteLink;
import session.workspace.AbstractWorkspaceDriver;

/**
 * A Workspace Driver that implements blocking method invocations as the model of computation. In a blocking method
 * invocation, the component invoking the method remains blocked until the remote method returns. Upon completion,
 * the result of the blocking method invocation is available to the invoker.
 * 
 * @author Rica| rdo Corr�a (correa@lia.ufc.br)
 */
public class BlockingWorkspaceDriver extends /*implements */ AbstractWorkspaceDriver  {
	/**
	 * Name of this workspace
	 * @uml.property  name="workName"
	 */
	private String workName;
	/**
	 * Driver of this location.
	 * @uml.property  name="ldriver"
	 * @uml.associationEnd  readOnly="true"
	 */
	private LocationDriver ldriver;
	/**
	 * Maps names to links. Each entry is a 2-link vector, containing the local endpoint and the remote endpoint, in this order
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
	public BlockingWorkspaceDriver(String wname, LocationDriver driver) {
		super();
		this.workName = wname;
		this.ldriver = driver;
		linkMap = new HashMap();
		invocationMap = new HashMap();
		datatypeFactory = DatatypeFactory.getInstance();
	}
	
	public boolean registerLink(String linkName, String locationName) {
		if (isRegistered(linkName)) return false;
		
		Link llink = null;
		try {
			llink = new AsynchronousRMILink(linkName);
			Naming.rebind("rmi://" + OldSessionDriver.hostName + "/" + AsynchronousRMILink.registeredName + "/" + linkName, (Remote) llink);
			System.out.println("Link " + linkName + " published");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Link rlink = null;
		boolean registered = true;
		do {
			try {
				rlink = (RemoteLink) Naming.lookup("rmi://" + locationName + "/" + AsynchronousRMILink.registeredName + "/" + linkName);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
//				registered = false;
				System.out.println("Link " + "rmi://" + locationName + "/" + AsynchronousRMILink.registeredName + "/" + linkName + " not registered");
				e.printStackTrace();
			}
		} while (!registered);
		
		System.out.println("Link " + linkName + " registered to workspace " + workName);
		linkMap.put(linkName, new Link[] { llink, rlink });
		
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
		
	public Datatype invoke(final String methodName, final Datatype[] args, String linkName) {
		final Link link = ((Link[]) linkMap.get(linkName))[1];
		if (link == null) return null;
		
		Datatype result = new DatatypeObject(datatypeFactory);
		try {
			Integer hashCode = new Integer(result.hashCode());
			invocationMap.put(hashCode, result);
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
	
	private CodedDatatype[] externalize(Datatype[] args) throws IOException {
		CodedDatatype[] ret = args != null ? new CodedDatatype[args.length] : null;
		if (ret != null)
			for (int i = 0; i < args.length; i++) {
				ret[i] = externalize(args[i]);
			}
		
		return ret;
	}
	
	private CodedDatatype externalize(Datatype arg) throws IOException {
		CodedDatatype ret = arg == null ? null : new CodedDatatype(arg);
		if (ret != null) {
			System.out.println("arg do tipo "+arg.getClass().getName());
			System.out.println(arg);
			ret.code();
		}
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
	private final class AsynchronousRMILink extends UnicastRemoteObject implements RemoteLink {
		
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
		AsynchronousRMILink(String name) throws RemoteException {
			super();
			this.name = name;
		}
		
		/* (non-Javadoc)
		 * @see session.workspace.Link#sendInvocation(java.lang.Integer, java.lang.String, session.workspace.ByteArrayDatatype[])
		 */
		public void sendInvocation(final int invocationHashCode, final String methodName, final CodedDatatype[] args) {
			Thread invocationThread = new Thread() {
				public void run() {
					try {
						Link link = ((Link[]) linkMap.get(name))[1];
						Datatype invocationRet = ldriver.invokeAtModel(methodName, internalize(args));
						link.returnToUsesPort(externalize(invocationRet));
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
		
		/* (non-Javadoc)
		 * @see session.workspace.Link#sendResult(java.lang.Integer, java.lang.String, session.workspace.ByteArrayDatatype)
		 */
		public void sendResult(int invocationHashCode, CodedDatatype result) {
			Datatype data;
			data = (Datatype) invocationMap.get(new Integer(invocationHashCode));
//				Datatype contents = (Datatype) Class.forName(result.getClassName()).newInstance();
			Datatype contents = result == null ? null : result.uncode(datatypeFactory);
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

		/* (non-Javadoc)
		 * @see session.workspace.Link#requireUses()
		 */
		public boolean requireUses() {
			return true;
		}
	}
}
