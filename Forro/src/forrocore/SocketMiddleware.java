/* project    : forro2.0
 * file name  : SocketMiddleware.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br), Francisco de Carvalho Junior (heron@lia.ufc.br), Gisele Ara�jo (gisele@lia.ufc.br)
 * created    : 10/11/2006
 * copyright  : Federal University of Cear�, Brazil
 *
 */

package forrocore;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Map;

import ccacore.Services;

import session.datatype.Datatype;
import session.datatype.DatatypeFactory;
import session.workspace.CodedDatatype;
import exceptions.CCAException;



/**
 * This class implements the methods that must be provided to a Workspace Driver by a middleware using Java Sockets.
 * 
 * @author Gisele
 */
public class SocketMiddleware implements Middleware {
	
	/**
	 * Port that the Middleware listens the connections.
	 */
	private Integer port;

	/**
	 * Keep all registered links in this middleware.
	 * @uml.property  name="registeredLink"
	 */
	private Map<String, Link> registeredLink;
	/**
	 * Keep all connected locations in this middleware.
	 * @uml.property  name="connectionLocation"
	 */
	private Map<String, Socket> connectedLocation;
	/**
	 * Used to create datatype objects
	 * @uml.property  name="datatypeFactory"
	 * @uml.associationEnd  readOnly="true"
	 */
	private DatatypeFactory datatypeFactory;

	
	

	
	
	/**
	 * Constructor. Its implements a Server to listen the client requests.
	 * @param port
	 */
	public SocketMiddleware(Integer port) {
		super();
		this.port = port;
		
		try {
			ServerSocket serverSocket = null;
			//Boolean listening = true;
			datatypeFactory = DatatypeFactory.getInstance();
			serverSocket = new ServerSocket(port);
			System.out.println(" ##### Server Socket: " + serverSocket);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		while (listening) {
						
			new MultiServerThread(serverSocket.accept()).start();
		}	
		serverSocket.close();
		*/}
	

	
	final class MultiServerThread extends Thread {
		private Socket socket = null;
		
		public MultiServerThread(Socket socket) {
			super("MultiServerThread");
			this.socket = socket;
		}
		
		public void run() {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
			
	

	/**
	 * Register a specified link in the registry service of the middleware under a specified name. This link becomes
	 * available to be remotely retrieved with the <tt>retrieve</tt> method.
	 * 
	 * @param linkName the link name in URL format
	 * @param link the link object to be registered
	 * 
	 * @throws RemoteException TODO
	 * @throws MalformedURLException TODO
	 */
	public void register(String linkName, Link link) throws CCAException {
        Link old;
        if ((old = registeredLink.put(linkName, link)) == null) {
        	registeredLink.put(linkName, old);
        	throw new IllegalArgumentException();
        }
	}

	/**
	 * Retrieve a registered link under a specified name at a specified location.
	 * 
	 * @param linkName the name under which the link has been registered
	 * @param locationName the location from where the link is retrieved 
	 * 
	 * @return the link object registered under the specified name
	 * 
	 * @throws RemoteException TODO
	 * @throws MalformedURLException TODO
	 */
	public Link retrieve(String linkName, String locationName)
			throws CCAException {
		
		/****  locationName = location + "." +   port  ******/
		final int locIdx = locationName.indexOf('.');
		final String locatName = linkName.substring(0, locIdx);
		final String portS =  locationName.substring(locIdx, locationName.length());
		Integer port = java.lang.Integer.parseInt(portS);

		
		
		Socket locationSocket = connectedLocation.get(locationName);
			
		if (locationSocket == null) {
			try {
				locationSocket = new Socket(locatName, port);
				connectedLocation.put(locatName, locationSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		return new Link() {

			public void invokeAtProvidesPort(String portName, String methodName, Class[] parameterTypes, Datatype[] args, String invoker) throws CCAException {
				// TODO Auto-generated method stub
				
			}

			public void returnToUsesPort(Datatype data) throws CCAException {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	

	/* 
	 * Decode and externalize an object to be transmitted
	 * @param arg - object to be transmitted
	 */
	public Datatype externalize(Datatype arg) throws IOException {
		CodedDatatype ret = new CodedDatatype(arg);
		ret.code();
		return ret.getCoded();
	}

	/* 
	 * Decode and externalize objects to be transmitted
	 * @param arg - objects to be transmitted
	 */
	public Datatype[] externalize(Datatype[] args) throws IOException {
		CodedDatatype[] ret = args != null ? new CodedDatatype[args.length] : null;
		Datatype[] retDatatype = null;
		if (ret != null)
			for (int i = 0; i < args.length; i++) {
				ret[i] = new CodedDatatype(externalize(args[i]));
				retDatatype[i] = ret[i].getCoded(); 
			}
		
		return retDatatype;
	}

	
	/* 
	 * Uncode and internalize received object 
	 * @param arg - received object
	 */
	public Datatype internalize(Datatype arg) throws CCAException {
		CodedDatatype ret = new CodedDatatype(arg);
		ret.code();
		return ret.getCoded();
	}
 
	/* 
	 * Uncode and internalize received objects 
	 * @param args - received objects
	 */
	public Datatype[] internalize(Datatype[] args) throws CCAException {
		CodedDatatype argTmp;
		Datatype[] ret = args != null ? new Datatype[args.length] : null;
		if (ret != null)
			for (int i = 0; i < args.length; i++) {
				argTmp = new CodedDatatype(args[i]);
				ret[i] = argTmp.uncode(datatypeFactory);
			}
		
		return ret;
	}

	/**
	 * @return the registeredLink
	 */
	protected Map<String, Link> getRegisteredLink() {
		return registeredLink;
	}

	/**
	 * @param registeredLink the registeredLink to set
	 */
	protected void setRegisteredLink(Map<String, Link> registeredLink) {
		this.registeredLink = registeredLink;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	public void setServices(Services s) throws CCAException {
		// TODO Auto-generated method stub
		
	}

	
	
}
