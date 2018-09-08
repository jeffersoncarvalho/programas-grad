package br.ufc.lia.es.solar.gui;

 

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.ufc.lia.es.solar.gui.alert.AlertMessage;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.util.CodesConstants;
 


/**
 * The alert demo displays a list of alerts that will be displayes once the
 * user clicks a list item. These alerts try to present the full range of
 * alert types supported in MIDP.
 *
 * @version 2.0
 */
public class EmailDestinoGUI
       implements CommandListener,IGUI {

	//MIDLet parent
	SolarMEMIDlet parent;
	 
	
	//
	Form mainForm;
	
	//comando para somar                                       
    private static final Command okCommand =
        new Command("OK", Command.SCREEN, 1);
    
    //campos
    private TextField textfieldEmail = new TextField("Email", "", 30, TextField.EMAILADDR);
	
	 
	
    /*
     * Gera codigo que sera exibido em tela.
     */
    public EmailDestinoGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	mainForm = new Form("Entre com email destino");
    	 
        mainForm.addCommand(okCommand);
  	     
        mainForm.append(textfieldEmail);
        
        
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

    	if (c== okCommand){
    		String email = textfieldEmail.getString() ;
             
            
            //campos nulos
            if(email==null  
               || email.equals("")  )
            {
            	AlertMessage alertMessageTest = new AlertMessage(parent,"Campos nulos!");
				alertMessageTest.show();
				return;
            }
            
             
			 
			this.parent.putSession(CodesConstants.EMAIL, email); 
			
			EmailConteudoGUI emailConteudoGUI = this.parent.getGuiFactory().getEmailConteudoGUI();
			emailConteudoGUI.show(); 
				
            
    	}else
    		this.parent.commandAction(c,d);
         
    }

    
     
     
     
}