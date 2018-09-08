package br.ufc.lia.sd.morphufc.observer;

import br.ufc.lia.sd.morphufc.gui.ControlPanel;
import br.ufc.lia.sd.morphufc.gui.FileListPanel;
import br.ufc.lia.sd.morphufc.gui.LogPanel;

public class Observer {
	
	 
	ControlPanel controlPanel;
	LogPanel logPanel;
	FileListPanel fileListPanel;
	
	public ControlPanel getControlPanel() {
		return controlPanel;
	}
	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
	public FileListPanel getFileListPanel() {
		return fileListPanel;
	}
	public void setFileListPanel(FileListPanel fileListPanel) {
		this.fileListPanel = fileListPanel;
	}
	public LogPanel getLogPanel() {
		return logPanel;
	}
	public void setLogPanel(LogPanel logPanel) {
		this.logPanel = logPanel;
	}

}
