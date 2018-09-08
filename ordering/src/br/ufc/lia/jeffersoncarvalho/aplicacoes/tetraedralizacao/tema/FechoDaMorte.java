package br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao.tema;

import java.util.ArrayList;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Embrulho;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Face;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Vertice;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet.Point3d;
import br.ufc.lia.jeffersoncarvalho.primivasgeometricas.PrimitivasGeometricas;

public class FechoDaMorte {

	 private ArrayList facesDaMorte;
	 private ArrayList verticesDaMorte;
	 private Face faceDaMorte;
	 public FechoDaMorte() {
		ArrayList verticesCima = gerarVerticesAleatoriosEsferaCima(200);
		ArrayList verticesBaixo = gerarVerticesAleatoriosEsferaBaixo(200);
		Embrulho embrulho = new Embrulho(null);
		facesDaMorte = embrulho.resolveByWESemApplet(verticesCima);
		facesDaMorte.addAll(embrulho.resolveByWESemApplet(verticesBaixo));
		this.faceDaMorte = this.faceDaMorte(); 
		//verticesDaMorte = embrulho.getVerticesdoFecho(facesDaMorte);
		 
	}

	 private ArrayList gerarVerticesAleatoriosEsferaCima(int n)
	 {
			ArrayList res = new ArrayList();
			 
		    for(int i=0;i<n;i++){
		    	Point3d p3d = Point3d.randomOnSphere().scale(0.45);
		    	
		    	double x =  p3d.x()*40 + 80;
		    	 
		        double y =  p3d.y()*40;
		        if(y>0.5){
			        double z =  p3d.z()*40;
			        Vertice v = new Vertice(x,y,z);
			         
			        
			        if(res.contains(v))
			        	System.out.println("ponto repetido!");
			        else{
			        	 
			        	res.add(v);
			        }
		        }
		    	
		    }
	        
		         
		    return res;
	}
	 
	 private ArrayList gerarVerticesAleatoriosEsferaBaixo(int n)
	 {
			ArrayList res = new ArrayList();
			 
		    for(int i=0;i<n;i++){
		    	Point3d p3d = Point3d.randomOnSphere().scale(0.45);
		    	
		    	double x =  p3d.x()*40 + 80;
		    	 
		        double y =  p3d.y()*40;
		        if(y<-0.5){
			        double z =  p3d.z()*40;
			        Vertice v = new Vertice(x,y,z);
			         
			        
			        if(res.contains(v))
			        	System.out.println("ponto repetido!");
			        else{
			        	 
			        	res.add(v);
			        }
		        }
		    	
		    }
	        
		         
		    return res;
	}
	 
	private Face faceDaMorte(){
		 double menorDist = Double.MAX_VALUE;
		 Face faceDaMorte = null;
		 double[] origem = {0,0,0};
		 for(int i=0;i<facesDaMorte.size();i++){
			 Face f = (Face)facesDaMorte.get(i);
			 Vertice vCentral = f.getCentro();
			 if(vCentral.getY()>6){
				 double[] vCentralVectosr = {vCentral.getX(), vCentral.getY(), vCentral.getZ()};
				 double dist = PrimitivasGeometricas.dist(vCentralVectosr,origem);
				 if(dist<menorDist){
					 menorDist = dist;
					 faceDaMorte = f;
				 } 
			 }
			 
		 }
		 return faceDaMorte;
	 }
	 public void move(double passo){
		 for(int i=0;i<verticesDaMorte.size();i++){
			 Vertice v = (Vertice)verticesDaMorte.get(i);
			 v.setX(v.getX()-passo);
		 }
		 
	 }

	public ArrayList getFacesDaMorte() {
		return facesDaMorte;
	}

	public Face getFaceDaMorte() {
		return faceDaMorte;
	}
}
