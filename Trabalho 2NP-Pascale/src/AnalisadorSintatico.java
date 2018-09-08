import java.util.*;
import java.io.*;


public class AnalisadorSintatico implements ConstantsAnalex
{
	private ArrayList alTokens;
	private int contador;
	private boolean codigoErrado;
	
	public AnalisadorSintatico()
	{
		alTokens=new ArrayList();
		codigoErrado=false;
		contador=0;
			
	}//construtor
	
	private String stringToken()
	{
		StringValor sv=(StringValor)alTokens.get(contador);
		return sv.getString();
		
	}//string do token
	
	private byte valorToken()
	{
		StringValor sv=(StringValor)alTokens.get(contador);
		return sv.getValor();
	}//valor do token-->ver classe de constantes
	
	private void erro(String mensagem)
	{
		
		
		codigoErrado=true;
		System.out.println("ERROR: "+mensagem+" expected.\n");
		System.exit(0);
	}
	
	private void incrementaContador()
	{
		if((contador+1)!=alTokens.size())
			contador++;
		else
		{
			StringValor trap=new StringValor("#",OTHER);
			alTokens.add(trap);
			contador++;
		}
	}
	
	public void analisaSintaticamente(String codigo)
	{
		Analex alex=new Analex();
		alTokens=alex.analisaLexicamente(codigo);//devolve todos os meus tokens.
		programa();//vor ver se tem erros.
	}
	
	//**************************************************************************
						//MÉTODOS REFERENTES AO NÃO TERMINAIS//
	//**************************************************************************
	
	private void programa()
	{
		if(stringToken().equals("program"))
		{
			incrementaContador();
			if(valorToken()==IDE)
			{
				incrementaContador();
				if(stringToken().equals("("))
				{
					incrementaContador();
					lista_de_identificadores();
					if(stringToken().equals(")"))
					{
						incrementaContador();
						if(stringToken().equals(";"))
						{
							incrementaContador();
							declaracoes();
							declaracoes_de_subprogramas();
							enunciado_composto();
							if(stringToken().equals("."))
							{
							 incrementaContador();
							 if(stringToken().equals("#"))
							 	System.out.println("Process completed whithout errors.");
							 else
							 {
							 	System.out.println("Process completed whithout errors.");
							 	System.out.println("Warning: text after 'end.' will be ignored by Pascale Compiler.");
							 }
							}//.
							else
							 erro("'.'");
						}//;
						else
						 erro("';'");
					}//)
					else
					 erro("')'");
				}//(
				else
				 erro("'('");
			}//IDE
			else
			 erro("identifier");
		}//program
		else
		 erro("'program'");
		
	}//metodo programa
	
	private void lista_de_identificadores()
	{
		if(valorToken()==IDE)
		{
			incrementaContador();
			lista_de_identificadores_1();
		}//IDE
		else
		 erro("identifier");
		 
	}//método lista_de_identificadores
	
	private void lista_de_identificadores_1()
	{
		if(stringToken().equals(","))
		{
			incrementaContador();
			if(valorToken()==IDE)
			{
				incrementaContador();
				lista_de_identificadores_1();
			}//IDE
			else
			 erro("identifier");
		}//, não obrigado
		
	}//método lista_de_identificadores 
	
	private void declaracoes()
	{
		declaracoes_1();
	}//método declaracoes
	
	private void declaracoes_1()
	{
		if(stringToken().equals("var"))
		{
			incrementaContador();
			lista_de_identificadores();
			if(stringToken().equals(":"))
			{
				incrementaContador();
				tipo();
				if(stringToken().equals(";"))
				{
					incrementaContador();
					declaracoes_1();
				}//;
				else
				 erro("';'");
			}//:
			else
			 erro("':'");
		}//var não obrigado
		
	}//método declaracoes_1
	
	private void tipo()
	{
		if(stringToken().equals("array"))
		{
			incrementaContador();
			if(stringToken().equals("["))
			{
				incrementaContador();
				if(valorToken()==NUM)
				{
					incrementaContador();
					if(stringToken().equals("."))
					{
					 incrementaContador();
					  if(stringToken().equals("."))
					  {
					  	incrementaContador();
					  	if(valorToken()==NUM)
					  	{
					  		incrementaContador();
					  		if(stringToken().equals("]"))
					  		{
					  			incrementaContador();
					  			if(stringToken().equals("of"))
					  			{
					  			 incrementaContador();
					  			 tipo_padrao();
					  			}//of
					  			else
					  			 erro("'of'");		 
					  		}//]
					  		else
					  		 erro("']'");
					  	}//NUM
					  	else
					  	 erro("number");
					  }//.
					  else
					   erro("'.'");
					}//.
					else
					 erro("'.'");
				}//NUM
				else
				 erro("number");
			}//[
			else
			 erro("'['");
		}//array
		else
		 tipo_padrao();
	}//metodo tipo
	
	private void tipo_padrao()
	{
		if(stringToken().equals("integer"))
		 incrementaContador();
		else
		 if(stringToken().equals("real"))
		  incrementaContador();
		 else
		  erro("integer or real");
	}//método tipo_padrao
	
	private void declaracoes_de_subprogramas()
	{
		declaracoes_de_subprogramas_1();	
	}//método declaracoes_de_subprogramas
	
	private void declaracoes_de_subprogramas_1()
	{
				if(stringToken().equals("procedure") || stringToken().equals("function"))
          		{
          			//não incremente!!!
                    declaracao_de_subprograma();
                    if(stringToken().equals(";"))
                    {
						incrementaContador();
                        declaracoes_de_subprogramas_1();
                    }//;
                    else
                     erro("';'");
                 }//não sor obrigado a escrever subprogramas
	}//método declaracoes_de_subprogramas_1()
	
	private void declaracao_de_subprograma()
	{
		cabecalho_de_subprograma();
		declaracoes();
		enunciado_composto();
	}//método declaracao_de_subprograma
	
	private void cabecalho_de_subprograma()
	{
		if(stringToken().equals("function"))
		{
			incrementaContador();
			if(valorToken()==IDE)
			{
				incrementaContador();
				argumentos();
				if(stringToken().equals(":"))
				{
					incrementaContador();
					tipo_padrao();
					if(stringToken().equals(";"))
					{
						incrementaContador();
					}//;
					else
					 erro("';'");
				}//:
				else
				 erro("':'");
			}//IDE	
			else
			 erro("identifier");
		}//function
		else
		 if(stringToken().equals("procedure"))
		 {
			incrementaContador();
			if(valorToken()==IDE)
			{
				incrementaContador();
				argumentos();
				if(stringToken().equals(";"))
				{
					incrementaContador();
				}//;
				else
				 erro("';'");
			}//IDE	
			else
			 erro("identifier");
		 }//procedure function
		 else
		  erro("'procedure' or 'function'");		
		
	}//método cabecalho_de_subprograma()
	
	private void argumentos()
	{
	  if(stringToken().equals("("))
	  {
	  	incrementaContador();
	  	lista_de_parametros();
	  	if(stringToken().equals(")"))
	  	{
	  		incrementaContador();
	  	}
	  	else
	  	 erro("')'");
	  	
	  }//( não é obrigado
	  
	}//método argumentos
	
	private void lista_de_parametros()
	{
		lista_de_identificadores();
		if(stringToken().equals(":"))
		{
			incrementaContador();
			tipo();
			lista_de_parametros_1();
		}
        else
          erro("':'");
	}//método lista_de_parametros()
	
	private void lista_de_parametros_1()
	{
		if(stringToken().equals(";"))
		{
			incrementaContador();
			lista_de_identificadores();
			if(stringToken().equals(":"))
			{
				incrementaContador();
				tipo();
				lista_de_parametros_1();
			}
			else
			 erro("':'");
		}//; não sor obrigado
		
		
	}//método lista_de_parametros_1
	
	private void enunciado_composto()
	{
		if(stringToken().equals("begin"))
		{
			incrementaContador();
			enunciados_opcionais();
			if(stringToken().equals("end"))
			{
				incrementaContador();
			}
			else
			 erro("'end'");
		}//begin
		else
			erro("'begin'");
			
	}//método enunciado_composto
	
	private void enunciados_opcionais()
	{
        //através do if abaixo eu sei se o código  que entrar em lista_de_enunciados or não indiretamente por enunciado
        if((stringToken().equals("if")) || (stringToken().equals("while")) || (stringToken().equals("begin")) || (valorToken()==IDE))
		{ 
		 lista_de_enunciados();
		}
	}//metodo enunciados_opcionais
	
	private void lista_de_enunciados()
	{
		enunciado();
		lista_de_enunciados_1();	
	}//metodo lista_de_enunciados

	private void lista_de_enunciados_1()
	{
		if(stringToken().equals(";"))
		{
			incrementaContador();
			enunciado();
			lista_de_enunciados_1();
			
		}//; não sor obrigado
		
	}//método lista_de_enunciados_1	
	
	private void enunciado()
	{
     	if(stringToken().equals("if"))
     	{
     		incrementaContador();
     		expressao();
     		if(stringToken().equals("then"))
     		{
     			incrementaContador();
     			enunciado();
     			if(stringToken().equals("else"))
     			{
     				incrementaContador();
     				enunciado();
     			}//else
     			else
     			 erro("'else'");
     			
     		}//then
     		else
     		 erro("'then'");
     		
     	}//if
     	else
     		if(stringToken().equals("while"))
     		{
     			incrementaContador();
     			expressao();
     			if(stringToken().equals("do"))
     			{
     				incrementaContador();
     				enunciado();
     			}//do
     			else
     			 erro("'do'");	
     		}//while if
     		else
     			if(stringToken().equals("begin"))
     			{
     				//não incremente o contador!!!
     				enunciado_composto();
     			}//begin while if
     			else
     			if(valorToken()==IDE)//Aqui tem brincadeira...
     			{
     				incrementaContador();
     				//agora posso ter uma das 3 opçoes abaixo or nenhuma...
     				if(valorToken()==OAT)
     				{
     					incrementaContador();
     					expressao();
     				}//OAT mas não sor obrigado
     				else
     				if(stringToken().equals("["))
     				{
     					incrementaContador();
     					expressao();
     					if(stringToken().equals("]"))
     					{
     						incrementaContador();
     						if(valorToken()==OAT)
     						{
     							incrementaContador();
     							expressao();
     						}
     						else
     						erro("addction operator ");
     					}//];
     					else
     						erro("']'");
     					
     				}//[ mas não sor obrigado
     				else
     				if(stringToken().equals("("))
     				{
     					incrementaContador();
     					lista_de_expressoes();
     					if(stringToken().equals(")"))
     					{
     						incrementaContador();
     					}
     					else
     					 erro("')'");
     				}//( mas não sor obrigado 
     					 			
     			}//IDE begin while if(or eu to na variavel or numa chamada de procedimento)
     			else
     			 erro("identifier or 'begin' or 'while' or ''if'");
     			   	       
	}//método enunciado. kRalho!Pòo esse foi phoda!
	
	private void lista_de_expressoes()
	{
		expressao();
		lista_de_expressoes_1();
	}//método lista_de_expressoes
	
	private void lista_de_expressoes_1()
	{
		if(stringToken().equals(","))
	    {
	    	incrementaContador();
			expressao();
			lista_de_expressoes_1();
		}
	}//método lista_de_expressoes_1
	
	private void expressao()
	{
		expressao_simples();
		if(valorToken()==OR)
		{
			incrementaContador();
			expressao_simples();
			
		}//não sor obrigado a ler um OR. posso apenas executar apenas expressao simples.
	}//método expressao
	
	private void expressao_simples()
	{
		/*if(valorToken()==OA)
		{
			incrementaContador();
			termo();
			expressao_simples_1();		
		}
		else
		 if(stringToken().equals("-") || stringToken().equals("+"))//leu signal
		 {
		 	incrementaContador();
		 	termo();
		 }
		 else
		  	termo();//termo chama fator e lá é que eu dor o erro
		  	
		*/
		
		//wendel
		if(stringToken().equals("-") || stringToken().equals("+"))
		{
			incrementaContador();	
			termo();
			if(valorToken()==OA)
			{
					//não incrmente!!tor tentando ler exp'
					expressao_simples_1();
			}//não sor obrigado		
					
		}
		else
		 if(valorToken()==IDE || valorToken()==NUM || stringToken().equals("(") || stringToken().equals("not"))
		 {
		 	//não incremente, estor tentando ler termo
		 	termo();
		 	if(valorToken()==OA)
			{
					//não incrmente!!tor tentando ler exp'
					expressao_simples_1();
			}//não sor obrigado
			
		 }
		 else
		  erro("signal or number or ide. or '(' or 'not'");
		
		  
	}//método expressão simples
	
	private void expressao_simples_1()
	{
		if(valorToken()==OA)
		{
			incrementaContador();
			termo();
			if(valorToken()==OA)
			 expressao_simples_1();//não sor obrigado		
		}
		else
		 erro("addction operator");
		
	}//expressao_simples		
	
	private void termo()
	{
		fator();
		termo_1();
		
	}//método termo
	
	private void termo_1()
	{
		if(valorToken()==OM)
		{
			incrementaContador();
			fator();
		}//não sor obrigado a ler um OM
	}// método termmo_1
	
	private void fator()
	{
		if(valorToken()==IDE)
		{
			
			incrementaContador();
			if(stringToken().equals("("))
			{
				incrementaContador();
				lista_de_expressoes();
				if(stringToken().equals(")"))
				{
					incrementaContador();
				}
				else
				 erro("')'");
			}//não sor obrigado a ler um (.
		}
		else //IDE		
		   if(valorToken()==NUM)
		   {
			 incrementaContador();
		   }
		   else //NUM IDE
				if(stringToken().equals("("))
				{
					
					incrementaContador();
					expressao();
					if(stringToken().equals(")"))
					 incrementaContador();
					else
					 erro("')'");
				}
				else //'(' NUM IDE
					if(stringToken().equals("not"))
					{
					
						incrementaContador();
						fator();
					}//not '(' NUM IDE
					else
					{
					 erro("not or '('  or number  or identifier or signal or addction operator");	
		   			}
		
	}//método fator
		
}