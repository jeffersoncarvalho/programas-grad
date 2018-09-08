package br.ufc.lia.es.solar.gui.adm;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import arcademis.ArcademisException;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.CodesConstants;
import br.ufc.lia.es.solar.util.MarshalableVector;

public class AtualizarUsuarioChoiceGUI implements CommandListener,IGUI {

	//MIDLet parent
	SolarMEMIDlet parent;
	 
	
	//
	Form mainForm;
	
	//comando para somar                                       
    private static final Command okCommand =
        new Command("Ok", Command.SCREEN, 1);
    
    //campos
     
	 
	private ChoiceGroup choiceGroupUsers = null;
	boolean first = true;
	
    /*
     * Gera codigo que sera exibido em tela.
     */
    public AtualizarUsuarioChoiceGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	mainForm = new Form("Escolha o Usuário a ser Atualizado");
    	 
        mainForm.addCommand(okCommand);
  	    
        this.update();
        
        if( choiceGroupUsers!=null){
        	 
            mainForm.append(choiceGroupUsers);
        }
        
        mainForm.setCommandListener(this);   
  	    
          
        mainForm.addCommand(SolarMEMIDlet.backCommand);
        mainForm.setCommandListener(this);
    
        
    }

    /**
     * Neste metodo irei pegar a referencia "display", vinda do parent e colocar o que eu aloquei no
     * construtor. 
     *
     */ 
    public void show(){
    	if(!first){
    		this.update();
    		
    	}
    	first = false;
        this.parent.getDisplay().setCurrent(mainForm);
        
    }

    private void update(){
    	try {
			
			MarshalableVector users  = this.parent.getISolarServerService().listarUsuariosLite();
			String[] usersArray = new String[users.size()];
			
			for(int i=0;i<users.size();i++)
				usersArray[i] = ((UsuarioModel)users.elementAt(i)).getLogin();
			
			
			choiceGroupUsers = new ChoiceGroup("Usuários",ChoiceGroup.POPUP,usersArray,null);
        
        } catch (ArcademisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
    }
    
    //action
    public void commandAction(Command c, Displayable d) {

    	if (c== okCommand){
    		 
    		 
    		String login = choiceGroupUsers.getString(choiceGroupUsers.getSelectedIndex());
    		this.parent.putSession(CodesConstants.TEMP_LOGIN, login); 
    		
    		this.parent.getGuiFactory().getAtualizarUsuarioGUI().show();
            
    	}else
    		this.parent.commandAction(c,d);
         
    }

    
     
     
     
}
