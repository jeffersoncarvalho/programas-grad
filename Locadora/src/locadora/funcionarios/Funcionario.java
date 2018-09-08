package locadora.funcionarios;

import java.util.Hashtable;
import java.io.*;

import locadora.repositorios.RepositorioProdutos;
import locadora.produto.Produto;

public abstract class Funcionario {
	
	public RepositorioProdutos carregarDados()
	{
		RepositorioProdutos  produtos;
		
		try{
	        File file = new File("produtos.dat");
	        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
	        produtos = (RepositorioProdutos) in.readObject();
	        in.close();
	        System.out.println ("Dados carregados com sucesso!");
	    }
	    catch(IOException ioe)
	    {
	    	System.out.println (ioe.toString());
	    	return null;
	    }
	    catch(ClassNotFoundException cnfe)
	    {
	    	System.out.println (cnfe.toString());
	    	return null;
	    }
     
        return produtos;
	}
	
	public abstract Produto procurarPorTitulo(String titulo);	
}
