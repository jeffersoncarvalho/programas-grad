/*
 * Created on Aug 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package forrocore;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import ccacore.Component;

import exceptions.CCAException;


import session.datatype.Datatype;

/**
 * This interface describes the methods that must be provided to a Workspace Driver by a middleware.
 * 
 * @author correa
 */
public interface Middleware extends Component {
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
	void register(String linkName, Link link) throws CCAException;
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
	Link retrieve(String linkName, String locationName) throws CCAException;
	/**
	 * Uncode a coded datatype object. The specified datatype must have been coded with the 
	 * <tt>externalize</tt> method of this middleware. This method should use the 
	 * <tt>readExternal</tt> method of the Datatype interface.
	 * 
	 * @param arg the coded datatype to be uncoded
	 * 
	 * @return an uncoded datatype object.
	 * 
	 * @throws IOException if the <tt>readExternal</tt> method throws an exception.
	 */
	Datatype internalize(Datatype arg) throws CCAException;
	/**
	 * Uncode an array of specified datatypes. It invokes the <tt>internalize(Datatype)</tt> method for each
	 * Datatype object of the array.
	 * 
	 * @param args the array of Datatype objects to be uncoded
	 * 
	 * @return the array of datatype objects, with its members uncoded
	 * 
	 * @throws IOException if <tt>internalize(Datatype)</tt> throws an exception
	 */
	Datatype[] internalize(Datatype[] args) throws CCAException;
	/**
	 * Code an uncoded datatype object. The specified datatype is put in the appropriate format
	 * to be transmitted through this middleware. It can be uncoded with the 
	 * <tt>internalize</tt> method of this middleware. This method should use the 
	 * <tt>writeExternal</tt> method of the Datatype interface.
	 * 
	 * @param arg the uncoded datatype to be coded
	 * 
	 * @return a new coded datatype object
	 * 
	 * @throws IOException if the <tt>writeExternal</tt> method throws an exception.
	 */
	Datatype externalize(Datatype arg) throws IOException;
	/**
	 * Code an array of specified datatypes. It invokes the <tt>externalize(Datatype)</tt> method for each
	 * Datatype object of the array.
	 * 
	 * @param args the array of Datatype objects to be coded
	 * 
	 * @return the array of coded objects
	 * 
	 * @throws IOException if <tt>externalize(Datatype)</tt> throws an exception
	 */
	Datatype[] externalize(Datatype[] args) throws IOException;
}
