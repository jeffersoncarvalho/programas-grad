package br.ufc.lia.sd.morphufc.printers;

 



public class ConsolePrinter implements IPrinter{

	public void clientPrinter(String msg) {
		System.out.println("CLIENT: " +  msg);
		
	}

	public void serverPrinter(String msg) {
		System.out.println("SERVER: " +  msg);
		
	}

}
