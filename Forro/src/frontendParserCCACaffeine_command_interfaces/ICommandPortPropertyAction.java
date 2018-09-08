package frontendParserCCACaffeine_command_interfaces;



/**
 * Call the CCAfeine action for 'instance' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandPortPropertyAction extends IAction{

	 
	
	public void portProperty(String componentInstanceName, String portName) ;

	public void portProperty(String componentInstanceName, String portName, String key) ;
	
	public void portProperty(String componentInstanceName, String portName, String key, String type, String value) ;
	 
}
