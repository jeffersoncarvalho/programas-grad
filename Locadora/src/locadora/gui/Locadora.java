/*
 * @(#)Locadora.java 1.0 04/05/19
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_1\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */
package locadora.gui;


class Locadora {
	
	
	public static String telaGerente()
	{
		String output = "";
		output += "\n1 - Cadastrar novo filme";
		output += "\n2 - Cadastrar novo album musical";
	    output += "\n3 - Procurar por titulo";
		output += "\n4 - Iniciar modo de operador";
		output += "\n5 - Encerrar sistema ";
		return output;
	}
	
	public static String telaOperador()
	{
		String output = "";
		output += "\n1 - Abrir nova locacao";
		output += "\n2 - Encerrar locacao";
		output += "\n3 - Procurar por titulo"; 
		output += "\n4 - Encerrar modo de operador"; 
		return output;
	}	
	
	public static String telaCadastrarFilme() 
	{
		String output = "";
		output += "\n1 - VHS";
		output += "\n2 - DVD";
		output += "\n3 - Finalizar cadastro"; 
		return output;
	}
	
	public static String telaCadastrarAlbumMusical() 
	{
		String output = "";
		output += "\n1 - CD";
		output += "\n2 - LP";
		output += "\n3 - Finalizar cadastro"; 
		return output;
	}
	
	public static String telaAbrirLocacao() 
	{
		String output = "";
		output += "\n1 - Inserir Filme";
		output += "\n2 - Inserir Album";
		output += "\n3 - Concluir nova locacao"; 
		return output;
	}
	
	public static String telaAlugarFilme() 
	{
		String output = "";
		output += "\n1 - VHS";
		output += "\n2 - DVD";
		output += "\n3 - Finalizar secao"; 
		return output;
	}
	
	public static String telaAlugarAlbum() 
	{
		String output = "";
		output += "\n1 - CD";
		output += "\n2 - LP";
		output += "\n3 - Finalizar secao"; 
		return output;
	}
	
	public static void limpaTela() 
	{
		for(int i = 0; i < 24; i++)
			System.out.println();
	}
	
}
