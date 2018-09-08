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
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.CodesConstants;
import br.ufc.lia.es.solar.util.MarshalableVector;

public class ParticipantesGUI implements CommandListener,IGUI {
	
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
	  public ParticipantesGUI(SolarMEMIDlet parent) {
	  	
	  		this.parent  = parent ;
	  		MarshalableVector participantes = this.getPartipantes();
	  		form = new Form("Lista de Particpantes");
	  		 
	  		for(int i=0;i<participantes.size();i++){
	  			UsuarioModel usuarioModel = (UsuarioModel)participantes.elementAt(i);
	  			form.append(new StringItem("Nome: ",usuarioModel.getNome()));
	  			form.append(new StringItem("Acessos ao sistema: ",usuarioModel.getAcessos().toString()));
	  			form.append(new StringItem(" "," "));
	  		}
	  		
	  
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
	
	  
	  private MarshalableVector getPartipantes(){
		  DisciplinaModel curso =  (DisciplinaModel)this.parent.getSession(CodesConstants.SUBJECT);
		  try {
			return this.parent.getISolarServerService().listarParticipantes(curso.getCodigo());
		} catch (ArcademisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }
	  

  
}
