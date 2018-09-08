package br.ufc.lia.es.solar.gui;

 

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import arcademis.ArcademisException;
import br.ufc.lia.es.solar.gui.alert.AlertMessage;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.CodesConstants;
 


/**
 * The alert demo displays a list of alerts that will be displayes once the
 * user clicks a list item. These alerts try to present the full range of
 * alert types supported in MIDP.
 *
 * @version 2.0
 */
public class LoginGUI
       implements CommandListener,IGUI {

	//MIDLet parent
	SolarMEMIDlet parent;
	 
	
	//
	Form mainForm;
	
	//comando para somar                                       
    private static final Command loginCommand =
        new Command("Efetuar Login", Command.SCREEN, 1);
    
    //campos
    private TextField textfieldLogin = new TextField("Usuário", "", 10, TextField.ANY);
	private TextField textfieldSenha = new TextField("Senha   ", "", 10, TextField.PASSWORD);
	 
	
    /*
     * Gera codigo que sera exibido em tela.
     */
    public LoginGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	mainForm = new Form("Efetue o seu login");
    	 
        mainForm.addCommand(loginCommand);
  	     
        mainForm.append(textfieldLogin);
        mainForm.append(textfieldSenha);
        
        mainForm.setCommandListener(this);   
  	    
          
        mainForm.addCommand(SolarMEMIDlet.exitCommand);
        mainForm.setCommandListener(this);
    
        
    }

    /**
     * Neste metodo irei pegar a referencia "display", vinda do parent e colocar o que eu aloquei no
     * construtor. 
     *
     */ 
    public void show(){
    	
        this.parent.getDisplay().setCurrent(mainForm);
    }

    //action
    public void commandAction(Command c, Displayable d) {

    	if (c== loginCommand){
    		String login = textfieldLogin.getString() ;
            String senha = textfieldSenha.getString() ;
            
            //campos nulos
            if(login==null || senha ==null
               || login.equals("") || senha.equals(""))
            {
            	AlertMessage alertMessageTest = new AlertMessage(parent,"Campos nulos!");
				alertMessageTest.show();
				return;
            }
            
             
			UsuarioModel usuario = null;
			
			 
			try {
				usuario = this.parent.getISolarServerService().efetuarLogin(login, senha);
			} catch (ArcademisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(usuario!=null){
				 
				if(usuario.getCode().equals(CodesConstants.LOGIN_OK)){
					//cria e coloca o usuario na "sessao"
					this.parent.putSession(CodesConstants.LOGIN_OK,usuario);
					try {
						this.parent.getISolarServerService().incrementaAcessos(login);
					} catch (ArcademisException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//como o login deu ok, então mostra o menu principal
					PerfilGUI perfilGUI = this.parent.getGuiFactory().getPerfilGUI();
					perfilGUI.show();
				}else if(usuario.getCode().equals(CodesConstants.LOGIN_NOK_BAD_PASSWORD)){
					
					AlertMessage alertMessageTest = new AlertMessage(parent,"Senha inválida!");
					alertMessageTest.show();
				}else if(usuario.getCode().equals(CodesConstants.LOGIN_NOK_BAD_USER)){
					AlertMessage alertMessageTest = new AlertMessage(parent,"Usuário inválido!");
					alertMessageTest.show();
				}else if(usuario.getCode().equals(CodesConstants.UNDEFINED_ERROR)){
					AlertMessage alertMessageTest = new AlertMessage(parent,"Erro desconhecido!");
					alertMessageTest.show();
				} 
			}else{
				AlertMessage alertMessageTest = new AlertMessage(parent,"Erro desconhecido!");
				alertMessageTest.show();
			} 
				
            
    	}else{
    		 
    		this.parent.commandAction(c,d);
    	}
    		
         
    }

    
     
     
     
}