package frontendParserCCACaffeine_command_interfaces;



/**
 * Call the CCAfeine action for 'instance' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandPropertyAction extends IAction{

	 
	
	public void property(String componentInstanceName) ;
	public void property(String componentInstanceName, String key) ;
	
	public void property(String componentInstanceName, String key, String value);
	 
	 
}
