package br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao;


import java.util.ArrayList;
import java.util.Iterator;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Embrulho;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Face;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Util3D;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Vertice;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.EmbrulhoPainel;

public class Tetraedralizacao {

	private EmbrulhoPainel tela;
	
	public Tetraedralizacao(EmbrulhoPainel tela) {
		super();
		// TODO Auto-generated constructor stub
		this.tela = tela;
	}

	
	public void tetraedralizar(ArrayList vertices){
		
		//Hash de Faces
		ArrayList tabelaDeFaces = new ArrayList(20);
		 
		//lista de tetraedros encontrados
		ArrayList listaTetraedros = new ArrayList(20);
		ArrayList listaTetraedrosClone = new ArrayList(20);
		//encontrando o tetraedro inicial
		Tetraedro tetraedroInicial = this.gerarTetraedroInicial(vertices);
		listaTetraedros.add(tetraedroInicial);
		listaTetraedrosClone.add(tetraedroInicial);
		//desenhando as faces deste tetraedro
		Face faces[]  = tetraedroInicial.getFaces();
		for(int i=0;i<faces.length;i++){
			tela.desenhaFace(faces[i]);
			
		}
		
		 while(!listaTetraedros.isEmpty()){
			Tetraedro tetraedro = (Tetraedro)listaTetraedros.get(0);
			listaTetraedros.remove(0);
			//achar faces livres deste tetraedro
			//faces livres não pertencem ao fc3d e não possuem dois tetraedros (tcw e tccw)
			ArrayList facesLivres = this.gerarFacesLivres(tetraedro,tabelaDeFaces,vertices);
			for(int i=0;i<facesLivres.size();i++){
				Face faceRef = (Face)facesLivres.get(i);
				Vertice verticeContrarioFace = tetraedro.getVerticeContrarioFace(faceRef);
				double angFaceRefVerticeContrario  = Util3D.anguloSolido(faceRef,verticeContrarioFace);
				
				double maiorAngulo = Double.MIN_VALUE;
				Vertice vFinal = null;
				for(Iterator ite = vertices.iterator() ; ite.hasNext() ; ){
					Vertice verticeRef = (Vertice)ite.next();
					
					
					if(     !verticeRef.equals(faceRef.getV1()) 
								&& !verticeRef.equals(faceRef.getV2()) 
								&& !verticeRef.equals(faceRef.getV3())
								
								)
						{
							if(angFaceRefVerticeContrario>0){
								//soh devo pegar angulos negativos, pois estão do outro lado da faceRef!!
								double ang = Util3D.anguloSolido(faceRef,verticeRef);
								if(ang<0)
									//no entanto, no teste, torno-o absoluto
									if(Math.abs(ang)>maiorAngulo){
										maiorAngulo = Math.abs(ang);
										vFinal = verticeRef;
									}
							}//if ang pos
							else{
								double ang = Util3D.anguloSolido(faceRef,verticeRef);
								if(ang>0)
									//agora pego so os positivos
									if( ang >maiorAngulo){
										maiorAngulo =  ang ;
										vFinal = verticeRef;
									}
							}
									
							 
								
						}//if
				}//for de vertices
				
				Tetraedro tetraedroNovo = new Tetraedro(faceRef.getV1(),faceRef.getV2(),
														faceRef.getV3(),vFinal);
				this.gerarFacesLivres(tetraedroNovo,tabelaDeFaces,vertices);
				if(listaTetraedrosClone.contains(tetraedroNovo))
					System.out.println("ERRO: Tetraedro repetido");
				else{
					listaTetraedros.add(tetraedroNovo);
					listaTetraedrosClone.add(tetraedroNovo);
					Face[] facesNovas = tetraedroNovo.getFaces();
					for(int fi=0;fi<facesNovas.length;fi++){
						if(!facesLivres.contains(facesNovas[fi]))
							tela.desenhaFace(facesNovas[fi]);
						 
					}
				}
					
			}//rodando as faces livres
			
		}
		tela.display();
		tela.repaint();
		tela.setTetraedros(listaTetraedrosClone);
		System.out.println("Numero de Tetraedros: " + listaTetraedrosClone.size());
		//System.out.println(tabelaDeFaces.size()); 
		
	}
	
	public ArrayList gerarFacesLivres(Tetraedro tetraedro, ArrayList tabelaDeFaces, ArrayList vertices){
		ArrayList facesLivres = new ArrayList();
		Face[] facesCandidatas = tetraedro.getFaces();
		for(int i=0;i<facesCandidatas.length;i++){
			
			Face faceRef = facesCandidatas[i];
			if(!isFaceFromFecho(tetraedro,faceRef,vertices)){
				//String rep = faceRef.gerarRepresentacao();
				int indexDaFace = tabelaDeFaces.indexOf(faceRef);
//				Face faceOnHash = (Face)tabelaDeFaces.indexOf(rep);
				
				if(indexDaFace<0){
					faceRef.setTcw(tetraedro);
					facesLivres.add(faceRef);
					tabelaDeFaces.add(faceRef);
				}else {
 					Face faceOnHash = (Face)tabelaDeFaces.get(indexDaFace);
					if(faceOnHash.getTccw()==null){
						facesLivres.add(faceOnHash);
						faceOnHash.setTccw(tetraedro);
					}
					if(faceOnHash.getTcw()==null)
						System.out.println("Algo errado");
				}
			} 
			
			
		}//for
		return facesLivres;
	}
	
	private boolean isFaceFromFecho(Tetraedro t, Face face, ArrayList vertices){
		Vertice vContrario = t.getVerticeContrarioFace(face);
		 
		double angFaceRefVerticeContrario  = Util3D.anguloSolido(face,vContrario);
		for(Iterator ite = vertices.iterator() ; ite.hasNext() ; ){
			Vertice verticeRef = (Vertice)ite.next();
			if(     !verticeRef.equals(face.getV1()) 
					&& !verticeRef.equals(face.getV2()) 
					&& !verticeRef.equals(face.getV3())
					
					)
			{
				double angTeste = Util3D.anguloSolido(face,verticeRef);
				if(angTeste!=0){
					if(angFaceRefVerticeContrario<0){
						if(angTeste>0)
							return false;
					}
					else if(angFaceRefVerticeContrario>0){
						if(angTeste<0)
							return false;
					}
				}
			}
			
		}
		
		return true;
	}
	
	public Tetraedro gerarTetraedroInicial(ArrayList vertices){
	
		Embrulho embrulho = new Embrulho(null);
		Face fInicial = embrulho.getFaceInicial(vertices );
		

		double maiorAngulo = Double.MIN_VALUE;
		
		
		Vertice vFinal = null;
		for(Iterator ite = vertices.iterator() ; ite.hasNext() ; ){
			Vertice verticeRef = (Vertice)ite.next();
			if(     !verticeRef.equals(fInicial.getV1()) 
					&& !verticeRef.equals(fInicial.getV2()) 
					&& !verticeRef.equals(fInicial.getV3()))
			{
			
				
				
			    double ang = Util3D.anguloSolido(fInicial,verticeRef);
				 
				 if(Math.abs(ang)>maiorAngulo){
					maiorAngulo = Math.abs(ang);
					vFinal = verticeRef;
				} 
				
				 
			}
		}
		
		Tetraedro tetraedro = new Tetraedro(fInicial.getV1(),fInicial.getV2(),
											fInicial.getV3(),vFinal); 
		return tetraedro;
		
	}
	
	
	
	
	 
	
	 
  
	
	 
 
	 
	 
	
	 
	
}
