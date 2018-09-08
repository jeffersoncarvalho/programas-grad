
import java.util.*;

interface ConstantsAnalex
{
	public static final byte PR=0;//palavra reservada
	public static final byte OA=1;//operador adi��o
	public static final byte OM=2;//operador multiplica��o
	public static final byte OD=3;//operador divis�o
	public static final byte OR=4;//operador relacional
	public static final byte OAT=5;//operador atribui��o
	public static final byte IDE=6;//identificador
	public static final byte NUM=7;//n�mero
	public static final byte OTHER=8;//outro	
}

class StringValor implements ConstantsAnalex
{
	private String string;
	private byte valor;
	
	public StringValor(String str, byte val)
	{
		string = str;
		valor = val;
	}
	public void setValor(byte val)
	{
		valor=val;
	}
	public void setString(String str)
	{
		string=str;
	}
	
	public byte getValor()
	{
		return valor;
	}
	
	public String getString()
	{
		return string;
	}
	
	public String toString()
	{
		String output="";
		switch (valor)
		{
			case PR:
			 output+= string+" :palavra reservada";
			break;
			case OA:
			 output+= string+" :operador de adi��o";
			break;
			case OM:
			 output+= string+" :operador de multiplica��o";
			break;
			case OD:
			 output+= string+" :operador de multiplica��o";
			break;
			case OR:
			 output+= string+" :operador relacional";
			break;
			case OAT:
			 output+= string+" :operador de atribui��o";
			break;
			case IDE:
			 output+= string+" :identificador";
			break;
			case NUM:
			 output+= string+" :n�mero";
			break;
			case OTHER:
			 output+= string+" :s�mbolo";
			break;
		}
		
		return output;
	}
}

public class Analex implements ConstantsAnalex
{
 
  
  PalavrasReservadas pr;
  ArrayList alTokens;
   

  
  public Analex()
  {
  	alTokens=new ArrayList();
    pr=new PalavrasReservadas();	
  }
  
  public void quebraCad(String cad)
  {
    String numString="",nomeString="",ideString="";
    
    
    cad+='\35';
    
    for(int i=0;i<cad.length()-1;i++)
    {
	//trato de sinais:
	if(/*(cad.charAt(i)=='+')||*/(cad.charAt(i)=='-'))
         if(i+1!=cad.length())
          if(Character.isDigit(cad.charAt(i+1)))
	  {
		numString+=cad.charAt(i);
		i++;
	  }


	

	if(Character.isLetter(cad.charAt(i)))//ids
	{
		ideString+=cad.charAt(i++);
		
		while(Character.isLetterOrDigit(cad.charAt(i)))
                {
                    ideString+=cad.charAt(i);
                    i++;
                }
		
		  if(pr.pertenceReservada(ideString))
		  {

		    StringValor token=new StringValor(ideString,PR);
		    alTokens.add(token);
		  }
		  else
		   if(pr.pertenceOpMultiplicacao(ideString))
		   {
		   
		    StringValor token=new StringValor(ideString,OM);
		    alTokens.add(token);
		   }
		   else
		    if(ideString.equalsIgnoreCase("or"))
		    {
		   
		     StringValor token=new StringValor(ideString,OA);
		     alTokens.add(token);
		    }
		    else
		    {

		  		StringValor token=new StringValor(ideString,IDE);
		    	alTokens.add(token);
		  	}
		  ideString="";
		  i--;
	}//if do teste de identificador
	else
         if(Character.isDigit(cad.charAt(i)))
         {
	   do{
		 numString+=cad.charAt(i);
		 i++;
	   }while(Character.isDigit(cad.charAt(i)));
          
           //teste de numero fracion�rio e exponencial
           switch(cad.charAt(i))
           {
               case '.':
                   if(Character.isDigit(cad.charAt(i+1)))
                   {
                       numString+=cad.charAt(i);
                       i++;
                       while(Character.isDigit(cad.charAt(i)))
                       {
                           numString+=cad.charAt(i);
                           i++;
                       }
                       //acabou o fracion�rio? Testo se existe exponecial
                       if((cad.charAt(i)=='E'))
                       {
                           //expoente sem sinal
                           if(Character.isDigit(cad.charAt(i+1)))
                           {
                               numString+='E';
                               i++;
                            while(Character.isDigit(cad.charAt(i)))
                            {
                                numString+=cad.charAt(i);
                                i++;
                            }
                           }
                           else
                           //expoente com sinal
                           if((cad.charAt(i+1)=='+')||(cad.charAt(i+1)=='-'))
                               if(Character.isDigit(cad.charAt(i+2)))
                               {
                                   numString+='E';
                                   
                                   numString+=cad.charAt(++i);//recebo sinal
                                   while(Character.isDigit(cad.charAt(++i)))
                                   {
                                        numString+=cad.charAt(i);
                                      //  i++;
                                   }
                                   
                               }
                                   
                       }
                       
                   }
                   
               break;//break do ponto, caso o n�mero seja fracion�rio.
               
               case 'E':
                   //expoente sem sinal
                    if(Character.isDigit(cad.charAt(i+1)))
                    {
                        numString+='E';
                        i++;
                        while(Character.isDigit(cad.charAt(i)))
                        {
                                numString+=cad.charAt(i);
                                i++;
                        }
                    }
                    else
                     //expoente com sinal
                           if((cad.charAt(i+1)=='+')||(cad.charAt(i+1)=='-'))
                               if(Character.isDigit(cad.charAt(i+2)))
                               {
                                   numString+='E';
                                   
                                   numString+=cad.charAt(++i);//recebe o sinal
                                   
                                    while(Character.isDigit(cad.charAt(++i)))
                                    {
                                        numString+=cad.charAt(i);
                                       
                                    }
                                   
                               }
                   
               break;
               
           }//switch
        
   

	   StringValor token=new StringValor(numString,NUM);
	   alTokens.add(token);
	   numString="";
	   i--; 	
	 }//if dos n�meros
	 else
         {
	     boolean sai=false;
	     do
	     {
		 nomeString+=cad.charAt(i);
		 i++;
	        if((i!=cad.length()-1))
		 if(Character.isDigit(cad.charAt(i+1)) && ((cad.charAt(i)=='+') || (cad.charAt(i)=='-')))
			sai=true;

	     }while(!Character.isLetterOrDigit(cad.charAt(i)) && (i!=cad.length()-1) && !sai);
	   

	   
	   for(int z=0;z<nomeString.length();z++)
       {
          switch(nomeString.charAt(z))
          {
          	case ':':
          	 if(((z+1)!=nomeString.length()) && (nomeString.charAt(z+1)=='='))
          	 {
          	  
          	  StringValor token=new StringValor(":=",OAT);
		      alTokens.add(token);
          	  z++;
        	 }
        	 else
        	 {
        	  
        	  StringValor token=new StringValor(Character.toString(nomeString.charAt(z)),OTHER);
		      alTokens.add(token);
		     } 
          	break;
          	
          	case '=':
          	  if(nomeString.charAt(z)=='=')
          	  { 
          	  	StringValor token=new StringValor("=",OR);
		      	alTokens.add(token);
		  	  }
          	break;
          	
          	case '>':
          	 if(((z+1)!=nomeString.length()) && (nomeString.charAt(z+1)=='='))
          	 {
          	  
          	  StringValor token=new StringValor(">=",OR);
		      alTokens.add(token);
          	  z++;
        	 }
        	 else
        	 {
        	  
        	  StringValor token=new StringValor(">",OR);
		      alTokens.add(token);
        	 }
        	break;
        	
        	case '<':
          	 if(((z+1)!=nomeString.length()) && (nomeString.charAt(z+1)=='='))
          	 {
          	  
          	  StringValor token=new StringValor("<=",OR);
		      alTokens.add(token);
          	  z++;
        	 }
        	 else
        	  if(((z+1)!=nomeString.length()) && (nomeString.charAt(z+1)=='>'))
          	  {
          	   
          	   StringValor token=new StringValor("<>",OR);
		       alTokens.add(token);
          	   z++;
        	  }
        	  else
        	  {
        	   
        	   StringValor token=new StringValor("<",OR);
		       alTokens.add(token);
        	  }
        	break;
        	
        	case '+': case '-':
        	 if(nomeString.charAt(z)=='+' || nomeString.charAt(z)=='-')
        	 {
        	 	
        	 	StringValor token=new StringValor(Character.toString(nomeString.charAt(z)),OA);
		     	alTokens.add(token);
		  	 }
        	break;
        	
        	case '*':case '/':
        	if(nomeString.charAt(z)=='*' || nomeString.charAt(z)=='/')
        	 {
        	 	
        	 	StringValor token=new StringValor(Character.toString(nomeString.charAt(z)),OM);
		     	alTokens.add(token);
		     }
        	break;
        	
        	default:
        		
        		StringValor token=new StringValor(Character.toString(nomeString.charAt(z)),OTHER);
		        alTokens.add(token); 
        	break;
          }//switch
          
       }//for
	   
	   
	    nomeString="";
	    i--;
	   
	  }
     }//for
	
  }//m�todo
  
  public ArrayList analisaLexicamente(String texto)
  {
  	StringTokenizer tokens=new StringTokenizer(texto);//o texto  ser� quebrado em tokens para analisa��o individual.
    alTokens.clear();
    
    while(tokens.hasMoreTokens())
    	quebraCad(tokens.nextToken());
	
    return alTokens;
      
  }
  

  
	
}