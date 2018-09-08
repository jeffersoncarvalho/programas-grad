package locadora.gui;

import locadora.produto.*;
import locadora.util.Teclado;

public class AlimentaObjetos
{
	public static VHS alimentaVHS()
	{
		VHS p = new VHS();
		
		System.out.println ("INSERIR PRODUTO VHS\n");
		//Classe Produto
		System.out.print("Digite o codigo: "); 
		p.setCodigo(Teclado.leConsoleInteger());
		System.out.print("Digite titulo: ");
		p.setTitulo(Teclado.leConsole());
		System.out.print ("Digite genero: ");
		p.setGenero(Teclado.leConsole());
		p.setLocado(false);
		
		//Filme
		System.out.print("Digite ano de lancamento: ");
		p.setAno(Teclado.leConsoleInteger());
		System.out.print("Digite duracao: ");
		p.setDuracao(Teclado.leConsoleInteger());
		
		//Classe VHS
		System.out.print ("Cores?(s|n): ");
		if(Teclado.leConsole().equalsIgnoreCase("s"))
			p.setCores(true);
		else
			p.setCores(false);
			
		return p;
	}
	
	public static DVD alimentaDVD()
	{
		DVD p = new DVD();
		
		System.out.println ("INSERIR PRODUTO DVD\n");
		//Classe Produto
		System.out.print("Digite o codigo: "); 
		p.setCodigo(Teclado.leConsoleInteger());
		System.out.print("Digite titulo: ");
		p.setTitulo(Teclado.leConsole());
		System.out.print ("Digite genero: ");
		p.setGenero(Teclado.leConsole());
		p.setLocado(false);
		
		//Filme
		System.out.print("Digite ano de lancamento: ");
		p.setAno(Teclado.leConsoleInteger());
		System.out.print("Digite duracao: ");
		p.setDuracao(Teclado.leConsoleInteger());
		
		System.out.print ("Arranhado?(s|n): ");
		if(Teclado.leConsole().equalsIgnoreCase("s"))
			p.setArranhado(true);
		else
			p.setArranhado(false);
			
		return p;
		
	}
	
	public static LP alimentaLP()
	{
		LP p = new LP();
		
		System.out.println ("INSERIR PRODUTO LP\n");
		//Classe Produto
		System.out.print("Digite o codigo: ");
		p.setCodigo(Teclado.leConsoleInteger());
		System.out.print("Digite titulo: ");
		p.setTitulo(Teclado.leConsole());
		System.out.print ("Digite genero: ");
		p.setGenero(Teclado.leConsole());
		p.setLocado(false);
		
		//Classe Música
		System.out.print("Digite o numero de faixas: ");
		p.setNumFaixas(Teclado.leConsoleInteger());
		System.out.print ("Digite autor: ");
		p.setAutor(Teclado.leConsole());
		
		//Classe LP
		System.out.print ("Raro?(s|n): ");
		if(Teclado.leConsole().equalsIgnoreCase("s"))
			p.setExemplarRaro(true);
		else
			p.setExemplarRaro(false);
			
		return p;
		
		
	}
	
	public static CD alimentaCD()
	{
		CD p = new CD();
		
		System.out.println ("INSERIR PRODUTO CD\n");
		//Classe Produto
		System.out.print("Digite o codigo: ");
		p.setCodigo(Teclado.leConsoleInteger());
		System.out.print("Digite titulo: ");
		p.setTitulo(Teclado.leConsole());
		System.out.print ("Digite genero: ");
		p.setGenero(Teclado.leConsole());
		p.setLocado(false);
		
		//Classe Música
		System.out.print("Digite o numero de faixas: "); 
		p.setNumFaixas(Teclado.leConsoleInteger());
		System.out.print ("Digite autor: ");
		p.setAutor(Teclado.leConsole());
		
		//classe CD
		System.out.print ("Duplo?(s|n): ");
		if(Teclado.leConsole().equalsIgnoreCase("s"))
			p.setDuplo(true);
		else
			p.setDuplo(false);
			
		System.out.print ("Arranhado?(s|n): ");
		if(Teclado.leConsole().equalsIgnoreCase("s"))
			p.setArranhado(true);
		else
			p.setArranhado(false);
		
		return p;
		
	}
}