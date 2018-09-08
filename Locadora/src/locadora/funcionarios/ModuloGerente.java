package locadora.funcionarios;

import java.io.*;

import locadora.repositorios.RepositorioProdutos;
import locadora.produto.Produto;
import locadora.util.Senha;

public class ModuloGerente extends Funcionario
{
	
	private Senha senha = new Senha("1234");
	private RepositorioProdutos repProdutosTemp;
	private RepositorioProdutos repCarregado;
	
	public ModuloGerente()
	{
		repProdutosTemp = new RepositorioProdutos();
		repCarregado = this.carregarDados();
	}
	
	public void gravarDados()
	{
		
	   if(repCarregado==null)
	    repCarregado = repProdutosTemp;
	   else
	    {
	    	repCarregado.putAll(repProdutosTemp);
	    }
	   
	   try{ 
			ObjectOutput out = new ObjectOutputStream(new
			FileOutputStream("produtos.dat"));
	        out.writeObject(repCarregado);
	        out.close();
	        System.out.println ("Arquivo salvo com sucesso!");
		}
		catch(IOException ioe)
		{
			System.out.println (ioe.toString());
		}
	}
	
	public void addProduto(Produto p)
	{
		this.repProdutosTemp.addProduto(p);
	}
	
	public Produto recuperaProduto(int codigo)
	{
		if(repCarregado != null)
			return repCarregado.getProduto(codigo);
		return null;
	}
	
	public boolean testaSenha(String senha)
	{
		return this.senha.testaSenha(senha);
	}
	
	public Produto procurarPorTitulo(String titulo)
	{
		if(repCarregado != null)
			return repCarregado.getProduto(titulo);
		return null;
	}
}