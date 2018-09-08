package locadora.repositorios;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Enumeration;

import locadora.produto.Produto;

public class RepositorioProdutos extends Hashtable 
{
	public void addProduto(Produto produto)
	{
		this.put(new Integer(produto.getCodigo()),produto);
	}
	
	public Produto getProduto(int codigo)
	{
		
		return (Produto)this.get(new Integer(codigo));
		
	}
	
	public Produto getProduto(String titulo)
	{
		Enumeration valores = this.elements();
		while(valores.hasMoreElements())
		{
			Produto produto = (Produto)valores.nextElement();
			if(produto.getTitulo().equalsIgnoreCase(titulo))
			 return produto;
		}
		
		return null;
	}
}