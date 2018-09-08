
import java.util.*;
import javax.swing.*;


class Node
{
   Node filhoEsq,filhoDir;
   int orientacao,pontoX,pontoY,ordemPonto;
   static int ordPt;
    
 
  public Node(int pX,int pY,int discriminator)
  {
    filhoEsq=null;
    filhoDir=null;
    orientacao=discriminator;
    pontoX=pX;
    pontoY=pY;
    ordemPonto=++ordPt;
 
  }

  public synchronized void inserir(int pX,int pY,int discriminador,int discPai)
  {
    if(discPai==0)//eixo x
    {
        if(pX<pontoX)
        {
            if(filhoEsq==null)
              filhoEsq=new Node(pX,pY,discriminador);
           else
              filhoEsq.inserir(pX,pY,discriminador,filhoEsq.orientacao);
        }
        else if(pX>pontoX)
        {
            if(filhoDir==null)
              filhoDir=new Node(pX,pY,discriminador);
            else
              filhoDir.inserir(pX,pY,discriminador,filhoDir.orientacao);
        }
    }
    else//eixo y
    {
        if(pY<pontoY)
        {
            if(filhoEsq==null)
              filhoEsq=new Node(pX,pY,discriminador);
           else
              filhoEsq.inserir(pX,pY,discriminador,filhoEsq.orientacao);
        }
        else if(pY>pontoY)
        {
            if(filhoDir==null)
              filhoDir=new Node(pX,pY,discriminador);
            else
              filhoDir.inserir(pX,pY,discriminador,filhoDir.orientacao);
        }
     }

  }//fim método iserir

}//fim Node

public class KdTree
{

  private Node raiz;
  private String resultado;
  
  private int maiorX,maiorY,menorX,menorY;

  public KdTree()
  {
    raiz=null;
    resultado="";
    
    maiorX=0;
    maiorY=0;
    menorX=100;
    menorY=100;
  }

  public synchronized boolean inserirNo(int pX,int pY,int discriminator)
  {
    if(raiz==null)
      raiz=new Node(pX,pY,discriminator);
    else
    {
      raiz.inserir(pX,pY,discriminator,raiz.orientacao);
      Node filho = pesquisaPonto(pX,pY);
      Node pai = noPai(filho);
      if(pai.orientacao==filho.orientacao)
      {
       filho.ordPt--;	
       removerNo(pX,pY);
       return false;
      }
	}
      
      return true;
  }//inserir no

  private void preOrdem(Node no)
  {
    if (no==null)
     return;

    resultado+= Integer.toString(no.pontoX) + " " + Integer.toString(no.pontoY) + " " + Integer.toString(no.ordemPonto+1) + " "+ Integer.toString(no.orientacao) + " "; 
    preOrdem(no.filhoEsq);
    preOrdem(no.filhoDir);
  }
  
  
  public Node pesquisaPonto(int pX, int pY)
  {
  	Node aux;
  	aux=raiz;
  	
  	while(aux!=null)
  	{
  		if(aux.pontoX==pX && aux.pontoY==pY)
  		 return aux;
  		 
  		if(aux.orientacao==0)//eixo x
  		{
  			if(pX<=aux.pontoX)
  			 aux=aux.filhoEsq;
  			else if(pX>aux.pontoX)
  			 aux=aux.filhoDir;
  		}
  		else
  		{
  			if(pY<=aux.pontoY)
  			 aux=aux.filhoEsq;
  			else if(pY>aux.pontoY)
  			 aux=aux.filhoDir;
  		}
  	}//while
  	
  	return null;
  	
  }

  public String getArvore()
  {
    resultado="";
    preOrdem(raiz);
    return resultado;
  }
  
  private boolean areNosIguais(Node no1, Node no2)
  {
  	if((no1.pontoX==no2.pontoX)&&(no1.pontoY==no2.pontoY))
  		return true;
  	return false;
  }//areNosIguais
  
  private  Node noPai(Node no)
  {
  	if(areNosIguais(no,raiz))
  		return null;
  		
  	Node pai=raiz;
  	
  	do
  	{	
  		if(pai.orientacao==0)
  		{
  			if(no.pontoX<pai.pontoX)
  			{
  				if(areNosIguais(pai.filhoEsq,no))
  					return pai;
  				else
  					pai=pai.filhoEsq;
  			}
  			else 
  				if(no.pontoX>pai.pontoX)
  				{
  					if(areNosIguais(pai.filhoDir,no))
  						return pai;
  					else
  					pai=pai.filhoDir;
  				}
  		}//no x
  		else
  		if(pai.orientacao==1)
  		{
  			if(no.pontoY<pai.pontoY)
  			{
  				if(areNosIguais(pai.filhoEsq,no))
  					return pai;
  				else
  					pai=pai.filhoEsq;
  			}
  			else 
  				if(no.pontoY>pai.pontoY)
  				{
  					if(areNosIguais(pai.filhoDir,no))
  						return pai;
  					else
  					pai=pai.filhoDir;
  				}
  		}//no x
  		
  	}while(pai!=null);
  	
  	return null;
  	
  }//noPai
  
  private  boolean isFolha(Node no)
  {
  	if(no.filhoDir==null && no.filhoEsq==null)
  		return true;
  	return false;
  }//isFolha
  
  public boolean removerNo(int px,int py)
  {
  	Node no = pesquisaPonto(px,py);
  	if(no==null)
  		return false;//nó não existente
  		
  	if(isFolha(no))
  	{
  		Node pai = noPai(no);
  		if(pai==null)
  			raiz=null;//se não tem pai, trata-se da raiz.
  		else
  		{
  			if(pai.orientacao==0)
  			{
  				if(px<pai.pontoX)
  					pai.filhoEsq=null;
  				else if(px>pai.pontoX)
  					pai.filhoDir=null;
  			}//eixo x de orientacao
  			else
  			if(pai.orientacao==1)
  			{
  				if(py<pai.pontoY)
  					pai.filhoEsq=null;
  				else if(py>pai.pontoY)
  					pai.filhoDir=null;
  			}
  		}//demais nós folhas
  	}//remoção inicial de um nó folha
  	else
  	{
  		if(tipoPai(no)==1)
  		{
  			Node pai = noPai(no);
  			if(pai!=null)
  			{
  				if(isFilhoEsqOrFilhoDir(pai,no)==1)//nó é filhoEsq de pai
  					pai.filhoEsq=no.filhoEsq;
  				else
  				 if(isFilhoEsqOrFilhoDir(pai,no)==2)//nó é filhoDir de pai
  					pai.filhoDir=no.filhoEsq;
  					
  				no.filhoEsq.orientacao=no.orientacao;
  				no.filhoEsq.filhoDir=no.filhoDir;
  				no.filhoDir=null;
  				no.filhoEsq=null;		 
  			}//no não é a raiz
  			else
  			{
  				raiz.filhoEsq.orientacao=raiz.orientacao;
  				raiz.filhoEsq.filhoDir=raiz.filhoDir;
  				raiz=raiz.filhoEsq;
  				no.filhoDir=null;
  				no.filhoEsq=null;
  				
  			}//nó é a raiz
  					
  		}//meu no.filhoEsq é um nó folha, tenho que subí-lo
  		else
  		 if(tipoPai(no)==2)
  		 {
  			Node pai = noPai(no);
  			if(pai!=null)
  			{
  				if(isFilhoEsqOrFilhoDir(pai,no)==1)//nó é filhoEsq de pai
  					pai.filhoEsq=no.filhoDir;
  				else
  				 if(isFilhoEsqOrFilhoDir(pai,no)==2)//nó é filhoDir de pai
  					pai.filhoDir=no.filhoDir;
  					
  				no.filhoDir.orientacao=no.orientacao;
  				no.filhoDir.filhoEsq=no.filhoEsq;
  				no.filhoDir=null;
  				no.filhoEsq=null;		 
  			}//no não é a raiz
  			else
  			{
  				raiz.filhoDir.orientacao=raiz.orientacao;
  				raiz.filhoDir.filhoEsq=raiz.filhoEsq;
  				raiz=raiz.filhoDir;
  				no.filhoDir=null;
  				no.filhoEsq=null;
  				
  			}//nó é a raiz
  					
  		 }//meu no.filhoDir é um nó folha, tenho que subí-lo
  		 else
  		 { 
  			//******Código para remoções de demais nós******
  			detonaNo(no);
  			//******Código para remoções de demais nós******
  		 }//demais nós 
  	}
  	System.gc();
  	return true;
  			
  }//removerNo
  
  private byte tipoPai(Node no)
  {
  	if(no.filhoEsq==null || no.filhoDir==null)
  	 return 0;
  	
  	 if(isFolha(no.filhoEsq))
  	  return 1;//se o filho esq. for folha.
  	 else
  	  if(isFolha(no.filhoDir))
  	   return 2; // se o filho dir. for folha.
  	  
  	 return 0;
  }//tipoPai
  
  private byte isFilhoEsqOrFilhoDir(Node pai, Node filhoCandidato)
  {
  	if((pai.filhoEsq.pontoX==filhoCandidato.pontoX) &&(pai.filhoEsq.pontoY==filhoCandidato.pontoY))
  	 return 1;//caso seja um filho Esquerdo
  	return 2;//caso seja um filho Direito
  }
  
  private void detonaNo(Node no)
  {
  		Node pai=noPai(no);
  		Node noASubir=null;
  		//int subiu=0;
  		
  		if(no.filhoEsq!=null)
  		{
  			maiorNoArvore(no.filhoEsq,no.orientacao);
  			noASubir=pesquisaPonto(maiorX,maiorY);
  			zeraNoMaior();
  			//subiu=0;//lado esquerdo;	
  		}
  		else
  		{
  			menorNoArvore(no.filhoDir,no.orientacao);
  			noASubir=pesquisaPonto(menorX,menorY);
  			aumentaNoMenor();
  			//subiu=1;//lado direito
  		}
  		
  		
  		
  		
  		if(isFolha(noASubir))
  		{
  			
  			
  			Node paiNoASubir = noPai(noASubir);
  			if(isFilhoEsqOrFilhoDir(paiNoASubir,noASubir)==1)
  			 paiNoASubir.filhoEsq = null;
  			else
  			 paiNoASubir.filhoDir = null;
  			 
  			//JOptionPane.showMessageDialog(null,noASubir.pontoX+"|"+noASubir.pontoY,"Erro",JOptionPane.ERROR_MESSAGE);      
  			  
  			noASubir.orientacao=no.orientacao;
  			noASubir.filhoEsq=no.filhoEsq;
  			noASubir.filhoDir=no.filhoDir;
  			 
  		    if(pai==null)
  		 		raiz=noASubir;
  			else
  			{
  				if(isFilhoEsqOrFilhoDir(pai,no)==1)
  			 		pai.filhoEsq=noASubir;
  				else
  			 		pai.filhoDir=noASubir;	
  			}//não perca a cabeça!!!*
	 
	 		no.filhoDir=null;
	 		no.filhoEsq=null;
	 		
  			return;
  			
  		}
  		else
  		 detonaNo(noASubir);
  		
  			
  		
  }//detona nó
  
  private void maiorNoArvore(Node no,int discriminador)
  {
  	if (no==null)
     return;

	if(discriminador==0)
	{
		if(no.pontoX>maiorX)
		{
			maiorX=no.pontoX;
			maiorY=no.pontoY;
		}
	}
	else
		if(no.pontoY>maiorY)
		{
			maiorX=no.pontoX;
			maiorY=no.pontoY;		
		}
    
    maiorNoArvore(no.filhoEsq,discriminador);
    maiorNoArvore(no.filhoDir,discriminador);
  	
  }//achaMaiorNoSubArvore
  
   private void menorNoArvore(Node no,int discriminador)
  {
  	if (no==null)
     return;

	if(discriminador==0)
	{
		if(no.pontoX<menorX)
		{
			menorX=no.pontoX;
			menorY=no.pontoY;
		}
	}
	else
		if(no.pontoY<maiorY)
		{
			menorX=no.pontoX;
			menorY=no.pontoY;		
		}
    
    menorNoArvore(no.filhoEsq,discriminador);
    menorNoArvore(no.filhoDir,discriminador);
  	
  }//achaMaiorNoSubArvore
  
 
  private void zeraNoMaior()
  {
  	maiorX=0;
  	maiorY=0;
  }
  
  private void aumentaNoMenor()
  {
  	menorX=100;
  	menorY=100;
  }

}//fim classe