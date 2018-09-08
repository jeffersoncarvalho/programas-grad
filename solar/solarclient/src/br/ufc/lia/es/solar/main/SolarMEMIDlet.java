package br.ufc.lia.es.solar.main;

import java.util.Hashtable;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

import rme.RmeConfigurator;
import rme.naming.RmeNaming;

import arcademis.ArcademisException;
import arcademis.NotBoundException;
import arcademis.concreteComponents.MalformedURLException;
import br.ufc.lia.es.solar.gui.LoginGUI;
import br.ufc.lia.es.solar.gui.factory.GUIFactory;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.rme.ISolarServerService;

/**
 * Representa a classe principal que ira rodar no dispositivo movel.
 * @author Jefferson
 * @version 1.0
 */

public class SolarMEMIDlet extends MIDlet 
implements CommandListener {
    // display manager
    private Display display;
    
    private ISolarServerService iSolarServerService;
    private GUIFactory guiFactory;
    
    // a menu with items
    // main menu
    //private List menu;
 
    

    

    //Sessão que ficara disponivel a toda a aplicação.
    private Hashtable  sessionTable = new Hashtable();

  

    // command
    public static final Command backCommand = new Command("Back", Command.BACK, 0);
    public static final Command mainMenuCommand = new Command("Main", Command.SCREEN, 1);
    public static final Command exitCommand = new Command("Exit", Command.STOP, 2);
     
    
    //Tela inicial
    private LoginGUI loginGui ;
    
    //Back GUI
    private IGUI backGUI;
    
    // constructor.
    public SolarMEMIDlet() {
    	 this.guiFactory = new GUIFactory(this);
    	 this.loginGui = guiFactory.getLoginGUI();
    	 this.lookupRMEinterface();
    	
    }

    private void lookupRMEinterface(){
    	
    	try {
    		RmeConfigurator mc = new RmeConfigurator();
			mc.configure();
    		 
			iSolarServerService = (ISolarServerService)RmeNaming.lookup("localhost/solar");
			
    	} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArcademisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
    }
    
    /**
     * Metodo chamdo automaticamente
     * Start the MIDlet by creating a list of
     * items and associating the
     * exit command with it.
     */
    public void startApp() throws 
    MIDletStateChangeException 
    {
        
      display = Display.getDisplay(this);
      this.loginGui.show();
       
    }

    public void pauseApp() {
      display = null;
      //menu = null;
      
  
    }

    public void destroyApp(boolean unconditional) {
      notifyDestroyed();
    }

   /**
    * Handle events.
    */  
   public void commandAction(Command c, Displayable d) {
      String label = c.getLabel();
       
      if (label.equals(SolarMEMIDlet.exitCommand.getLabel())) {
         destroyApp(true);
      }if (label.equals(SolarMEMIDlet.backCommand.getLabel())){
    	  if(this.backGUI!=null)
    		  this.backGUI.show();
      }
       
  }

  public Display getDisplay() {
		return display;
  }
  
  //codigo de acesso ao objeto Singleton, que eh a sessão.
  
  public void putSession(Object key, Object content){
	  this.sessionTable.put(key,content);
  }
  
  public Object getSession(Object key){
	return this.sessionTable.get(key);  
  }

  public ISolarServerService getISolarServerService() {
		return iSolarServerService;
  }

public IGUI getBackGUI() {
	return backGUI;
}

public void setBackGUI(IGUI backGUI) {
	this.backGUI = backGUI;
}

public GUIFactory getGuiFactory() {
	return guiFactory;
}
	  
}
