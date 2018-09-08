package locadora.produto;

import java.text.DecimalFormat;

public class CD extends Musica {
	
	private boolean duplo;
	private boolean arranhado;
	
	public void setDuplo(boolean duplo) {
		
		this.duplo = duplo;
			
	}
	
	public void setArranhado(boolean arranhado) {
	
		this.arranhado = arranhado;
		
	}
		
	public boolean getDuplo() {
	
		return duplo;
		
	}
	
	public boolean getArranhado() {
	
		return arranhado;
		
	}
	
	public double calculaDiaria()
	{
		double preco = 0.0;
		
			   if (this.getDuplo() == false){
                     preco = 2.00;}
               else{
                     preco = 3.50;}
               if(this.getArranhado() == true){
                     preco-=0.4*preco;}
        return preco;
	}
	
	public String toString()
	{
		String output = super.toString();
		output += "\nDuplo: " + this.duplo;
		output += "\nArranhado: " + this.arranhado;
		DecimalFormat doisDigitos=new DecimalFormat("0.00");
		output += "\nVALOR DIARIA: " + doisDigitos.format(this.calculaDiaria());
		return output;
		
	}
	
}