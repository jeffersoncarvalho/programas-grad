/* file name  : SessionHandlerException.java
 * authors    : Ricardo Corr�a (correa@lia.ufc.br)
 * created    : Feb 18, 2005
 * copyright  : 
 *
 * modifications:
 *
 */
package exceptions;

/**
 * TODO Describe this type.
 * 
 * @author Ricardo Corr�a (correa@lia.ufc.br)
 */
public class SessionHandlerException extends Exception {

	/**
	 * 
	 */
	public SessionHandlerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public SessionHandlerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public SessionHandlerException(Throwable cause) {
		super(cause);
		cause.printStackTrace();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SessionHandlerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
