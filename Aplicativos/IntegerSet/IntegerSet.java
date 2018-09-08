package br.unifor.jefferson;
/******************
1º passo: defino o nome do meu pacote.
2º crio a pasta "classes" no diretório
   c:\diretoriodojava\jre. logo ficaria:
   c:\diretoriodojava\jre\classes
3º compilo a minha classe IntegerSet com
   javac -d c:\diretoriodojava\jre\classes IntegerSet.java
   desta forma o IntegerSet.class ficará no diretório do classes;
4º Finalmente eu importo a classe Integer set para o aplicativo que
   quero usá-la. Neste caso será o testSet.java .
*******************/
public class IntegerSet extends Object
{
  private boolean[] elements;
  private int size;
  
  public IntegerSet()
  {
	elements=new boolean[101];
        size=0;
  }
  public void unionOfIntegerSets(IntegerSet obj)
  {
	for(int i=0;i<=100;i++)
          if(obj.elements[i])
	   elements[i]=true;
	 
  }
  public void intersectionOfIntegerSets(IntegerSet obj)
  {
	for(int i=0;i<=100;i++)
          if(obj.elements[i] && elements[i])
	    elements[i]=true;
	  else
	    elements[i]=false;
	
  }
  public void insertElement(int x)
  {
	if(x>=0 && x<=100)
	 elements[x]=true;
        size++;
  }
  public void deleteElement(int x)
  {
	if(x>=0 && x<=100)
	 elements[x]=false;
	size--;
  }
  public boolean isEqualTo(IntegerSet obj)
  {
	boolean equal=true;

	if(obj.size!=size)
	  return false;

	for(int i=0;( i<=100 && equal!=false );i++)
	  if(obj.elements[i]!=elements[i])
	   equal=false;
	
	return equal;
  }
  public String setPrint()
  {
	String output="\n(";

	for(int i=0;i<101;i++)
         if(elements[i])
          output+=" "+i+" ";
	output+=")";

	return output;	
  }

}

	


  


