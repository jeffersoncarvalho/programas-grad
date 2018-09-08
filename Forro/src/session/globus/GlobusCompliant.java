/* file name  : GlobusCompliant.java
 * authors    : Ricardo Corrêa (correa@lia.ufc.br)
 * created    : Apr 9, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.globus;

/**
 * Indicates that a session handler implementation is Globus compliant.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public interface GlobusCompliant {
	/**
	 * A Globus Compliant remote session handler is registered under this name.
	 */
	public static final String registeredName = "GlobusCompliantSessionHandler";

}
