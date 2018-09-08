package locadora.gui;

import locadora.util.Teclado;
import locadora.funcionarios.ModuloGerente;
import locadora.funcionarios.ModuloOperador;
import locadora.produto.Produto;
import locadora.gui.Locadora;
import locadora.gui.AlimentaObjetos;

public class InterfaceLocadora
{
	static ModuloGerente gerente;
	static ModuloOperador operador;
	
	public static void main(String args[])
	{    
		gerente = new ModuloGerente();
		
		
		String minhaSenha;
		do
		{
			System.out.print("Gerente, digite a senha do sistema: ");
			minhaSenha = Teclado.leConsole();
		}while(!gerente.testaSenha(minhaSenha));
		System.out.println ("Senha correta!!");
		//delay(2000);
		
		//iniciando a o modo de Gerente
		int opcao;
		do{
			System.out.print(Locadora.telaGerente());
			System.out.print ("\n\nDigite a opcao: ");
			opcao = Teclado.leConsoleInteger();
			//switch da tela do gerente
			switchTelaGerente(opcao);
		}while(opcao < 4);
		
		//iniciando modo de operador
		if(opcao == 4)
		{
			operador = new ModuloOperador();
			do{
			System.out.print(Locadora.telaOperador());
			System.out.print ("\n\nDigite a opcao: ");
			opcao = Teclado.leConsoleInteger();
			//switc tela do operador
			switchTelaOperador(opcao);
			}while(opcao < 4);
		}
		
	}
	
	//---------------------------GERENTE-------------------------------
	
	public static void switchTelaGerente(int opcao)
	{
		switch(opcao)
		{
			case 1:
			 //cadastrar novo filme
			 cadastrarFilme();
			break;
			case 2:
			 //cadastar novo álbum de música
			 cadastrarAlbum();
			break;
			case 3:
			 //procurar por título
			 pesquisarTitulo();
			break;
		}
			
	}
	
	public static void cadastrarFilme()
	{
		int opcao;
		do
		{
			System.out.print(Locadora.telaCadastrarFilme());
			System.out.print ("\n\nDigite a opcao: ");
			opcao = Teclado.leConsoleInteger();
			//switch telaCadastrarFilme
			switchTelaCadastrarFilme(opcao);
		}while(opcao < 3);
		gerente.gravarDados();
	}
	
	public static void switchTelaCadastrarFilme(int opcao)
	{
		
		switch(opcao)
		{
			case 1:
			 //VHS
			 gerente.addProduto(AlimentaObjetos.alimentaVHS());
			break;
			case 2:
			 //DVD
			 gerente.addProduto(AlimentaObjetos.alimentaDVD());
			break;
		}	
		
	} 
	
	public static void cadastrarAlbum()
	{
		int opcao;
		do
		{
			System.out.print(Locadora.telaCadastrarAlbumMusical());
			System.out.println ("\n\nDigite opcao: ");
			opcao = Teclado.leConsoleInteger();
			//switch telaCadastrarAlbumMusical
			switchTelaCadastrarAlbum(opcao);
		}while(opcao < 3);
		gerente.gravarDados();
	}
	
	public static void switchTelaCadastrarAlbum(int opcao)
	{
		
		switch(opcao)
		{
			case 1:
			 //CD
			 gerente.addProduto(AlimentaObjetos.alimentaCD());
			break;
			case 2:
			 //LP
			 gerente.addProduto(AlimentaObjetos.alimentaLP());
			break;
		}	
		
	}
	
	//-------------------------OPERADOR----------------------------------
	
	public static void switchTelaOperador(int opcao)
	{
		switch(opcao)
		{
			case 1:
			 //Abrir nova locação
			 abrirNovaLocacao();
			break;
			case 2:
			 //encerrar locação/
			 System.out.print ("Digite o codigo do cliente: ");
			 System.out.println (operador.encerrarLocacao(Teclado.leConsoleInteger()));
			break;
			case 3:
			 //procurar por título
			 pesquisarTitulo();
			break;
		}
			
	}
	
	public static void abrirNovaLocacao()
	{
		int opcao;
		do
		{
			System.out.print(Locadora.telaAbrirLocacao());
			System.out.print ("\n\nDigite a opcao: ");
			opcao = Teclado.leConsoleInteger();
			//switch telaAbrirLocacao
			switchAbrirLocacao(opcao);
		}while(opcao < 3);
		
	} 
	
	public static void switchAbrirLocacao(int opcao)
	{
		switch(opcao)
		{
			case 1:
			 //Filme
			  System.out.println ("ABRIR LOCACAO DE FILME");
			  inserirPedido();
			break;
			case 2:
			 //Musica
			 System.out.println ("ABRIR LOCACAO DE ALBUM");
			 inserirPedido();
			break;
		}
	}
	
	public static void inserirPedido()
	{
		System.out.print ("\nDigite o codigo do cliente: ");
		int idCliente = Teclado.leConsoleInteger();
		System.out.print ("\nDigite o codigo do produto: ");
		int idProduto = Teclado.leConsoleInteger();
		if(operador.procurarPorID(idProduto) == null)
		 System.out.println ("Produto inexistente!");
		else
		{
			System.out.print ("\nDigite a qtd de diarias: ");
			int diarias = Teclado.leConsoleInteger();
			operador.abrirNovaLocacao(idCliente,idProduto,diarias);
			System.out.println ("Locacao inserida");
		}
		
	}
	
	
	
	//---------------------------COMUM--------------------------------------
	
	public static void pesquisarTitulo()
	{
		System.out.print("Digite titulo do produto: ");
		String titulo = Teclado.leConsole();
		Produto produto = gerente.procurarPorTitulo(titulo);
		if(produto == null)
		 System.out.println ("Produto inexistente!");
		else
		 System.out.println (produto);
	} 
	
	
}