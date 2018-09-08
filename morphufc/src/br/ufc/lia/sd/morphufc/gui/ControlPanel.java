package br.ufc.lia.sd.morphufc.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufc.lia.sd.morphufc.communication.Client;
import br.ufc.lia.sd.morphufc.communication.Server;
import br.ufc.lia.sd.morphufc.observer.Observer;

public class ControlPanel extends JPanel{
	
	private JLabel searchLabel;
	private JTextField fileNameField;
	private JButton searchButton;
	private Observer observer;
	
	public ControlPanel(){
	
		//criando componentes
		this.fileNameField = new JTextField(20);
		this.searchButton = new JButton("Procurar");
		this.searchLabel = new JLabel("Arquivo: ");
		//layout
		this.setLayout(new BorderLayout());
		
		//listeners
		//cria clientes os quais
		//procuram arquivos locais e posteriormente, remotos.
		this.searchButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String content = fileNameField.getText().trim();
				if(content!=null && !content.equals("") && !content.equals(" ")){
					disableControls();
					observer.getFileListPanel().reset();
					Client client = new Client();
					client.setFileRequired(content);
					Server.printer.clientPrinter("=======================");
					client.setObserver(observer);
					client.start();
					
				}
				else
					JOptionPane.showMessageDialog(null,"Invalid Input!!!","Error",JOptionPane.ERROR_MESSAGE);
				
			}
		}
		);
		
		//adcionando componentes ao layout
		this.add(searchLabel,BorderLayout.WEST);
		this.add(fileNameField,BorderLayout.CENTER);
		this.add(searchButton,BorderLayout.EAST);
	}

	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	
	public void enableControls(){
		this.searchButton.setEnabled(true);
		this.fileNameField.setEnabled(true);
	}
	
	public void disableControls(){
		this.searchButton.setEnabled(false);
		this.fileNameField.setEnabled(false);
	}

}
