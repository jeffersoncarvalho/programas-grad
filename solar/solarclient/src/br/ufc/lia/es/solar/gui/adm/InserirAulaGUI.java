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
import br.ufc.lia.es.solar.model.AulaModel;
import br.ufc.lia.es.solar.model.DisciplinaModel;
import br.ufc.lia.es.solar.util.MarshalableVector;
import br.ufc.lia.es.solar.util.SplitString;

public class InserirAulaGUI implements CommandListener,IGUI {

	//MIDLet parent
	SolarMEMIDlet parent;
	 
	
	//
	Form mainForm;
	
	//comando para somar                                       
    private static final Command inserirCommand =
        new Command("Inserir", Command.SCREEN, 1);
    
    //campos
   // private TextField textfieldCodigo = new TextField("Código", "", 30, TextField.ANY);
	private TextField textfieldAssunto = new TextField("Assunto   ", "", 30, TextField.ANY);
	private TextField textfieldData = new TextField("Data", "", 30, TextField.ANY);
	private TextField textfieldTarefa = new TextField("Tarefa", "", 30, TextField.ANY);
	private ChoiceGroup choiceGroupCurso = null;
	private TextField textfieldDescricao = new TextField("Descrição", "", 60, TextField.ANY);
	private boolean first = true;
	 
	
    /*
     * Gera codigo que sera exibido em tela.
     */
    public InserirAulaGUI(SolarMEMIDlet parent) {
    	
    	this.parent  = parent ;
    	mainForm = new Form("Inserir Aula");
        mainForm.addCommand(inserirCommand);
         
        mainForm.append(textfieldAssunto); 
        mainForm.append(textfieldData);  
        mainForm.append(textfieldTarefa);
        this.update();
        if(choiceGroupCurso!=null){
        	mainForm.append(choiceGroupCurso);
            
        } 
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
    	
    	if(!first){
    		this.update();
    		
    	}
    	first = false;
        this.parent.getDisplay().setCurrent(mainForm);
    }

    private void update(){
    	try {
			MarshalableVector cursos = this.parent.getISolarServerService().listarDisciplinasLite();
			 
			String[] cursosArray = new String[cursos.size()];
		
			for(int i=0;i<cursos.size();i++)
				cursosArray[i] = ((DisciplinaModel)cursos.elementAt(i)).getCodigo() + "-"+ ((DisciplinaModel)cursos.elementAt(i)).getNome();
		
			choiceGroupCurso = new ChoiceGroup("Cursos",ChoiceGroup.POPUP,cursosArray,null);
			 
        
        } catch (ArcademisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
    }
    
    //action
    public void commandAction(Command c, Displayable d) {

    	if (c== inserirCommand){
    		 
            String assunto = textfieldAssunto.getString() ;
            String data = textfieldData.getString() ;
            String curso = choiceGroupCurso.getString(choiceGroupCurso.getSelectedIndex());
    		curso  = SplitString.split(curso+"-",'-')[0];
            String descricao = textfieldDescricao.getString() ;
            String tarefa = textfieldTarefa.getString();
            
            
            
            //campos nulos
            if(assunto==null
               || assunto.equals(""))
            {
            	AlertMessage alertMessageTest = new AlertMessage(parent,"Campos nulos!");
				alertMessageTest.show();
				return;
            }
            
             
			 AulaModel aulaModel = new AulaModel();
			 aulaModel.setAssunto(assunto);
			 aulaModel.setData(data);
			 aulaModel.setDisciplinaCodigo(curso);
			 aulaModel.setDescricao(descricao);
			 aulaModel.setTarefa(tarefa);
			
			 
			try {
				this.parent.getISolarServerService().inserirAula(aulaModel);
				 
			} catch (ArcademisException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			OKMessage okMessage = new OKMessage(this.parent,"Aula inserido com sucesso");
			okMessage.show();	
            
    	}else
    		this.parent.commandAction(c,d);
         
    }

    
     
     
     
}