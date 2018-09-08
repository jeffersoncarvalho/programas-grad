package br.ufc.lia.es.solar.gui;

import java.util.Enumeration;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;

import arcademis.ArcademisException;
import br.ufc.lia.es.solar.gui.adm.MenuADMGUI;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.CodesConstants;
import br.ufc.lia.es.solar.util.MarshalableVector;

public class PerfilGUI implements CommandListener,IGUI {

	 // ticker
    Ticker ticker = new Ticker(
    "Solar Micro Edition");

	//MIDLet parent
	SolarMEMIDlet parent;
	
	 
	//main menu
    List menu;
	MarshalableVector disciplinas = null; 
	DisciplinaModel disciplinaEscolhida = null;
    boolean adm = false;
    int sub = 0;
    
 
    /*
     * Gera codigo que sera exibido em tela.
     */
    public PerfilGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	
    	
    	//pegando sessão
    	UsuarioModel user = (UsuarioModel)parent.getSession(CodesConstants.LOGIN_OK);
    	if(user!=null  ){
    	    
    		try {
    			this.disciplinas =	this.listarDisciplinas();
			} catch (ArcademisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		this.ticker.setString(this.ticker.getString()+" - selecione a disciplina");
    		
    	    //disciplinas
    	    menu = new List("Usuário: " + user.getNome(), Choice.IMPLICIT);
    	    
    	    if(user.getNivel().equals(CodesConstants.ADM_LEVEL)){
    	    	menu.append("Administração da Aplicação", null);
    	    	this.adm = true;
    	    	this.sub = 1;
    	    }
    	    
    	    Enumeration disEnum = this.disciplinas.elements();
    	    while(disEnum.hasMoreElements()){
    	    	DisciplinaModel disciplinaModel = (DisciplinaModel)disEnum.nextElement();
    	    	menu.append(disciplinaModel.getNome(), null);
    	    }
    	     
    	   
    	    
    	    menu.addCommand(SolarMEMIDlet.exitCommand);
    	    menu.setCommandListener(this);
    	    menu.setTicker(ticker);    			
    	} 
    		 
   
    
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
        if (label.equals(SolarMEMIDlet.exitCommand.getLabel())) {
           parent.destroyApp(true);
        }else {
	           List down = (List)parent.getDisplay().getCurrent();
	           int selected = down.getSelectedIndex();
	           if(selected==0 && this.adm){
	        	   MenuADMGUI menuADMGUI = this.parent.getGuiFactory().getMenuADMGUI();
	        	   menuADMGUI.show();
	           }else{
	        	   this.disciplinaEscolhida = (DisciplinaModel)this.disciplinas.elementAt(selected-this.sub);
	              	try {
	              		DisciplinaModel disciplinaModel = this.parent.getISolarServerService().retornaDisciplina(this.disciplinaEscolhida.getCodigo());
	              		this.parent.putSession(CodesConstants.SUBJECT, disciplinaModel);
	              		//mostando o menu pra disciplina escolhida que está em sessao
	              		MenuGUI menuGUI = this.parent.getGuiFactory().getMenuGUI();
	              		menuGUI.show();
	              	} catch (ArcademisException e) {
	              		
	              		e.printStackTrace();
	   			    }    
	           } 
        }
         
    }

    
   private MarshalableVector  listarDisciplinas() throws ArcademisException{
	   MarshalableVector list = null;
	   UsuarioModel usuario = (UsuarioModel)parent.getSession(CodesConstants.LOGIN_OK);
	   list = this.parent.getISolarServerService().listarDisciplinasPorUsuario(usuario.getLogin());
	    
	   return list;
   }
   
     
     
}
