package frontendParserCCACaffeine_exceptions;

public class FactoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7014359483525240856L;

	/**
	 * 
	 */
	
	public FactoryException(String msg) {
		super("Cannot create object of type \"" + msg+"\".\nCheck your \"ICCACommandActionFactory\" implementation.");
	}
}
