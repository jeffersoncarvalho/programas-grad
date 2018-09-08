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

public class ProfessorGUI implements CommandListener,IGUI {

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
   public ProfessorGUI(SolarMEMIDlet parent) {
   	
   		this.parent  = parent ;
   		UsuarioModel professor = this.getProfessor();
   		form = new Form("Perfil do Professor");
   		form.append(new StringItem("Nome: ",professor.getNome()));
   		form.append(new StringItem("E-mail: ",professor.getEmail()));
   		form.append(new StringItem("Site: ",professor.getSite()));
   		form.append(new StringItem("Área: ",professor.getArea()));
   		form.append(new StringItem("Acessos ao sistema: ",professor.getAcessos().toString()));
   		
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

   
   private UsuarioModel getProfessor(){
	   DisciplinaModel curso =  (DisciplinaModel)this.parent.getSession(CodesConstants.SUBJECT);
	   
	   try {
		return this.parent.getISolarServerService().retornaUsuario(curso.getProfessor_login());
		} catch (ArcademisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
   }
   

   
}
