package locadora.util;

public class Senha {
	
	private String senha;
	
	public Senha(String senha) {
		this.senha = senha;
	}

	
	public void setSenha(String senha) {
		this.senha = senha; 
	}

	public String getSenha() {
		return (this.senha); 
	}
	
	public boolean testaSenha(String senha)
	{
		return (this.senha.equals(senha))? true:false;
	}
		
}
