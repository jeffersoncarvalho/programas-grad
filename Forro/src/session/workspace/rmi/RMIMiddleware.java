/*
 * Created on Aug 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package session.workspace.rmi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import forrocore.Link;
import forrocore.Middleware;
import session.OldSessionDriver;
import session.datatype.Datatype;
import session.datatype.DatatypeByteArray;
import session.datatype.DatatypeFactory;
import session.workspace.RemoteLink;
import session.workspace.VisibleByteArrayOutputStream;

/**
 * 
 * @author correa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class RMIMiddleware implements Middleware {
	
	/**
	 * @uml.property  name="datatypeFactory"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private DatatypeFactory datatypeFactory = DatatypeFactory.getInstance();
	
	/* (non-Javadoc)
	 * @see session.workspace.Middleware#register(java.lang.String, session.workspace.Link)
	 */
	public void register(String linkName, Link link) throws RemoteException, MalformedURLException {
		Link llink = null;
		try {
			llink = new RMILink(link);
			Naming.rebind("rmi://" + OldSessionDriver.hostName + "/" + RMILink.registeredName + "/" + linkName, (Remote) llink);
			System.out.println("Link " + linkName + " published");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see session.workspace.Middleware#retrieve(java.lang.String)
	 */
	public Link retrieve(String linkName, String locationName) throws RemoteException, MalformedURLException {
		Link rlink = null;
		try {
			rlink = (Link) Naming.lookup("rmi://" + locationName + "/" + RMILink.registeredName + "/" + linkName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("Link " + "rmi://" + locationName + "/" + RMILink.registeredName + "/" + linkName + " not registered");
			e.printStackTrace();
		}
		return rlink;
	}

	/* (non-Javadoc)
	 * @see session.workspace.Middleware#internalize(session.datatype.Datatype)
	 */
	public Datatype internalize(Datatype arg) throws IOException {
		Datatype ret = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(((DatatypeByteArray) arg).getValue()));
			ret = datatypeFactory.newInstance(in.readUTF());
			ret.readExternal(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public Datatype[] internalize(Datatype[] args) throws IOException {
		Datatype[] ret = args != null ? new Datatype[args.length] : null;
		if (ret != null)
			for (int i = 0; i < args.length; i++)
				ret[i] = internalize(args[i]);
		
		return ret;
	}
	
	public Datatype externalize(Datatype arg) throws IOException {
		if (arg == null) return null;
		
		final VisibleByteArrayOutputStream byteArray = new VisibleByteArrayOutputStream();
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(byteArray);
			out.writeUTF(arg.getClass().getName());
			arg.writeExternal(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new DatatypeByteArray(byteArray.getBuf());
	}

	public Datatype[] externalize(Datatype[] args) throws IOException {
		Datatype[] ret = args != null ? new Datatype[args.length] : null;
		if (ret != null)
			for (int i = 0; i < args.length; i++)
				ret[i] = externalize(args[i]);
		
		return ret;
	}


	/**
	 * @author  correa  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
	 */
	private class RMILink extends UnicastRemoteObject implements RemoteLink {

		/**
		 * An implementation of the Link interface
		 */
		private Link llink;
		/**
		 * This name is used by the workspace driver to lookup remote links
		 */
		private static final String registeredName = "RMILink";
		
		/**
		 * @throws java.rmi.RemoteException
		 */
		RMILink(Link l) throws RemoteException {
			super();
			llink = l;
		}

		/* (non-Javadoc)
		 * @see session.workspace.Link#sendInvocation(int, java.lang.String, session.workspace.CodedDatatype[], java.lang.String, byte[])
		 */
		public void invokeAtProvidesPort(int invocationHashCode, String methodName,
				Datatype[] args, String sessionName, byte[] sessionSig)
				throws RemoteException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see session.workspace.Link#sendInvocation(int, java.lang.String, session.datatype.Datatype[])
		 */
		public void invokeAtProvidesPort(String portName, String methodName, Class[] parameterTypes, Datatype[] args, String invoker) throws RemoteException {
			llink.invokeAtProvidesPort(null, methodName, null, args, null);
		}

		/* (non-Javadoc)
		 * @see session.workspace.Link#sendResult(int, session.workspace.CodedDatatype, java.lang.String, byte[])
		 */
		public void returnToUsesPort(int invocationHashCode, Datatype data,
				String sessionName, byte[] sessionSig) throws RemoteException {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see session.workspace.Link#sendResult(int, session.datatype.Datatype)
		 */
		public void returnToUsesPort(Datatype data)	throws RemoteException {
			llink.returnToUsesPort(data);
		}


	}
}
