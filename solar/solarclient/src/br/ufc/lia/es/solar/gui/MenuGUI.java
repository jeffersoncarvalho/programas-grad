package br.ufc.lia.es.solar.gui;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;

import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.util.CodesConstants;

public class MenuGUI implements CommandListener,IGUI {

	 // ticker
    Ticker ticker = new Ticker(
    "Solar Micro Edition");

	//MIDLet parent
	SolarMEMIDlet parent;
	
	 
	//main menu
    List menu;
    
     
	 
    
 
    /*
     * Gera codigo que sera exibido em tela.
     */
    public MenuGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	this.parent.setBackGUI(this);
    	DisciplinaModel disRef = (DisciplinaModel)this.parent.getSession(CodesConstants.SUBJECT);
    	menu = new List("Curso: " + disRef.getNome(), Choice.IMPLICIT);
    	menu.append("Agenda", null);
    	menu.append("Descrição", null);
    	menu.append("Material de Apoio", null);
    	menu.append("Participantes", null);
    	menu.append("Fórum", null);
    	menu.append("Horários", null);
    	menu.append("Professor", null);
    	menu.append("Enviar Mensagem ao Fórum", null);
    	menu.append("Enviar Email", null);

     
    	
    	menu.addCommand(SolarMEMIDlet.exitCommand);
    	 
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
            	 AulaGUI aulaGUI = this.parent.getGuiFactory().getAulaGUI();
            	 aulaGUI.clean();
            	 aulaGUI.show();
             break;
             case 1:
            	 DescricaoGUI descricaoGUI = this.parent.getGuiFactory().getDescricaoGUI();
            	 descricaoGUI.show();
             break;
             case 2:  
            	 System.out.println("apoio");
             break;
             case 3: 
            	 
            	 ParticipantesGUI partcipantesGUI = this.parent.getGuiFactory().getPartcipantesGUI();
            	 partcipantesGUI.show();
             break;
             case 4:  
            	 ForumGUI forumGUI = this.parent.getGuiFactory().getForumGUI();
            	 forumGUI.clean();
            	 forumGUI.show();
            	
             break;
             case 5:  
            	 HorarioGUI horarioGUI = this.parent.getGuiFactory().getHorarioGUI();
            	 horarioGUI.show();
             break;
             case 6:  
            	 
            	 ProfessorGUI professorGUI = this.parent.getGuiFactory().getProfessorGUI();
            	 professorGUI.show();
             break;
             case 7:  
            	 
            	 MensagemGUI mensagemGUI = this.parent.getGuiFactory().getMensagemGUI();
            	 mensagemGUI.show();
            	  
             break;
             case 8:  
            	EmailDestinoGUI emailDestinoGUI = this.parent.getGuiFactory().getEmDestinoGUI();
            	emailDestinoGUI.show();
				  
			 break;
              
           }
              
        }
         
    }

	 

   
     
     
}
