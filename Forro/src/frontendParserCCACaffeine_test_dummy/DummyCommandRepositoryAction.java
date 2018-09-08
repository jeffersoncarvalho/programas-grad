package frontendParserCCACaffeine_test_dummy;

import java.rmi.RemoteException;

import exceptions.CCAException;
import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_interfaces.ICommandRepositoryAction;
import frontendParserCCACaffeine_command_util.Writer;

/**
 * Call the CCAfeine action for 'display' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandRepositoryAction implements ICommandRepositoryAction{

	 
	public void repositoryList(ForroDriverRMIInterface forroDriver){
		
		//testing
		Writer.print("Executing command repository list...");
	}
	 
	public void repositoryGet(String className, ForroDriverRMIInterface forroDriver){
		
		//testing
		Writer.print("Executing command repository get...");
	}
	
	public void repositoryGetPorts(String libPathName, ForroDriverRMIInterface forroDriver){
		
		//testing
		Writer.print("Executing command repository get ports...");
	}
	
	public void repositoryGetGlobal(String className, ForroDriverRMIInterface forroDriver){
		

		//testing
		Writer.print("Executing command repository get global...");
		
	}
	
	public void repositoryGetLazy(String className, ForroDriverRMIInterface forroDriver){

		//testing
		Writer.print("Executing command repository get lazy...");
	}
	
	public void repositoryGetLazyGlobal(String className, ForroDriverRMIInterface forroDriver){
	
		//testing
		Writer.print("Executing command repository get lazy global...");
	}
	
	public void repositoryInit(String locationName, String sessionName, ForroDriverRMIInterface forroDriver){

		try {
			//ForroDriver.getFrontEndForroAdapter().initialize(sessionName);
			String optionKey[] = new String[1];  
			String optionValue[] = new String[1];
			
//			ForroDriver.getForrodriver().execute(sessionName, locationName,"repositoryInitCommand", optionKey, optionValue);
			
			forroDriver.execute(sessionName, locationName,"repositoryInitCommand", optionKey, optionValue);
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CCAException e) {
			e.printStackTrace();
		}		Writer.print("Executing command repository init...");
	}
	
	public void repositoryInitLazy(ForroDriverRMIInterface forroDriver){
		
		//testing
		Writer.print("Executing command repository init lazy...");
	}
	
	public void repositoryInitLazyGlobal(ForroDriverRMIInterface forroDriver){
		

		//testing
		Writer.print("Executing command repository init lazy global...");
	}
	
	public void repositoryInitGlobal(String forroName, String sessionName, ForroDriverRMIInterface forroDriver){

		 
		String optionKey[] = new String[1];  
		String optionValue[] = new String[1];

			
		// Connect two ports
		 
		try {		 

			forroDriver.execute(sessionName, "localhost","repositoryInitCommand", optionKey, optionValue);
			
		} catch (RemoteException e) {
			e.printStackTrace();   
		} catch (CCAException e) {
			e.printStackTrace();   
		}
		Writer.print("Executing command repository init global...");
	}
}
