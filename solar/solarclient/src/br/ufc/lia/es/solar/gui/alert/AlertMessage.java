package br.ufc.lia.es.solar.gui.alert;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;

import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;

public class AlertMessage   implements IGUI{

	//MIDLet parent
	SolarMEMIDlet parent;
    
    //campos
    final Alert soundAlert = 
        new Alert("sound Alert");
	
    
    public AlertMessage(SolarMEMIDlet parent, String message) {
    	
    	this.parent = parent; 
        
    	soundAlert.setType(AlertType.ERROR);
        //soundAlert.setTimeout(20);
        soundAlert.setString("** ERROR **\n" + message);
       
    }

 

	public void show() {
		this.parent.getDisplay().setCurrent(soundAlert);
	}
}
