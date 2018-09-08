package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.CommandsContants;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandInstantiateAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandInstantiateParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandInstantiateAction cmdInstAction;
	
	public CommandInstantiateParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException{
	
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdInstAction =  (ICommandInstantiateAction)this.getFactory().create(FactoryConstants.INSTANTIATE);
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException{
		
		if (tokens.size()==0)
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
		
		String firstToken = tokens.remove(0);
		
		if(firstToken.equals(CommandsContants.COMPONENT)){
			if((tokens.size()<2)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>2)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			String className = tokens.remove(0);
			String locationName = tokens.remove(0);
		
			this.cmdInstAction.instantiate(className, locationName, forroDriver);
			
		}else if(firstToken.equals(CommandsContants.LOCATION)){
			if((tokens.size()<3)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>3)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			String locationName = tokens.remove(0);
			String middlewareName = tokens.remove(0);
			String portName = tokens.remove(0);
		
			this.cmdInstAction.createLocation(locationName, middlewareName, portName, forroDriver);
			
		}else if(firstToken.equals(CommandsContants.WORKSPACE)){
			if((tokens.size()<3)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>3)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			String workspaceClassType = tokens.remove(0);
			String workspaceName = tokens.remove(0);
			String locationName = tokens.remove(0);
		
			this.cmdInstAction.createWorkspace(workspaceClassType, workspaceName, locationName, forroDriver);
			
		}else if(firstToken.equals(CommandsContants.LINK)){
			if((tokens.size()<3)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>3)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			String linkName = tokens.remove(0);
			String workspaceName = tokens.remove(0);
			String locationName = tokens.remove(0);
		
			this.cmdInstAction.createLink(linkName, workspaceName, locationName, forroDriver);
			
		} else if(firstToken.equals(CommandsContants.RELATIONWORKSPACELOCATION)){
			if((tokens.size()<3)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>3)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}

			String workspaceName = tokens.remove(0);
			String locationRelationName = tokens.remove(0);
			String locationName = tokens.remove(0);
		
			this.cmdInstAction.createRelationWorkspaceLocation(workspaceName, locationRelationName, locationName, forroDriver);
			
		}
		
		
		
		
	}
		
		
		
		

}
