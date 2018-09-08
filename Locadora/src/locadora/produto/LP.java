package locadora.produto;

import java.text.DecimalFormat;

public class LP extends Musica{

	private boolean exemplarRaro;
	
	public void setExemplarRaro(boolean exemplarRaro) {
	
		this.exemplarRaro = exemplarRaro;
		
	}
	
	public boolean getExemplarRaro() {
		
		return exemplarRaro;
			
	}
	
	public double calculaDiaria()
	{
		double preco = 0.0;
		if(this.getExemplarRaro() == false)
		{
            preco = 4.00;}
        else{
            preco = 8.50;}
        return preco;
	}
	
	public String toString()
	{
		String output = super.toString();
		output += "\nRaro: " + this.exemplarRaro;
		DecimalFormat doisDigitos=new DecimalFormat("0.00");
		output += "\nVALOR DIARIA: " + doisDigitos.format(this.calculaDiaria());
		return output;
	}
		
}