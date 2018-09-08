package br.ufc.lia.es.solar.gui.adm;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import arcademis.ArcademisException;
import br.ufc.lia.es.solar.gui.alert.AlertMessage;
import br.ufc.lia.es.solar.gui.alert.OKMessage;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.SplitString;

public class InserirUsuarioGUI implements CommandListener,IGUI {

	//MIDLet parent
	SolarMEMIDlet parent;
	 
	
	//
	Form mainForm;
	
	//comando para somar                                       
    private static final Command inserirCommand =
        new Command("Inserir", Command.SCREEN, 1);
    
    //campos
    private TextField textfieldLogin = new TextField("Usuário", "", 10, TextField.ANY);
	private TextField textfieldSenha = new TextField("Senha   ", "", 10, TextField.PASSWORD);
	private TextField textfieldNome = new TextField("Nome", "", 30, TextField.ANY);
	private TextField textfieldEmail = new TextField("Email", "", 30, TextField.ANY);
	private TextField textfieldSite = new TextField("Site", "", 30, TextField.ANY);
	private TextField textfieldArea = new TextField("Área", "", 30, TextField.ANY);
	String niveis[] = {"1-Aluno","2-Professor"};
	private ChoiceGroup choiceGroupNivel = new ChoiceGroup("Nível",ChoiceGroup.EXCLUSIVE,niveis,null);
	//private TextField textfieldNivel = new TextField("Nivel", "", 1, TextField.ANY);
	
    /*
     * Gera codigo que sera exibido em tela.
     */
    public InserirUsuarioGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	mainForm = new Form("Inserir Usuário");
    	 
        mainForm.addCommand(inserirCommand);
  	     
        mainForm.append(textfieldLogin);
        mainForm.append(textfieldSenha);
        mainForm.append(textfieldNome); 
        mainForm.append(textfieldEmail);  
        mainForm.append(textfieldSite); 
        mainForm.append(textfieldArea); 
        mainForm.append(choiceGroupNivel);
        
        mainForm.setCommandListener(this);   
  	    
          
        mainForm.addCommand(SolarMEMIDlet.backCommand);
        mainForm.setCommandListener(this);
    
        
    }

    /**
     * Neste metodo irei pegar a referencia "display", vinda do parent e colocar o que eu aloquei no
     * construtor. 
     *
     */ 
    public void show(){
    	
        this.parent.getDisplay().setCurrent(mainForm);
    }

    //action
    public void commandAction(Command c, Displayable d) {

    	if (c== inserirCommand){
    		String login = textfieldLogin.getString() ;
            String senha = textfieldSenha.getString() ;
            String nome = textfieldNome.getString() ;
            String email = textfieldEmail.getString() ;
            String site = textfieldSite.getString() ;
            String nivel = choiceGroupNivel.getString(choiceGroupNivel.getSelectedIndex()) ;
            nivel = SplitString.split(nivel+"-",'-')[0];
            String area = textfieldArea.getString() ;
            
            
            
            //campos nulos
            if(login==null || senha ==null
               || login.equals("") || senha.equals(""))
            {
            	AlertMessage alertMessageTest = new AlertMessage(parent,"Campos nulos!");
				alertMessageTest.show();
				return;
            }
            
             
			UsuarioModel usuario = new UsuarioModel();
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setNivel(nivel);
			usuario.setEmail(email);
			usuario.setSite(site);
			usuario.setArea(area);
			usuario.setAcessos(new Integer(0));
			 
			try {
				this.parent.getISolarServerService().inserirUsuario(usuario);
				
			} catch (ArcademisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			OKMessage okMessage = new OKMessage(this.parent,"Usuário inserido com sucesso");
			okMessage.show();	
            
    	}else
    		this.parent.commandAction(c,d);
         
    }

    
     
     
     
}
