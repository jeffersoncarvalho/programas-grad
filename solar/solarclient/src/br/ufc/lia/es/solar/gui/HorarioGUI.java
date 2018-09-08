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

public class HorarioGUI implements CommandListener,IGUI {

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
  public HorarioGUI(SolarMEMIDlet parent) {
  	
  		this.parent  = parent ;
  		DisciplinaModel curso = (DisciplinaModel)this.parent.getSession(CodesConstants.SUBJECT);
  		form = new Form("Horários de acompanhamento ao vivo");
  		form.append(new StringItem("",curso.getHorario()));
  		
  		
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
