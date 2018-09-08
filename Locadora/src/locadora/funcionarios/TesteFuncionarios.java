package locadora.funcionarios;

import locadora.produto.*;
import locadora.repositorios.RepositorioProdutos;

public class TesteFuncionarios {
	public static void main(String[] args) {
		ModuloGerente gerente = new ModuloGerente();
		
		/*VHS vhs = new VHS();
		vhs.setAno(2003);
		vhs.setCodigo(1540);
		vhs.setCores(true);
		vhs.setDuracao(230);
		vhs.setGenero("Luta");
		vhs.setLocado(true);
		vhs.setTitulo("Matrix Revolutions 1");
		gerente.addProduto(vhs);
		gerente.gravarDados();*/
		
		/*CD cd = new CD();
		cd.setArranhado(true);
		cd.setAutor("Jefferson");
		cd.setCodigo(2301);
		cd.setDuplo(true);
		cd.setNumFaixas(20);
		cd.setGenero("Pop");
		cd.setTitulo("Rock´n´roll");
		gerente.addProduto(cd);
		gerente.gravarDados();*/
		
		
		
		System.out.println (gerente.recuperaProduto(2301));
	}	
}
