package locadora.funcionarios;

import java.io.*;

import locadora.repositorios.RepositorioPedidos;
import locadora.repositorios.RepositorioProdutos;
import locadora.produto.Produto;

public class ModuloOperador extends Funcionario
{
	private RepositorioPedidos repPedidosTemp;
    private RepositorioProdutos repProdutosTemp;

	public ModuloOperador() {
		repPedidosTemp = new RepositorioPedidos();
		repProdutosTemp = this.carregarDados() ;
	}
	
	public void abrirNovaLocacao(int idClient, int idProduto,int diarias)
	{
		repPedidosTemp.inserirPedido(idClient,repProdutosTemp.getProduto(idProduto),diarias);
		
	}
		
	public String encerrarLocacao(int idClient)
	{
		String results = "";
		results += repPedidosTemp.listarPedidos(idClient);
		return results;
	}
	
	public Produto procurarPorTitulo(String titulo)
	{
		if(repProdutosTemp != null)
			return repProdutosTemp.getProduto(titulo);
		return null;
	}
	
	public Produto procurarPorID(int id)
	{
		if(repProdutosTemp != null)
			return repProdutosTemp.getProduto(id);
		return null;
	}
}
