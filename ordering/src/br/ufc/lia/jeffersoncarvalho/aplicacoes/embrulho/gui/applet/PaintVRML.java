package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet;
import java.awt.*;
 
/*import vrml.external.field.*;
import vrml.external.Node;
import vrml.external.Browser;
import vrml.external.exception.*;*/

public class PaintVRML implements Painter{  

  Applet3d applet; //parent applet
  Object3d model;

  public PaintVRML(Applet3d applet){
    this.applet = applet;
  }

  public void setModel(Object3d model){
    // get the handle to the VRML Browser.
    // we must do this first to ensure that vrmlAllPROTOs works

    VRMLState state = new VRMLState(applet.viewer);
    model.toVRML(state);
    AnimationWidgetVRML.toVRML(state);
    String s = "NavigationInfo{type [\"EXAMINE\",\"ANY\"]}\n"+
      state.toString();
    if (applet.getParameter("cgi")!=null){
      System.out.println("#VRML V2.0 utf8\n"+s);
    } /*else {
      try {
	 Browser browser = (Browser) vrml.external.Browser.getBrowser(applet);
	Node[] scene = browser.createVrmlFromString(s);
	browser.replaceWorld(scene);
      }
      catch (InvalidVrmlException ie) {
	System.err.println("Bad VRML! " + ie);
	//System.err.println(s);
      }
      catch (NoClassDefFoundError e) {
	System.err.println("Cannot communicate with VRML browser");
      }
    }*/
  }
  
  
  public void paint(Graphics g){
    //nothing to do - VRML browser does it all
  }
  
  public void run(){
  }
}

