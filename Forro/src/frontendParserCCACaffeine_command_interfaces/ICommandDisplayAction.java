package frontendParserCCACaffeine_command_interfaces;



/**
 * Call the CCAfeine action for 'display' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandDisplayAction extends IAction{

	 
	
	public void displayPallet() ;

	public void displayArena();
	
	public void displayComponent(String componentIntanceName) ;
	
	public void displayChain( ) ;
}
