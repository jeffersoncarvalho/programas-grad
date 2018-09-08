package br.ufc.lia.es.solar.gui.adm;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import arcademis.ArcademisException;
import br.ufc.lia.es.solar.gui.alert.AlertMessage;
import br.ufc.lia.es.solar.gui.alert.OKMessage;
import br.ufc.lia.es.solar.gui.service.IGUI;
import br.ufc.lia.es.solar.main.SolarMEMIDlet;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.model.UsuarioDisciplinaModel;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.MarshalableVector;
import br.ufc.lia.es.solar.util.SplitString;

public class MatricularUsuarioGUI implements CommandListener,IGUI {

	//MIDLet parent
	SolarMEMIDlet parent;
	 
	
	//
	Form mainForm;
	
	//comando para somar                                       
    private static final Command inserirCommand =
        new Command("Inserir", Command.SCREEN, 1);
    
    //campos
     
	private ChoiceGroup choiceGroupCurso = null;
	private ChoiceGroup choiceGroupUsers = null;
	boolean first = true;
	
    /*
     * Gera codigo que sera exibido em tela.
     */
    public MatricularUsuarioGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	mainForm = new Form("Matricular Usuário");
    	 
        mainForm.addCommand(inserirCommand);
  	    
        this.update();
        
        if(choiceGroupCurso!=null && choiceGroupUsers!=null){
        	mainForm.append(choiceGroupCurso);
            mainForm.append(choiceGroupUsers);
        }
        
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
    	if(!first){
    		this.update();
    		
    	}
    	first = false;
        this.parent.getDisplay().setCurrent(mainForm);
        
    }

    private void update(){
    	try {
			MarshalableVector cursos = this.parent.getISolarServerService().listarDisciplinasLite();
			MarshalableVector users  = this.parent.getISolarServerService().listarUsuariosLite();
			String[] cursosArray = new String[cursos.size()];
			String[] usersArray = new String[users.size()];
			
			for(int i=0;i<cursos.size();i++)
				cursosArray[i] = ((DisciplinaModel)cursos.elementAt(i)).getCodigo() + "-"+ ((DisciplinaModel)cursos.elementAt(i)).getNome();
		
			for(int i=0;i<users.size();i++)
				usersArray[i] = ((UsuarioModel)users.elementAt(i)).getLogin();
			
			choiceGroupCurso = new ChoiceGroup("Cursos",ChoiceGroup.POPUP,cursosArray,null);
			choiceGroupUsers = new ChoiceGroup("Usuários",ChoiceGroup.POPUP,usersArray,null);
        
        } catch (ArcademisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
    }
    
    //action
    public void commandAction(Command c, Displayable d) {

    	if (c== inserirCommand){
    		 
    		 
    		String login = choiceGroupUsers.getString(choiceGroupUsers.getSelectedIndex());
    		String curso = choiceGroupCurso.getString(choiceGroupCurso.getSelectedIndex());
    		curso  = SplitString.split(curso+"-",'-')[0];
            
            //campos nulos
            if(login==null || curso ==null
               || login.equals("") || curso.equals(""))
            {
            	AlertMessage alertMessageTest = new AlertMessage(parent,"Campos nulos!");
				alertMessageTest.show();
				return;
            }
            
             
			UsuarioDisciplinaModel usuarioDisciplinaModel = new UsuarioDisciplinaModel();
			usuarioDisciplinaModel.setDisciplina_codigo(curso);
			usuarioDisciplinaModel.setUsuario_login(login);
			 
			try {
				this.parent.getISolarServerService().inserirMatricula(usuarioDisciplinaModel);
			} catch (ArcademisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			OKMessage okMessage = new OKMessage(this.parent,"Matricula inserida com sucesso");
			okMessage.show();	
            
    	}else
    		this.parent.commandAction(c,d);
         
    }

    
     
     
     
}
