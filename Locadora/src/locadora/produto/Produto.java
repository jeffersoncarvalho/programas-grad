package locadora.produto;

import java.io.Serializable;


public abstract class Produto implements Serializable{
	
	private int codigo;
	private String titulo;
	private String genero;
	private boolean locado;	
	
	public void setCodigo(int codigo) {
		
		this.codigo = codigo;
		
	}
	
	public void setTitulo(String titulo) {
	
		this.titulo = titulo;
		
	}
	
	public void setGenero(String genero) {
	
		this.genero = genero;
		
	}
	
	public void setLocado(boolean locado) {
	
		this.locado = locado;
		
	}
	
	public String getTitulo() {
	
		return titulo;
		
	}
	
	public String getGenero() {
	
		return genero;
		
	}
	
	public int getCodigo() {
	
		return codigo;
		
	}
	
	public boolean getLocado() {
	
		return locado;
		
	}
	
	public abstract double calculaDiaria();
	
	public String  toString()
	{
		String output = "";
		
		if(this instanceof DVD)
		 output += "\nTipo: DVD";
		else
		 if(this instanceof VHS)
		 	output += "\nTipo: VHS";
		 else
		  if(this instanceof CD)
		 	output += "\nTipo: CD";
		  else
		    output += "\nTipo: LP";
		    
		output += "\nNome: " + this.titulo;
		output += "\nCodigo: " + this.codigo;
		output += "\nGenero: " + this.genero;
		output += "\nLocado: " + this.locado;
		return output;
	}
}