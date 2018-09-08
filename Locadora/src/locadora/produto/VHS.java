package locadora.produto;

import java.text.DecimalFormat;

public class VHS extends Filme{

	private boolean cores;
	
	public void setCores(boolean cores) {
	
		this.cores = cores;
		
	}
	
	public boolean getCores() {
	
		return cores;
		
	}
	
	public double calculaDiaria()
	{
		double preco = 0.0;
		
			   if (this.getAnoLancamento() >= 2000){
                       preco = 4.00;
               }
               else{
                       preco = 2.30;
               }
               
               if(this.getCores() == false)
               {
                       preco /= 2;
               }
               
               return preco;
	}
	
	public String toString()
	{
		String output = super.toString();
		output += "\nA cores: " + this.cores;
		DecimalFormat doisDigitos=new DecimalFormat("0.00");
		output += "\nVALOR DIARIA: " + doisDigitos.format(this.calculaDiaria());
		return output;
	}
		
}