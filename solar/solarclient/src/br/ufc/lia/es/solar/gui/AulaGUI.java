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
import br.ufc.lia.es.solar.model.AulaModel;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.util.CodesConstants;
import br.ufc.lia.es.solar.util.MarshalableVector;

public class AulaGUI implements CommandListener,IGUI {
	
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
public  AulaGUI(SolarMEMIDlet parent) {
	
		this.parent  = parent ;
		
		form = new Form("Cronograma");
		 
		
		

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
	 MarshalableVector aulas = this.getAulas();
	 for(int i=0;i<aulas.size();i++){
			AulaModel aulaModel = (AulaModel)aulas.elementAt(i);
			
			form.insert(position++,new StringItem(aulaModel.getAssunto(),""));
			form.insert(position++,new StringItem("Data: ",aulaModel.getData()));
			form.insert(position++,new StringItem("Assunto: ",aulaModel.getAssunto()));
			form.insert(position++,new StringItem("Tarefa:",aulaModel.getTarefa()));
			form.insert(position++,new StringItem(".::::::. ",""));
			 
	 }
	 
    this.parent.getDisplay().setCurrent(form);
}

public void commandAction(Command command, Displayable display) {
	 
	   this.parent.commandAction(command,display);
}

public void clean(){
	/* while (form.size() > 0)
    { 
       form.delete(0); 
    };*/
    form.deleteAll();
}

private MarshalableVector getAulas(){
	  DisciplinaModel curso =  (DisciplinaModel)this.parent.getSession(CodesConstants.SUBJECT);
	  try {
		return this.parent.getISolarServerService().listarAulas(curso.getCodigo());
	} catch (ArcademisException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}



}
