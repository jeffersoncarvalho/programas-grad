package frontendParserCCACaffeine_command_parser;

import java.util.ArrayList;

import forrocore.ForroDriverRMIInterface;
import frontendParserCCACaffeine_command_action_factory.FactoryConstants;
import frontendParserCCACaffeine_command_action_factory.ICCACommandActionFactory;
import frontendParserCCACaffeine_command_constants.CommandsContants;
import frontendParserCCACaffeine_command_constants.ErrorsConstants;
import frontendParserCCACaffeine_command_interfaces.ICommandRepositoryAction;
import frontendParserCCACaffeine_exceptions.FactoryException;
import frontendParserCCACaffeine_exceptions.ParserException;


public class CommandRepositoryParser extends AbstractParser{

	private ArrayList<String> tokens;
	private ICommandRepositoryAction cmdRepAction;
	
	public CommandRepositoryParser(ArrayList<String> tokens, ICCACommandActionFactory factory) throws FactoryException {
	
		this.setFactory(factory);
		this.tokens = tokens;
		this.cmdRepAction = (ICommandRepositoryAction)this.getFactory().create(FactoryConstants.REPOSITORY);
		
	}
	
	public void parse(ForroDriverRMIInterface forroDriver) throws ParserException {
		
		if (tokens.size()==0)
			throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
		
		String firstToken = tokens.remove(0);
		
		if(firstToken.equals(CommandsContants.REPOSITORY_LIST)){
			
			this.cmdRepAction.repositoryList(forroDriver);
			
		}else if(firstToken.equals(CommandsContants.REPOSITORY_GET)){
		
			if((tokens.size()<1)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>1)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			
			this.cmdRepAction.repositoryGet(tokens.remove(0),forroDriver);
			
		}else if(firstToken.equals(CommandsContants.REPOSITORY_GETPORTS)){
		
			if((tokens.size()<1)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>1)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			
			this.cmdRepAction.repositoryGetPorts(tokens.remove(0), forroDriver);
			
		}else if(firstToken.equals(CommandsContants.REPOSITORY_GETGLOBAL)){
		
			if((tokens.size()<1)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>1)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			
			this.cmdRepAction.repositoryGetGlobal(tokens.remove(0), forroDriver);
			
		}
		else if(firstToken.equals(CommandsContants.REPOSITORY_GETLAZY)){
		
			if((tokens.size()<1)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>1)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			
			this.cmdRepAction.repositoryGetLazy(tokens.remove(0), forroDriver);
			
		}else if(firstToken.equals(CommandsContants.REPOSITORY_GETLAZYGLOBAL)){
		
			if((tokens.size()<1)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>1)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			
			this.cmdRepAction.repositoryGetLazyGlobal(tokens.remove(0), forroDriver);
			
		}else if(firstToken.equals(CommandsContants.REPOSITORY_INIT)){
			if((tokens.size()<2)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>2)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
			String locationName = tokens.remove(0);
			String sessionName = tokens.remove(0);

			this.cmdRepAction.repositoryInit(locationName, sessionName, forroDriver);
			
		}else if(firstToken.equals(CommandsContants.REPOSITORY_INITLAZY)){
			
			this.cmdRepAction.repositoryInitLazy(forroDriver);
			
		}else if(firstToken.equals(CommandsContants.REPOSITORY_INITLAZYGLOBAL)){
			
			this.cmdRepAction.repositoryInitLazyGlobal(forroDriver);
			
		}else if(firstToken.equals(CommandsContants.REPOSITORY_INITGLOBAL)){
			if((tokens.size()<2)){
				throw new ParserException(ErrorsConstants.UNSUFICIENT_COMMAND);
			}else if((tokens.size()>2)){
				throw new ParserException(ErrorsConstants.TOO_MANY_COMMAND);
			}
				
			String forroName = tokens.remove(0);
			String sessionName = tokens.remove(0);

			this.cmdRepAction.repositoryInitGlobal(forroName, sessionName, forroDriver);
			
		}else throw new ParserException(ErrorsConstants.NOT_RECOG_COMMAND);
		
	}
	
	
}
