package br.ufc.lia.jeffersoncarvalho.main;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.EmbrulhoGUI;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet.AppletHull;

public class MainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args!=null && args.length>0){
			if(args[0].equals("opengl")){
				new EmbrulhoGUI();
			}else if (args[0].equals("vrml")){
				 AppletHull applet = new AppletHull();
				 applet.init();
				 System.exit(0);
			}
		}else
			System.out.println("Opcoes:\n\topengl carrega versao opengl\n\tvrml carrega versao vrml");

	}

}
