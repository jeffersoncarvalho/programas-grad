package locadora.repositorios;

import java.util.Hashtable;
import java.util.Enumeration;
import java.text.DecimalFormat;

import locadora.produto.Produto;

public class RepositorioPedidos extends Hashtable{
	public RepositorioPedidos() {
		
	}
	
	public void inserirPedido(int idClient, Produto produto, int diarias)
	{
		Pedido pedido = new Pedido(idClient,produto.getCodigo());
		pedido.setDiarias(diarias);
		this.put(pedido,produto);
	}
	
	public void removerPedido(int idClient, int idProduto)
	{
		this.remove(new Pedido(idClient,idProduto));
	}
	
	public String listarPedidos(int idClient)
	{
		
		double total = 0.0;
		String output = "";
		DecimalFormat doisDigitos=new DecimalFormat("0.00");
		Enumeration chaves = this.keys();
		int i =1;
		while(chaves.hasMoreElements())
		{
		
			Pedido pedido = (Pedido)chaves.nextElement();
			if(pedido.getIdClient() == idClient)
			{
		
				output += "\n\nPEDIDO NUMERO "+i;
				Produto produto = (Produto)this.get(pedido); 
				output += produto;
				double conta = pedido.getDiarias()*produto.calculaDiaria();
				total +=conta;
				output += "\nDIARIAS: " + pedido.getDiarias();
				output += "\nA PAGAR: " + doisDigitos.format(conta);
				i++;
			}
		}
		output += "\n\nTOTAL A PAGAR: " + doisDigitos.format(total);
		return output;
	}
	
		
}


class Pedido
{
	private int idClient;
	private int idProduto;
    private int diarias;

	public Pedido(int idClient, int idProduto) {
		this.idClient = idClient;
		this.idProduto = idProduto;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient; 
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto; 
	}

	public void setDiarias(int diarias) {
		this.diarias = diarias; 
	}

	public int getIdClient() {
		return (this.idClient); 
	}

	public int getIdProduto() {
		return (this.idProduto); 
	}

	public int getDiarias() {
		return (this.diarias); 
	}
	
	public boolean equals(Object pedido)
	{
		Pedido p = (Pedido)pedido;
		if( (p.getIdClient() == this.getIdClient()) &&
		    (p.getIdProduto() == this.getIdProduto())
		  )
		  return true;
		return false;
	}
	
	public String toString() {

		String sep = System.getProperty("line.separator");

		StringBuffer buffer = new StringBuffer();
		buffer.append(sep);
		buffer.append("idClient = ");
		buffer.append(idClient);
		buffer.append(sep);
		buffer.append("idProduto = ");
		buffer.append(idProduto);
		buffer.append(sep);
		buffer.append("diarias = ");
		buffer.append(diarias);
		buffer.append(sep);
		
		return buffer.toString();
	}

	
}
