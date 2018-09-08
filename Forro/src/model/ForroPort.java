package model;

import ccacore.Port;

/*
 * Interface de todas as portas do ForroDriver e estas informa√ß√µes dever√£o ficar no model driver 
 * seguindo as especificaÁ„o do CCA 
 */

public interface ForroPort extends Port {
	/**
	 * Boolean constants. 
	 */
//	public static final DatatypeBoolean datatypeTrue = new DatatypeBoolean(true);
//	public static final DatatypeBoolean datatypeFalse = new DatatypeBoolean(false);
	/**
	 * Boolean constants.
	 * @uml.property  name="name"
	 */
	String getName();
	/**
	 * Return <tt>true</tt> if this object extends <i>UsesPort</i>, which means that it contains remotely callable
	 * methods. If it returns <tt>false</tt>, then this object extends <i>ProvidesPort</i>, and only local method invocations
	 * are allowed.
	 * 
	 * @return <tt>true</tt> if this object is remote.
	 */
	boolean isUses();
	/**
	 * Return <tt>true</tt> if this object extends <i>ProvidesPort</i>, which means that only local method invocations
	 * are allowed. If it returns <tt>false</tt>, then this object extends <i>UsesPort</i>, and it contains remotely callable
	 * methods.
	 * 
	 * @return <tt>true</tt> if this object is local.
	 */
	boolean isProvides();

}
