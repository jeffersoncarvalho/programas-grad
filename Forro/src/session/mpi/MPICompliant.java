/* file name  : MPICompliant.java
 * authors    : Ricardo Corrêa (correa@lia.ufc.br)
 * created    : Apr 9, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package session.mpi;

/**
 * Indicates that a session handler implementation is MPI compliant.
 * 
 * @author Ricardo Corrêa (correa@lia.ufc.br)
 */
public interface MPICompliant {
	/**
	 * An MPI Compliant remote session handler is registered under this name.
	 */
	public static final String registeredName = "MPICompliantSessionHandler";

}
