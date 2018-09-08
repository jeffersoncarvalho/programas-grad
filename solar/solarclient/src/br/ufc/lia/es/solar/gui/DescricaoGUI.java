package br.ufc.lia.es.solar.gui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.Ticker;

import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.util.CodesConstants;

public class DescricaoGUI implements CommandListener,IGUI {

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
 public DescricaoGUI(SolarMEMIDlet parent) {
 	
 		this.parent  = parent ;
 		DisciplinaModel curso = (DisciplinaModel)this.parent.getSession(CodesConstants.SUBJECT);
 		form = new Form("Descrição da Disciplina");
 		form.append(new StringItem("",curso.getDescricao()));
 		
 		
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
 	
     this.parent.getDisplay().setCurrent(form);
 }

 public void commandAction(Command command, Displayable display) {
	 
	   this.parent.commandAction(command,display);
 }

 
  
 
}
