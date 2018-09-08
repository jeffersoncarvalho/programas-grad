
package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho;

 
 

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.EmbrulhoPainel;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet.Object3dList;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet.Point3d;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet.Triangle3d;

public class Embrulho {

	private EmbrulhoPainel tela;
	
	public Embrulho(EmbrulhoPainel tela) {
		super();
		// TODO Auto-generated constructor stub
		this.tela = tela;
	}


	/**
	 * A partir de um List de vértices, retorna a Face inicial para inicio do Embrulho.
	 * @param vertices
	 * @return
	 */
	public Face getFaceInicial(ArrayList vertices){
		
		 
		//de todos os vertice, achar aquele de z mínimo
		Vertice verticeExtremo = null;
		double menor = Double.MAX_VALUE;
		for(Iterator ite = vertices.iterator() ; ite.hasNext() ; ){
			Vertice verticeRef = (Vertice)ite.next();
			if(verticeRef.getVPerturbado().getZ()<menor)
			{
				menor = verticeRef.getVPerturbado().getZ();
				verticeExtremo = verticeRef;
			}
		}
		
		//traçando "semi-plano" horizontal abaixo do menor vertice e direcao ao eixo y positivo.
		Vertice v1 = verticeExtremo;
		Vertice v2 = new Vertice(v1.getX()+1,v1.getY(),v1.getZ()); //vertice fake 1
		Vertice v3 = new Vertice(v1.getX(),v1.getY()-1,v1.getZ()); //vertice fake 2
		
		
		
		//varrer outros vertices excluindo v1, eh claro
		//aki acharei a aresta inicial!! Se Deus quiser!
		double menorAngulo = Double.MAX_VALUE;
		Vertice vEscolhido = null;
		Aresta arestaDeGiroFake = new Aresta(v1,v2);
		for(Iterator ite = vertices.iterator() ; ite.hasNext() ; ){
			Vertice verticeRef = (Vertice)ite.next();
			if(!verticeRef.equals(v1)){
				double ang = Util3D.anguloEntreFaceEVertice2(v3,arestaDeGiroFake,verticeRef);
				if(ang<menorAngulo){
					menorAngulo = ang;
					vEscolhido = verticeRef;
				}
			}
		}
		
		 
		 
//		aresta inicial!! The Force is strong on me!!
		Aresta arestaInicial = new Aresta (vEscolhido,v1);
		Vertice fake3 = new Vertice(v1.getX()-11,v1.getY(),v1.getZ()); //vertice fake 1
		//finalmente, a face inicial...
		//com o cuidado de não testar os vertices que já estão na minha aresta!!!
		//v3 foi pro espaço! Não preciso mais dele, nem dele de mim...:(
		double maiorAngulo = Double.MIN_VALUE;
		Vertice vFinal = null;
		for(Iterator ite = vertices.iterator() ; ite.hasNext() ; ){
			Vertice verticeRef = (Vertice)ite.next();
			if(!verticeRef.equals(arestaInicial.getV1()) && !verticeRef.equals(arestaInicial.getV2())){
				 
				double ang = Util3D.anguloEntreFaceEVertice2(fake3,arestaInicial,verticeRef);
				if(ang>maiorAngulo){
					maiorAngulo = ang;
					vFinal = verticeRef;
				}
			}
		}
		
		//voi-lá
		return new Face(vFinal,arestaInicial.getV1(),arestaInicial.getV2());
	}
	
	 
	
	 
 
	//retorna um vertice, em uma face, que seja difernte dos dois exis tentes na arestadegiro
	private Vertice getVerticeDeGiro(Face faceDeGiro, Aresta arestaDeGiro){
		Vertice verticeDeGiro = null;
		if(!faceDeGiro.getV1().equals(arestaDeGiro.getV1()) &&
		   !faceDeGiro.getV1().equals(arestaDeGiro.getV2()))
			return faceDeGiro.getV1();
		if(!faceDeGiro.getV2().equals(arestaDeGiro.getV1()) &&
		   !faceDeGiro.getV2().equals(arestaDeGiro.getV2()))
			return faceDeGiro.getV2();
		if(!faceDeGiro.getV3().equals(arestaDeGiro.getV1()) &&
			!faceDeGiro.getV3().equals(arestaDeGiro.getV2()))
			return faceDeGiro.getV3();
		return verticeDeGiro;
		
	}
	
	 
 
	//passo principal. O miolo do embrulho pra presente. Devolve um conjunto de objetos que será usado na apresentação do applet.
	public  Object3dList resolveByWE(ArrayList vertices){
		System.out.println("(resolveByWE) Pontos lidos: " + vertices.size());
		//faces do fecho.
		ArrayList listaDeFaces = new ArrayList();
		Hashtable tabelaDeArestas = new Hashtable();
		Object3dList facesDraw = new Object3dList(20);
		int frame = 1;
		//caso degenerado
		 
		
		//face inicial, usando vertice artificiais
		Face faceInicial = this.getFaceInicial(vertices );
		faceInicial.orientacao = 1;
		Point3d p3da = new Point3d(faceInicial.getV1().getX(),faceInicial.getV1().getY(),faceInicial.getV1().getZ());
		Point3d p3db = new Point3d(faceInicial.getV2().getX(),faceInicial.getV2().getY(),faceInicial.getV2().getZ());
		Point3d p3dc = new Point3d(faceInicial.getV3().getX(),faceInicial.getV3().getY(),faceInicial.getV3().getZ());
		frame ++; 
		 
		Triangle3d t3d = new Triangle3d(p3da,p3db,p3dc,frame);
		facesDraw.addElement(t3d);
		if(tela!=null)
			tela.desenhaFace(faceInicial);
		listaDeFaces.add(faceInicial);
 
		//varrendo todas a faces.
		while(!listaDeFaces.isEmpty()){
			Face faceDeGiro = (Face)listaDeFaces.get(0);
			 
			listaDeFaces.remove(0);
			
			
			ArrayList arestasLivres = this.gerarArestasLivresWE(faceDeGiro,tabelaDeArestas);
			//System.out.println("Face -- "+indexFaceAtual);
			for(int i=0;i<arestasLivres.size();i++){
				//System.out.println("Aresta -- "+i);
				Aresta arestaLivreRef = (Aresta)arestasLivres.get(i);
				Vertice verticeDeGiro = this.getVerticeDeGiro(faceDeGiro,arestaLivreRef);
				
				//Face de maior angulo com a face de giro, girando em torno da 
				//aresta de giro e mexendo o vertice de giro
				double maiorAngulo = Double.MIN_VALUE;
				Vertice vFinal = null;
				
				 
				 
				
				for(int index =0; index < vertices.size() ; index++ ){
					Vertice verticeRef = (Vertice)vertices.get(index);
					if( 
							!verticeRef.equals(verticeDeGiro)
						 && !verticeRef.equals(arestaLivreRef.getV1()) 
						 && !verticeRef.equals(arestaLivreRef.getV2()))
					{
						double ang = Util3D.anguloEntreFaceEVertice2(verticeDeGiro,arestaLivreRef,verticeRef);
						 
						if(ang>maiorAngulo){
							maiorAngulo = ang;
							vFinal = verticeRef;
						}
						
						
						
					}//evitando pegar vertices da face de giro
				}
				
	 
				//nova face encontrada.que faz parte do fecho..
				Face novaFace = new Face( arestaLivreRef.getV1(),arestaLivreRef.getV2(), vFinal);
				//arestaLivreRef.setFccw(novaFace);
				this.gerarArestasLivresWE(novaFace,tabelaDeArestas);
				if(listaDeFaces.contains(novaFace))
					System.out.println("ERRO: repetindo faces.");
				else{
					listaDeFaces.add(novaFace);
					if(tela!=null)
						tela.desenhaFace(novaFace);
					 
					p3da = new Point3d(arestaLivreRef.getV1().getX(),arestaLivreRef.getV1().getY(),arestaLivreRef.getV1().getZ());
					p3db = new Point3d(arestaLivreRef.getV2().getX(),arestaLivreRef.getV2().getY(),arestaLivreRef.getV2().getZ());
					p3dc = new Point3d(vFinal.getX(),vFinal.getY(),vFinal.getZ());
					
					if(faceDeGiro.orientacao==1){
						
						t3d = new Triangle3d(p3da,p3dc,p3db,frame);
						facesDraw.addElement(t3d);
						novaFace.orientacao=-1;
					}
						
					else{
						 
						t3d = new Triangle3d(p3da,p3db,p3dc,frame);
						facesDraw.addElement(t3d);
						novaFace.orientacao =1;
					}
						
					
					frame++;
				}
				 
				
					
				
			}//arestas livres da faceDeGiro
			
			 
		}
		
		
		
		//System.out.println(listaDeFaces.size());
		 return facesDraw;
	}
	
	public  ArrayList resolveByWESemApplet(ArrayList vertices){
		
		System.out.println("(resolveByWESemApplet) Pontos lidos: " + vertices.size());
		//faces do fecho.
		ArrayList listaDeFaces = new ArrayList();
		Hashtable tabelaDeArestas = new Hashtable();
		 
		 
		//caso degenerado
		 
		
		//face inicial, usando vertice artificiais
		Face faceInicial = this.getFaceInicial(vertices );
		 
		 
		listaDeFaces.add(faceInicial);
 
		//varrendo todas a faces.
		int indexFaces = 0;
		while(indexFaces!=listaDeFaces.size()){
			Face faceDeGiro = (Face)listaDeFaces.get(indexFaces);
			 
			indexFaces++;
			
			
			ArrayList arestasLivres = this.gerarArestasLivresWE(faceDeGiro,tabelaDeArestas);
			//System.out.println("Face -- "+indexFaceAtual);
			for(int i=0;i<arestasLivres.size();i++){
				//System.out.println("Aresta -- "+i);
				Aresta arestaLivreRef = (Aresta)arestasLivres.get(i);
				Vertice verticeDeGiro = this.getVerticeDeGiro(faceDeGiro,arestaLivreRef);
				
				//Face de maior angulo com a face de giro, girando em torno da 
				//aresta de giro e mexendo o vertice de giro
				double maiorAngulo = Double.MIN_VALUE;
				Vertice vFinal = null;
				
				 
				 
				
				for(int index =0; index < vertices.size() ; index++ ){
					Vertice verticeRef = (Vertice)vertices.get(index);
					if( 
							!verticeRef.equals(verticeDeGiro)
						 && !verticeRef.equals(arestaLivreRef.getV1()) 
						 && !verticeRef.equals(arestaLivreRef.getV2()))
					{
						double ang = Util3D.anguloEntreFaceEVertice2(verticeDeGiro,arestaLivreRef,verticeRef);
						 
						if(ang>maiorAngulo){
							maiorAngulo = ang;
							vFinal = verticeRef;
						}
						
						
						
					}//evitando pegar vertices da face de giro
				}
				
	 
				//nova face encontrada.que faz parte do fecho..
				Face novaFace = new Face( arestaLivreRef.getV1(),arestaLivreRef.getV2(), vFinal);
				//arestaLivreRef.setFccw(novaFace);
				this.gerarArestasLivresWE(novaFace,tabelaDeArestas);
				if(listaDeFaces.contains(novaFace))
					System.out.println("ERRO: repetindo faces.");
				else{
					listaDeFaces.add(novaFace);
					 
					 
				}
				 
				
					
				
			}//arestas livres da faceDeGiro
			
			 
		}
		
		//System.out.println(listaDeFaces.size());
		 
		 return listaDeFaces;
	}
	
	//Retorna quais as arestas livres de face em comparação com o vetor de outras faces.
	private ArrayList gerarArestasLivresWE(Face face, Hashtable outrasArestasWE)
	{
		/*ArrayList arestas = face.gerarArestas();*/ 
		ArrayList arestasLivres = new ArrayList(3);
		
		Aresta a1 = new Aresta(face.getV1(),face.getV2());
		Aresta a2 = new Aresta(face.getV2(),face.getV3());
		Aresta a3 = new Aresta(face.getV3(),face.getV1());
		 
		
		if(!outrasArestasWE.containsKey(a1.getRep()) 
			&& 
		   !outrasArestasWE.containsKey(a1.getRepInversa())){
			
			a1.setFcw(face);
			outrasArestasWE.put(a1.getRep(),a1);
			arestasLivres.add(a1);
		}else{
			 
			Aresta aRef = (Aresta)outrasArestasWE.get(a1.getRep());
			if(aRef==null)
				aRef = (Aresta)outrasArestasWE.get(a1.getRepInversa());
			
			if(aRef.isLivre()){
				arestasLivres.add(aRef);
				aRef.setFccw(face);
			}
				
			
		}
		
		if(!outrasArestasWE.containsKey(a2.getRep()) 
				&& 
			   !outrasArestasWE.containsKey(a2.getRepInversa())){
				
				a2.setFcw(face);
				outrasArestasWE.put(a2.getRep(),a2);
				arestasLivres.add(a2);
			}else{
				 
				Aresta aRef = (Aresta)outrasArestasWE.get(a2.getRep());
				if(aRef==null)
					aRef = (Aresta)outrasArestasWE.get(a2.getRepInversa());
				
				if(aRef.isLivre()){
					arestasLivres.add(aRef);
					aRef.setFccw(face);
				}
					
				
			}
		
		if(!outrasArestasWE.containsKey(a3.getRep()) 
				&& 
			   !outrasArestasWE.containsKey(a3.getRepInversa())){
				
				a3.setFcw(face);
				outrasArestasWE.put(a3.getRep(),a3);
				arestasLivres.add(a3);
			}else{
				 
				Aresta aRef = (Aresta)outrasArestasWE.get(a3.getRep());
				if(aRef==null)
					aRef = (Aresta)outrasArestasWE.get(a3.getRepInversa());
				
				if(aRef.isLivre()){
					arestasLivres.add(aRef);
					aRef.setFccw(face);
				}
					
				
			}
		 
		return arestasLivres;
	}
	 
	public ArrayList getVerticesdoFecho(ArrayList facesFc3d){
		ArrayList verticesDoFc3d = new ArrayList(10);
		for(int i=0;i<facesFc3d.size();i++){
			 Face f = (Face)facesFc3d.get(i);
			 if(!verticesDoFc3d.contains(f.getV1()));
			 	verticesDoFc3d.add(f.getV1());
			 if(!verticesDoFc3d.contains(f.getV2()));
			 	verticesDoFc3d.add(f.getV2());
			 if(!verticesDoFc3d.contains(f.getV3()));
			 	verticesDoFc3d.add(f.getV3());
			 	
			 
		 }
		return verticesDoFc3d;
	}
	 
	
}