package br.ufc.lia.es.solar.gui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.Ticker;

import arcademis.ArcademisException;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.model.MensagemModel;
import br.ufc.lia.es.solar.util.CodesConstants;
import br.ufc.lia.es.solar.util.MarshalableVector;

public class ForumGUI implements CommandListener,IGUI {
	
	 // ticker
 Ticker ticker = new Ticker(
 "Solar Micro Edition");

	//MIDLet parent
	SolarMEMIDlet parent;
	
	 
	 
	//main menu
 Form form;
 
  


 /*
  * Gera codigo que sera exibido em tela.
  */
 public ForumGUI(SolarMEMIDlet parent) {
 	
 		this.parent  = parent ;
 		
 		form = new Form("Fórum");
 		 
 		
 		
 
 		form.addCommand(SolarMEMIDlet.backCommand);
 	    //form.addCommand(SolarMEMIDlet.exitCommand);
 	   
 	    form.setCommandListener(this);
 	    form.setTicker(ticker);    			
 	 
 		 

 
 }

 /**
  * Neste metodo irei pegar a referencia "display", vinda do parent e colocar o que eu aloquei no
  * construtor. 
  *
  */ 
 public void show(){
	 
	 
	 int position = 0;
	 MarshalableVector mensagens = this.getMensagens();
	 for(int i=0;i<mensagens.size();i++){
			MensagemModel msgModel = (MensagemModel)mensagens.elementAt(i);
			
			form.insert(position++,new StringItem("De: ",msgModel.getAutorNome()));
			form.insert(position++,new StringItem("Data: ",msgModel.getData()));
			form.insert(position++,new StringItem("-",msgModel.getConteudo()));
			form.insert(position++,new StringItem(".::::::. ",""));
			 
	 }
	 
     this.parent.getDisplay().setCurrent(form);
 }

 public void commandAction(Command command, Displayable display) {
	 
	   this.parent.commandAction(command,display);
 }

 public void clean(){
	 
     form.deleteAll();
 }
 
 private MarshalableVector getMensagens(){
	  DisciplinaModel curso =  (DisciplinaModel)this.parent.getSession(CodesConstants.SUBJECT);
	  try {
		return this.parent.getISolarServerService().listarMensagemsForum(curso.getCodigo());
	} catch (ArcademisException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
 }
 


}
