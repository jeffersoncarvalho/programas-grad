package locadora.produto;

public abstract class Filme extends Produto {

	private int duracao;
	private int anoLancamento;
	
	public void setDuracao(int duracao) {
		
		this.duracao = duracao;
		
	}
	
	public void setAno(int anoLancamento) {
		
		this.anoLancamento = anoLancamento;
		
	}
	
	
	public int getDuracao() {
	
		return duracao;
		
	}
	
	public int getAnoLancamento() {
	
		return anoLancamento;
		
	}
	
	public abstract double calculaDiaria();
	
	public String toString()
	{
		String output = super.toString();
		output += "\nDuracao: " + this.duracao;
		output += "\nAno do lancamento: " + this.anoLancamento; 
		return output;
	}
	
}