package br.ufc.lia.sd.morphufc.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import br.ufc.lia.sd.morphufc.communication.Server;
import br.ufc.lia.sd.morphufc.printers.MemoPrinter;

public class LogPanel extends JPanel{

	JProgressBar progressBar;
	private int progress = 0;
	
	public LogPanel(){ 
		
		this.progressBar = new JProgressBar();
		
		//layout
		this.setLayout(new BorderLayout());
		this.progressBar.setValue(this.progress);
		this.progressBar.setStringPainted(true);
		
		//adcionando componentes ao layout
		JScrollPane scrollPane = new JScrollPane(((MemoPrinter)Server.printer).getTextArea());
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(this.progressBar,BorderLayout.NORTH);
		
	}
	
	public void setBarSize(int size){
		this.progressBar.setMaximum(size);
	}
	
	public void reset()
	{
		
		this.progressBar.setValue(0);
	}
	
	public void addProgress()
	{
		this.progress++;
		this.progressBar.setValue(this.progress);
	}
}
