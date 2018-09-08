package frontendParserCCACaffeine_command_interfaces;

import forrocore.ForroDriverRMIInterface;



/**
 * Call the CCAfeine action for 'display' command and respective parameters
 * @author Jefferson
 *
 */
public interface ICommandRepositoryAction extends IAction{

	 
	public void repositoryList(ForroDriverRMIInterface forroDriver);
	 
	public void repositoryGet(String className, ForroDriverRMIInterface forroDriver);
	
	public void repositoryGetPorts(String libPathName, ForroDriverRMIInterface forroDriver);
	
	public void repositoryGetGlobal(String className, ForroDriverRMIInterface forroDriver);
	
	public void repositoryGetLazy(String className, ForroDriverRMIInterface forroDriver);
	
	public void repositoryGetLazyGlobal(String className, ForroDriverRMIInterface forroDriver);
	
	public void repositoryInit(String locationName, String sessionName, ForroDriverRMIInterface forroDriver);
	
	public void repositoryInitLazy(ForroDriverRMIInterface forroDriver);
	
	public void repositoryInitLazyGlobal(ForroDriverRMIInterface forroDriver);
	
	public void repositoryInitGlobal(String forroName, String sessionName, ForroDriverRMIInterface forroDriver);
}
