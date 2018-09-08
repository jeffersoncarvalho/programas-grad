package br.ufc.lia.sd.morphufc.printers;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;


public class MemoPrinter implements IPrinter{

	private JTextArea textArea;
	
	public MemoPrinter() {
		this.textArea = new JTextArea();
		this.textArea.setRows(20);
		this.textArea.setEditable(false);
		this.textArea.setBackground(Color.BLACK);
		this.textArea.setForeground(Color.WHITE);
		Font font = new Font("Lucida Console",Font.PLAIN,12);
		
		this.textArea.setFont(font);
	}
	
	public void clientPrinter(String msg) {
		
		textArea.append("CLIENT: " +msg +"\n");
	}

	public void serverPrinter(String msg) {
		textArea.append("SERVER: " +msg + "\n");
		
	}

	public JTextArea getTextArea() {
		return textArea;
	}

}
