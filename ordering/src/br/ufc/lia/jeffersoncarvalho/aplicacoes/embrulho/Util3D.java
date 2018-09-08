package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho;

 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.Cor;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.EmbrulhoPainel;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet.Point3d;
import br.ufc.lia.jeffersoncarvalho.primivasgeometricas.PrimitivasGeometricas;

public class Util3D {
	
	 
	
	public static double anguloEntreFaceEVertice2(Vertice verticeDeGiro, Aresta arestaGiro , Vertice vComparado ){
		
		 Vertice agV1 = arestaGiro.getV1().getVPerturbado();
		Vertice agV2 = arestaGiro.getV2().getVPerturbado();
		Vertice vGiro = verticeDeGiro.getVPerturbado();
		Vertice vComp = vComparado.getVPerturbado();
		 
		//vetores relativos a face base (v1 v2 e v3 sao os vertice da base de giro)
		//vetor do vertice a ser girado
		double[] v1v3 = Util3D.createVectorR3(agV1,vGiro,0); //p1p
		//vetor da aresta de giro
		double[] v1v2 = Util3D.createVectorR3(agV1,agV2,0); //p1p2
		
		//vetor relativo ao vertice de comparacao que tem aresta comum arestaGiro a face base!
		double[] v1vComparado = Util3D.createVectorR3(agV1,vComp,0); //p1p'
		
		
		
		//norma da face base
		
		double[] norma_v1v2v3 = PrimitivasGeometricas.produtoVetorialR3(v1v3,v1v2); 
		
		
		//norma da face formada pela aresta de giro e o vcomparado
		 
		double[] norma_v1v2vComparado = PrimitivasGeometricas.produtoVetorialR3(v1vComparado,v1v2); 
		
		
		
		//angulo entre as duas normas
		return PrimitivasGeometricas.ang(norma_v1v2vComparado,norma_v1v2v3) ;
		
		 
	}
	
	
	 
	public static double anguloSolido(Face face , Vertice vCandidato ){
		
		  
		 double omega = 0;
		 double[] origem = {0,0,0};
		 double[] R1 = createVectorR3(vCandidato ,face.getV1(),0 );
		 double[] R2 = createVectorR3(vCandidato ,face.getV2(),0 );
		 double[] R3 = createVectorR3(vCandidato ,face.getV3(),0 );
		 
		 double dR1 = PrimitivasGeometricas.dist(R1,origem);
		 double dR2 = PrimitivasGeometricas.dist(R2,origem);
		 double dR3 = PrimitivasGeometricas.dist(R3,origem);
		 
		 double R1R2 = PrimitivasGeometricas.produtoEscalar(R1,R2);
		 double R1R3 = PrimitivasGeometricas.produtoEscalar(R1,R3);
		 double R2R3 = PrimitivasGeometricas.produtoEscalar(R2,R3);
		 
		 double detA = R1[0]*R2[1]*R3[2] + R2[0]*R3[1]*R1[2] + R3[0]*R1[1]*R2[2] - 
		 			   R3[0]*R2[1]*R1[2] - R1[0]*R3[1]*R2[2] - R2[0]*R1[1]*R3[2];
		 
		 double div = detA/(dR1*dR2*dR3 + R1R2*dR3 + R1R3*dR2 + R2R3*dR1);
		 
		 omega =  2*Math.atan(div); 
		 
		 return omega;
		
		 
	}

	public static double[] createVectorR3(Vertice vStart, Vertice vEnd, int shift){
		double[] res = new double[3];
		 
		res[0] = vEnd.getX()-vStart.getX()+shift;
		res[1] = vEnd.getY()-vStart.getY()+shift;
		res[2] = vEnd.getZ()-vStart.getZ()+shift;
		
		return res;
	}
	
	
	public static ArrayList gerarVerticesDeArquivo(String caminhoArquivo, EmbrulhoPainel tela)
	{
		ArrayList res = new ArrayList();
		try {
	        BufferedReader in = new BufferedReader(new FileReader(caminhoArquivo));
	        String str;
	        int i = 0;
	        while ((str = in.readLine()) != null && !str.equals("") && !str.equals(" ")) {
	        	str = str.trim();
	        	StringTokenizer tokens = new StringTokenizer(str," "); 
	            
	            double x = Double.parseDouble(tokens.nextToken()) ;
	            double y = Double.parseDouble(tokens.nextToken()) ;
	            double z = Double.parseDouble(tokens.nextToken()) ;
	            Vertice v = new Vertice(x,y,z);
	            
	            tela.inserePonto(v.toPonto3D(),new Cor(0f,0f,0f));
	            
	           // System.out.println(Math.round(x)+","+Math.round(y)+","+Math.round(z));
	            if(res.contains(v))
	            	System.out.println("ponto repetido!");
	            else{
	            	i++;
	            	res.add(v);
	            }
	            	
	        }
	        in.close();
	        System.out.println( i + " pontos adicionados...");
	    } catch (IOException e) {
	    	System.out.println("Erro na leitura do arquivo");
	    }
	    return res;
	}
	
	
	
	public static ArrayList gerarVerticesAleatoriosCubo(int n, EmbrulhoPainel tela)
	{
		ArrayList res = new ArrayList();
		 
	    for(int i=0;i<n;i++){
	    	Point3d p3d = Point3d.random().add(new Point3d(-0.5,-0.5,-0.5)).scale(1.25);
	    	double x =  p3d.x()*40;
	        double y =  p3d.y()*40;
	        double z =  p3d.z()*40;
	        Vertice v = new Vertice(x,y,z);
	        tela.inserePonto(v.toPonto3D(),new Cor(1f,0f,0f));
	        
	        if(res.contains(v))
	        	System.out.println("ponto repetido!");
	        else{
	        	 
	        	res.add(v);
	        }
	    }
        
	         
	    return res;
	}
	
	public static ArrayList gerarVerticesAleatoriosEsfera(int n, EmbrulhoPainel tela)
	{
		ArrayList res = new ArrayList();
		 
	    for(int i=0;i<n;i++){
	    	Point3d p3d = Point3d.randomOnSphere().scale(0.75);
	    	double x =  p3d.x()*40;
	        double y =  p3d.y()*40;
	        double z =  p3d.z()*40;
	        Vertice v = new Vertice(x,y,z);
	        tela.inserePonto(v.toPonto3D(),new Cor(1f,0f,0f));
	        
	        if(res.contains(v))
	        	System.out.println("ponto repetido!");
	        else{
	        	 
	        	res.add(v);
	        }
	    }
        
	         
	    return res;
	}
	
	public static ArrayList gerarVerticesAleatoriosGaussian(int n, EmbrulhoPainel tela)
	{
		ArrayList res = new ArrayList();
		 
	    for(int i=0;i<n;i++){
	    	Point3d p3d = Point3d.randomGaussian().scale(0.3);
	    	double x =  p3d.x()*32;
	        double y =  p3d.y()*32;
	        double z =  p3d.z()*32;
	        Vertice v = new Vertice(x,y,z);
	        tela.inserePonto(v.toPonto3D(),new Cor(1f,0f,0f));
	        
	        if(res.contains(v))
	        	System.out.println("ponto repetido!");
	        else{
	        	 
	        	res.add(v);
	        }
	    }
        
	         
	    return res;
	}
	
	public static void main(String[] args) {
		
		Vertice v1= new  Vertice(-5,5,-1);
		Vertice v2= new  Vertice(5,5,-1);
		Vertice v3= new  Vertice(0,-5,-1);
		
		Vertice vc1= new  Vertice(0,0,5);
		Vertice vc2= new  Vertice(0,0,100);
		
		Face f = new Face(v1,v2,v3);
		System.out.println(Util3D.anguloSolido(f,vc1));
		System.out.println(Util3D.anguloSolido(f,vc2));
	}
}
