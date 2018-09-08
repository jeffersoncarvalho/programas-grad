package frontendParserCCACaffeine_command_interfaces;



/**
 * Call the CCAfeine action for 'display' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandPathAction extends IAction{

	 
	
	public void pathAppend(String directory) ;

	public void pathPrepend(String directory) ;
	
	public void pathInit() ;
	
	public void pathSet(String directory) ;
	
	public void path() ;
}
