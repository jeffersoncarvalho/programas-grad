/*
 * CidadeFactory.java
 *
 * Created on 25 de Agosto de 2004, 22:02
 */

package ambiente;

import java.io.*;
import util.Ponto;
import gui.GridPanel;
/**
 *
 * @author  knoppix
 */
public class CidadeFactory {
    
    /** Creates a new instance of CidadeFactory */
    public CidadeFactory() {
    }
    
    public Ambiente criarAmbiente(GridPanel gp)
    {
        Ambiente a = new Ambiente(30,30,gp);
        //banco 1
        PontoNotavel banco1 = new PontoNotavel();
        banco1.setTipo(AmbienteConstantes.BANCO) ;
        banco1.adicionarPonto(new Ponto(1,27));
        banco1.adicionarPonto(new Ponto(2,27));
        banco1.adicionarPonto(new Ponto(1,28));
        banco1.adicionarPonto(new Ponto(2,28));
        
        //banco 2
        PontoNotavel banco2 = new PontoNotavel();
        banco2.setTipo(AmbienteConstantes.BANCO) ;
        banco2.adicionarPonto(new Ponto(12,7));
        banco2.adicionarPonto(new Ponto(13,7));
        banco2.adicionarPonto(new Ponto(12,8));
        banco2.adicionarPonto(new Ponto(13,8));
        
        //banco 3
        PontoNotavel banco3 = new PontoNotavel();
        banco3.setTipo(AmbienteConstantes.BANCO) ;
        banco3.adicionarPonto(new Ponto(22,18));
        banco3.adicionarPonto(new Ponto(23,18));
        banco3.adicionarPonto(new Ponto(22,19));
        banco3.adicionarPonto(new Ponto(23,19));
        
         //farmacia 1
        PontoNotavel farmacia1 = new PontoNotavel();
        farmacia1.setTipo(AmbienteConstantes.FARMACIA) ;
        farmacia1.adicionarPonto(new Ponto(6,19));
        farmacia1.adicionarPonto(new Ponto(7,19));
        farmacia1.adicionarPonto(new Ponto(8,19));
        farmacia1.adicionarPonto(new Ponto(6,20));
        farmacia1.adicionarPonto(new Ponto(7,20));
        farmacia1.adicionarPonto(new Ponto(8,20));
        
         //farmacia 2
        PontoNotavel farmacia2 = new PontoNotavel();
        farmacia2.setTipo(AmbienteConstantes.FARMACIA) ;
        farmacia2.adicionarPonto(new Ponto(23,23));
        farmacia2.adicionarPonto(new Ponto(24,23));
        farmacia2.adicionarPonto(new Ponto(23,24));
        farmacia2.adicionarPonto(new Ponto(24,24));
        
         //farmacia 3
        PontoNotavel farmacia3 = new PontoNotavel();
        farmacia3.setTipo(AmbienteConstantes.FARMACIA) ;
        farmacia3.adicionarPonto(new Ponto(27,1));
        farmacia3.adicionarPonto(new Ponto(28,1));
        farmacia3.adicionarPonto(new Ponto(27,2));
        farmacia3.adicionarPonto(new Ponto(28,2));
        
        //supermercado 1
        PontoNotavel sm1 = new PontoNotavel();
        sm1.setTipo(AmbienteConstantes.SUPERMERCADO) ;
        sm1.adicionarPonto(new Ponto(14,26));
        sm1.adicionarPonto(new Ponto(15,26));
        sm1.adicionarPonto(new Ponto(14,27));
        sm1.adicionarPonto(new Ponto(15,27));
        
        //supermercado 2
        PontoNotavel sm2 = new PontoNotavel();
        sm2.setTipo(AmbienteConstantes.SUPERMERCADO) ;
        sm2.adicionarPonto(new Ponto(16,14));
        sm2.adicionarPonto(new Ponto(17,14));
        sm2.adicionarPonto(new Ponto(16,15));
        sm2.adicionarPonto(new Ponto(17,15));
        
        //supermercado 3
        PontoNotavel sm3 = new PontoNotavel();
        sm3.setTipo(AmbienteConstantes.SUPERMERCADO) ;
        sm3.adicionarPonto(new Ponto(28,26));
        sm3.adicionarPonto(new Ponto(29,26));
        sm3.adicionarPonto(new Ponto(28,27));
        sm3.adicionarPonto(new Ponto(29,27));
        
        //obst�ulo 1
        PontoNotavel o1 = new PontoNotavel();
		o1.setTipo(AmbienteConstantes.OBSTACULO);
		for(int i=4;i<9;i++)
			o1.adicionarPonto(new Ponto(i,5));
		for(int i=5;i<13;i++)
			o1.adicionarPonto(new Ponto(8,i));
		
		//obst�ulo 2
        PontoNotavel o2 = new PontoNotavel();
		o2.setTipo(AmbienteConstantes.OBSTACULO);
		for(int i=11;i<16;i++)
			o2.adicionarPonto(new Ponto(i,22));
		
		//obst�ulo 3
        PontoNotavel o3 = new PontoNotavel();
		o3.setTipo(AmbienteConstantes.OBSTACULO);
		for(int i=4;i<10;i++)
			o3.adicionarPonto(new Ponto(20,i));
		
        //adcionando pontos
        a.adicionarPontosNotaveis(banco1);
        a.adicionarPontosNotaveis(banco2);
        a.adicionarPontosNotaveis(banco3);
        
        a.adicionarPontosNotaveis(farmacia1);
        a.adicionarPontosNotaveis(farmacia2);
        a.adicionarPontosNotaveis(farmacia3);
        
        a.adicionarPontosNotaveis(sm1);
        a.adicionarPontosNotaveis(sm2);
        a.adicionarPontosNotaveis(sm3);
        
        a.adicionarPontosNotaveis(o1);
        a.adicionarPontosNotaveis(o2);
        a.adicionarPontosNotaveis(o3);
        
        //a.criaPoliciais();
        
        return a;
    }
    
    public Ambiente criarAmbienteDoArquivoDoJeitoQueOVascoQuer(GridPanel gp, File fileName)
    {
        int[][] mat = this.arquivoParaMatriz(fileName);
        Ambiente a = new Ambiente(30,30,gp);
        for(int i=0; i<mat.length; i++)
            for(int j=0;j<mat[0].length;j++)
            {
                int numero = mat[i][j];
                if(numero != AmbienteConstantes.VAZIO)
                {
                    PontoNotavel pn = new PontoNotavel();
                    pn.setTipo(numero);
                    this.alimentaPontoNotavelRecursivamente(pn, i, j, mat);
                    a.adicionarPontosNotaveis(pn);
                }
            }
     
        return a;
    }
    
    private int[][] arquivoParaMatriz(File fileName)
    {
        int mat[][] = new int [30][30];
        try{
            RandomAccessFile raf = new RandomAccessFile(fileName,"r");
            String line ;
            int j=0;
            while( (line = raf.readLine())!=null )
            {
                for(int i=0;i<30;i++)
                {
                	try{
						char letra = line.charAt(i);
						//System.out.print(letra + " ");
						switch (letra)
						{
							case 'F':
								mat[j][i] = AmbienteConstantes.FARMACIA;
							break;
							case 'S':
								mat[j][i] = AmbienteConstantes.SUPERMERCADO;
							break;
							case 'B':
								mat[j][i] = AmbienteConstantes.BANCO;
							break;
							case 'O':
								mat[j][i] = AmbienteConstantes.OBSTACULO;
							break;
							default:
								mat[j][i] = AmbienteConstantes.VAZIO;
							break;
                            
						}//switch
                	}
                    catch(Exception e)
                    {
						System.out.println("Furou:" + e.toString());
                    }
                }//for
                //System.out.println("");
                j++;          
            }//while
            raf.close();
        }
        catch(Exception e)
        {
            
            System.out.println(e.toString());
        }
        
        return mat;
    }
    
    
    private void alimentaPontoNotavelRecursivamente(PontoNotavel pn,int i, int j, int[][] mat)
    {
        
        if((mat[i][j] == AmbienteConstantes.VAZIO) || (mat[i][j]!=pn.getTipo()))
            return;
        
        pn.adicionarPonto(new Ponto(j,i));
        mat[i][j] = AmbienteConstantes.VAZIO;
                
        if(j+1<30)
            this.alimentaPontoNotavelRecursivamente(pn, i, j+1, mat);
        if(i+1<30)
            this.alimentaPontoNotavelRecursivamente(pn, i+1, j,mat);
        if(i-1>=0)
            this.alimentaPontoNotavelRecursivamente(pn,i-1, j,mat);
        if(j-1>=0)
            this.alimentaPontoNotavelRecursivamente(pn,i, j-1,mat);
    }
}
