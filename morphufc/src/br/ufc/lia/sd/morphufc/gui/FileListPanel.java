package br.ufc.lia.sd.morphufc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufc.lia.sd.morphufc.communication.Client;
import br.ufc.lia.sd.morphufc.communication.Server;
import br.ufc.lia.sd.morphufc.observer.Observer;
import br.ufc.lia.sd.morphufc.packet.Packet;
import br.ufc.lia.sd.morphufc.util.Constants;
import br.ufc.lia.sd.morphufc.util.FileUtil;
import br.ufc.lia.sd.morphufc.util.MetaFile;

public class FileListPanel extends JPanel implements ListSelectionListener {
	
	private JList list;
	private JLabel label = new JLabel("File List:");
	private JScrollPane scrollPane;
	private JButton downloadButton;
	private DefaultListModel listModel;
	private Client client;
	private String itemSelected;
	private Observer observer;
	public FileListPanel() {
 
		 
		this.setLayout(new BorderLayout());
		 
		this.add(label,BorderLayout.NORTH);
		
		listModel = new DefaultListModel();
		
		this.list = new JList(listModel);
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setLayoutOrientation(JList.VERTICAL_WRAP);
		this.list.setVisibleRowCount(5);
		this.list.addListSelectionListener(this);
		
		 
		
		this.scrollPane = new JScrollPane(list);
		 
		this.scrollPane.setPreferredSize(new Dimension(100,100));
		this.add(this.scrollPane,BorderLayout.CENTER);
		
		this.downloadButton = new JButton("Download");
		this.downloadButton.setEnabled(false);
		this.downloadButton.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					reset();
					
					if(FileUtil.existsOnReceived(itemSelected)){
						 if(getClientDecision(itemSelected)){
							 FileUtil.deleteOnReceived(itemSelected);
						 }else
							 observer.getControlPanel().enableControls();
							 
					}
					
					if(!FileUtil.existsOnReceived(itemSelected)){
						Packet packetToSend = new Packet();
						packetToSend.setCode(Constants.DOWNLOAD);
						packetToSend.setFileName(itemSelected);
						Server.printer.clientPrinter("Sending DOWNLOAD");
						client.send(packetToSend);
						downloadButton.setEnabled(false);
					}
					
					
					
				}
			}
		);
		 
		this.add(downloadButton,BorderLayout.SOUTH);
		
	}
	
	public void addFilesToList(MetaFile metaFile){
		this.listModel.addElement(metaFile);
	}
	
	public void reset(){
		this.listModel.clear();
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
                

            } else {
            //Selection, enable the fire button.
            	MetaFile selected = (MetaFile)list.getSelectedValue();
                itemSelected = selected.getName();
                //System.out.println(item);
                downloadButton.setEnabled(true);
                /**/
                
            }
        }
		
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private boolean getClientDecision(String fileName) {
	       Object[] options = {"yes","no" };
	       int n = JOptionPane.showOptionDialog(new Frame(),
	       "The file "+fileName+" already exists on your received directory.\nWould you like to replace it? ",
	       "question about replace",
	        JOptionPane.YES_NO_CANCEL_OPTION,
	        JOptionPane.QUESTION_MESSAGE,
	        null,
	        options,
	        options[0]);
	        this.downloadButton.setEnabled(false);
	         
	        if (n==0) return true;
	        if (n==1) return false;
	 
	     
	        else return false; 
	 
	}

	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}
}
