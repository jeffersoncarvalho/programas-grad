package exceptions;

public abstract class CCAExceptionType {

	/* Someone caught a non-CCAException that was declared
	 *  at an interface that claims it throws ONLY CCAException. 
	 */
	public static final int Unexpected = -1;

	/* A CCAException that is carrying a non-standard message. */
	public static final int Nonstandard = 1;

	/* Action attempted on a port name that is neither registered
	 * nor added. 
	 */
	public static final int PortNotDefined = 2;

	/* Adding/registering an already added/registered Port was
	 *  attempted. 
	 */
	public static final int PortAlreadyDefined = 3;

	/* Attempt to getPort, getPortNonblocking, or releasePort
	 * with a port named that is not connected. 
	 */
	public static final int PortNotConnected = 4;

	/* Redundant attempt to release a Port. */
	public static final int PortNotInUse = 5;

	/* Attempt to unregister a Port that is still being used. */
	public static final int UsesPortNotReleased = 6;

	/* Port name given to createPortInfo is bogus. */
	public static final int BadPortName = 7;

	/* Port class/type given to createPortInfo is bogus. */
	public static final int BadPortType = 8;

	/* 
	 * Port properties given to createPortInfo is bogus.
	 * Note: null is NOT a bogus input, but a fairly common one. 
	 */
	public static final int BadProperties = 9;

	/* PortInfo given in port add/register call is bogus or null. */
	public static final int BadPortInfo = 10;

	/* Services implementation failed to allocate memory. */
	public static final int OutOfMemory = 11;

	/* Port (or function within it) died on a remote error. */
	public static final int NetworkError = 12;

	/* Port already in use. */
	public static final int PortAlreadyInUse = 13;

}
