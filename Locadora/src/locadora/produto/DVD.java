package locadora.produto;

import java.text.DecimalFormat;

public class DVD extends Filme{

	private boolean arranhado;
	
	public void setArranhado(boolean arranhado) {
	
		this.arranhado = arranhado;
		
	}
	
	public boolean getArranhado() {
	
		return arranhado;
		
	}
	
	public double calculaDiaria()
	{
		double preco;
		       if(this.getAnoLancamento() >=2000){
                     preco = 5.00;}
               else{
                     preco = 3.00;}
               if(this.getArranhado() == true){
                     preco -= 0.3*preco;}
         return preco;
	}
	
	public String toString()
	{
		String output = super.toString();
		output += "\nArranhado: " + this.arranhado;
		DecimalFormat doisDigitos=new DecimalFormat("0.00");
		output += "\nVALOR DIARIA: " + doisDigitos.format(this.calculaDiaria());
		return output;
	}
		
}