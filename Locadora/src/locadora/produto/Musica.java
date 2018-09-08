package locadora.produto;


public abstract class Musica extends Produto {

	
	private int numFaixas;
	private String autor;
	
	
	
	public void setNumFaixas(int numFaixas) {
	
		this.numFaixas = numFaixas;
		
	}
	
	public void setAutor(String autor) {
	
		this.autor = autor;
		
	}
	
	public String getAutor() {
	
		return autor;
		
	}
	
	public int getNumeroFaixas() {
	
		return numFaixas;
		
	}
	
	public abstract double calculaDiaria();
	
	public String toString()
	{
		
		String output = super.toString();
		output += "\nNumero de faixas: " + this.numFaixas;
		output += "\nAutor: " + this.autor;
		return output;
	}
	
}