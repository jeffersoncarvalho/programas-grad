package br.ufc.lia.es.solar.gui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;

import arcademis.ArcademisException;
import br.ufc.lia.es.solar.gui.alert.AlertMessage;
import br.ufc.lia.es.solar.gui.alert.OKMessage;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.model.MensagemModel;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.CodesConstants;

public class EmailConteudoGUI implements CommandListener,IGUI {

	 // ticker
	   Ticker ticker = new Ticker(
	   "Solar Micro Edition");
	
	//MIDLet parent
	SolarMEMIDlet parent;
	 
	
	 
	
	//comando para somar                                       
    private static final Command submitCommand =
        new Command("Enviar", Command.SCREEN, 1);
    
    //campos
    TextBox textBox = new TextBox("", "", 250, TextField.ANY);

	
    /*
     * Gera codigo que sera exibido em tela.
     */
    public EmailConteudoGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	
    	
    	textBox.setTitle("Escrevendo email: ");
        textBox.addCommand(submitCommand);
       
        textBox.addCommand(SolarMEMIDlet.backCommand);
       
   	   
        textBox.setCommandListener(this);
        textBox.setTicker(ticker);
  	
    }

    /**
     * Neste metodo irei pegar a referencia "display", vinda do parent e colocar o que eu aloquei no
     * construtor. 
     *
     */ 
    public void show(){
    	
        this.parent.getDisplay().setCurrent(textBox);
    }

    //action
    public void commandAction(Command c, Displayable d) {
    	
    	
    	
    	if (c== submitCommand){
    		
    		if(this.textBox.getString()==null || this.textBox.getString().equals("")){
        		AlertMessage alertMessageTest = new AlertMessage(parent,"Campo nulo!");
    			alertMessageTest.show();
    			return;
        	}
    		
    		UsuarioModel usuarioModel = (UsuarioModel)this.parent.getSession(CodesConstants.LOGIN_OK);
    		DisciplinaModel disciplinaModel = (DisciplinaModel)this.parent.getSession(CodesConstants.SUBJECT);
    		final MensagemModel mensagemModel = new MensagemModel();
    		mensagemModel.setId(new Integer(0));
    		mensagemModel.setDisciplinaCodigo(disciplinaModel.getCodigo());
    		mensagemModel.setAutorLogin(usuarioModel.getLogin());
    		mensagemModel.setAutorNome(usuarioModel.getNome());
    		mensagemModel.setConteudo(this.textBox.getString());
    		final String destino = (String)this.parent.getSession(CodesConstants.EMAIL);
    		
    		Thread t = new Thread(){
    			
    			public void run() {
    				try {
    					parent.getISolarServerService().enviaMensagemEmail(mensagemModel, destino);
    				} catch (ArcademisException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		};
    		
    		t.start();
			
			OKMessage okMessage = new OKMessage(this.parent,"Mensagem enviada com sucesso");
			okMessage.show();
			
			
    	}else
    		this.parent.commandAction(c,d);
         
    }

    
     
     
     
}
