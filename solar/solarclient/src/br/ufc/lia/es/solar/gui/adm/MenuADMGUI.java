package br.ufc.lia.es.solar.gui.adm;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;

import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;

public class MenuADMGUI implements CommandListener,IGUI {

	 // ticker
   Ticker ticker = new Ticker(
   "ADM - Solar Micro Edition");

	//MIDLet parent
	SolarMEMIDlet parent;
	
	 
	//main menu
   List menu;
   
    
	 
   

   /*
    * Gera codigo que sera exibido em tela.
    */
   public MenuADMGUI(SolarMEMIDlet parent) {
   	
   	this.parent  = parent ;
   	this.parent.setBackGUI(this);
   	
   	menu = new List("Administração", Choice.IMPLICIT);
   	menu.append("Inserir Usuário", null);
   	menu.append("Inserir Aula", null);
   	menu.append("Matricular Usuário", null);
   	menu.append("Inserir Curso", null);
   	menu.append("Atualizar Usuário", null);
    
   	
   	menu.addCommand(SolarMEMIDlet.exitCommand);
	menu.addCommand(SolarMEMIDlet.backCommand);
   	menu.setCommandListener(this);
   	menu.setTicker(ticker);
   	 
   
   }

   /**
    * Neste metodo irei pegar a referencia "display", vinda do parent e colocar o que eu aloquei no
    * construtor. 
    *
    */ 
   public void show(){
   	
       this.parent.getDisplay().setCurrent(menu);
   }

   //action
   public void commandAction(Command c, Displayable d) {

   	 
   	   String label = c.getLabel();
       if ( label.equals(SolarMEMIDlet.exitCommand.getLabel()) ||
    		label.equals(SolarMEMIDlet.backCommand.getLabel())) {
    	   this.parent.commandAction(c, d);
       } else {
          List down = (List)parent.getDisplay().getCurrent();
          switch(down.getSelectedIndex()) {
            case 0: 
           	  this.parent.getGuiFactory().getInserirUsuarioGUI().show();
            break;
            case 1:
           	  this.parent.getGuiFactory().getInserirAulaGUI().show();
            break;
            case 2:
            	
            	this.parent.getGuiFactory().getMatricularUsuarioGUI().show();
            break;
            case 3:
            	this.parent.getGuiFactory().getInserirDisciplinaGUI().show();
            break;
            case 4:
            	this.parent.getGuiFactory().getAtualizarUsuarioChoiceGUI().show();
            break;
          }
             
       }
        
   }

	 

  
    
    
}
