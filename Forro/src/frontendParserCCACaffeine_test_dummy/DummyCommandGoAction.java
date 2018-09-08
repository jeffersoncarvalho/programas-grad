package frontendParserCCACaffeine_test_dummy;

import java.rmi.RemoteException;

import exceptions.CCAException;
import forrocore.ForroDriver;
import forrocore.ForroDriverRMIInterface;
import forrocore.ForroSessionDriver;
import frontendParserCCACaffeine_command_interfaces.ICommandGoAction;
import frontendParserCCACaffeine_command_util.Writer;


/**
 * Call the CCAfeine action for 'go, run' command and respective parameters
 * @author Jefferson
 *
 */
public class DummyCommandGoAction implements ICommandGoAction{

	 public void go(String instance, String port, ForroDriverRMIInterface forroDriver){
		 String optionKey[] = new String[2];  
		 String optionValue[] = new String[2];
		 // Command Go
		 optionKey[0] = ForroDriver.componentNameLabel;
		 optionKey[1] = ForroDriver.portNameLabel;
			
		 optionValue[0] = instance;                                	        							
		 optionValue[1] = port;                                	        							

			try {		 
				
				String sessionName = ((ForroSessionDriver) ForroDriver.getCurrentSessionDriver()).getSessionName();
				if (sessionName.equals(""))
				{
					throw new CCAException("Current session not defined.");
				}				
				
				forroDriver.execute(sessionName, "localhost","goCommand", optionKey, optionValue);
			
			} catch (RemoteException e) {
				e.printStackTrace();   
			} catch (CCAException e) {
				e.printStackTrace();   
			}		
		
		 Writer.print("go to " + instance);
	 }
}
