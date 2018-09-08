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
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.model.UsuarioModel;
import br.ufc.lia.es.solar.util.MarshalableVector;

public class InserirDisciplinaGUI implements CommandListener,IGUI {

	//MIDLet parent
	SolarMEMIDlet parent;
	 
	
	//
	Form mainForm;
	
	//comando para somar                                       
    private static final Command inserirCommand =
        new Command("Inserir", Command.SCREEN, 1);
    
    //campos
   // private TextField textfieldCodigo = new TextField("Código", "", 30, TextField.ANY);
	private TextField textfieldNome = new TextField("Nome   ", "", 30, TextField.ANY);
	private TextField textfieldHorario = new TextField("Horário", "", 30, TextField.ANY);
	private ChoiceGroup choiceGroupProfessor;
	
	private TextField textfieldDescricao = new TextField("Descrição", "", 60, TextField.ANY);
	
	 
	
    /*
     * Gera codigo que sera exibido em tela.
     */
    public InserirDisciplinaGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	mainForm = new Form("Inserir Curso");
    	
    	try {
    		//choice group
			MarshalableVector professores = this.parent.getISolarServerService().listarProfessores();
			String profArray[] = new String[professores.size()];
			for(int i=0;i<profArray.length;i++){
				UsuarioModel userRef = (UsuarioModel)professores.elementAt(i);
				profArray[i] = userRef.getLogin();
			}
			this.choiceGroupProfessor = new ChoiceGroup("Professor",ChoiceGroup.POPUP,profArray,null);
			
			 
		} catch (ArcademisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        mainForm.addCommand(inserirCommand);
        //mainForm.append(textfieldCodigo);
        mainForm.append(textfieldNome); 
        mainForm.append(textfieldHorario);  
        mainForm.append(choiceGroupProfessor); 
        mainForm.append(textfieldDescricao); 
         
        
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
    		String codigo = "000";//textfieldCodigo.getString() ;
            String nome = textfieldNome.getString() ;
            String horario = textfieldHorario.getString() ;
            String professor = choiceGroupProfessor.getString(choiceGroupProfessor.getSelectedIndex());
            String descricao = textfieldDescricao.getString() ;
             
            
            
            
            //campos nulos
            if(codigo==null || nome ==null
               || codigo.equals("") || nome.equals(""))
            {
            	AlertMessage alertMessageTest = new AlertMessage(parent,"Campos nulos!");
				alertMessageTest.show();
				return;
            }
            
             
			DisciplinaModel disciplinaModel = new DisciplinaModel();
			disciplinaModel.setCodigo(codigo);
			disciplinaModel.setNome(nome);
			disciplinaModel.setHorario(horario);
			disciplinaModel.setProfessor_login(professor);
			disciplinaModel.setDescricao(descricao);
			
			
			 
			try {
				this.parent.getISolarServerService().inserirDisciplina(disciplinaModel);
				 
			} catch (ArcademisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			OKMessage okMessage = new OKMessage(this.parent,"Curso inserido com sucesso");
			okMessage.show();	
            
    	}else
    		this.parent.commandAction(c,d);
         
    }

    
     
     
     
}
