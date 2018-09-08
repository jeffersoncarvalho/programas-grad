/* file name  : RMICompliant.java
 * authors    : Ricardo Corrêa (correa@lia.ufc.br)
 * created    : Apr 9, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.rmi;

/**
 * Indicates that a session handler implementation is Java's RMI compliant.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public interface RMICompliant {
	/**
	 * A Java's RMI Compliant remote session handler is registered under this name.
	 */
	public static final String registeredName = "RMICompliantSessionsHandler";
}
