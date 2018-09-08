package br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Face;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Vertice;

public class Tetraedro {

	 Vertice v1;
	 Vertice v2;
	 Vertice v3;
	 Vertice v4;
	 
	 Face faces[];
	 private double velocidade = 0;
	private double velocidadeX;
	private double velocidadeY;
	private double velocidadeZ;
	
	 
	 
	public double getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}
	public Vertice getV1() {
		return v1;
	}
	public void setV1(Vertice v1) {
		this.v1 = v1;
	}
	public Vertice getV2() {
		return v2;
	}
	public void setV2(Vertice v2) {
		this.v2 = v2;
	}
	public Vertice getV3() {
		return v3;
	}
	public void setV3(Vertice v3) {
		this.v3 = v3;
	}
	public Vertice getV4() {
		return v4;
	}
	public void setV4(Vertice v4) {
		this.v4 = v4;
	}
	 
	public Face[] getFaces(){
		
		return faces;
	}
	
	public Tetraedro(Vertice v1, Vertice v2, Vertice v3, Vertice v4) {
		 
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
		this.velocidade = 0.3+(Math.random() /1.5);
		this.velocidadeX = 0.3+(Math.random() /1.5);
		this.velocidadeY = 0.3+(Math.random() /1.5);
		this.velocidadeZ = 0.3+(Math.random() /1.5);
		int r =  (int)(Math.random()*2);
     	int g =  (int)(Math.random()*2);
    	int b =  (int)(Math.random()*2);
    	int sinX;
    	int sinY;
    	int sinZ;
    	if(r==1)
    		 sinX=-1;
    	else
    		 sinX=1;
    	if(g==0)
    		 sinY=-1;
    	else
    		 sinY=1;
    	if(b==1)
    		 sinZ=-1;
    	else
    		 sinZ=1;
    	v1.setSins(sinX,sinY,sinZ);
    	v2.setSins(sinX,sinY,sinZ);
    	v3.setSins(sinX,sinY,sinZ);
    	v4.setSins(sinX,sinY,sinZ);
		
		this.faces = new Face[4];
		faces[0] = new Face(v1,v2,v3);
		faces[1] = new Face(v1,v2,v4);
		faces[2] = new Face(v1,v3,v4);
		faces[3] = new Face(v2,v3,v4);
		 
	}

	public Vertice getVerticeContrarioFace(Face face){
		
		if(v1!=face.getV1()&&v1!=face.getV2()&&v1!=face.getV3())
			return v1;
		if(v2!=face.getV1()&&v2!=face.getV2()&&v2!=face.getV3())
			return v2;
		if(v3!=face.getV1()&&v3!=face.getV2()&&v3!=face.getV3())
			return v3;
		return v4;
	}
	
	public boolean equals(Object obj) {
		 
		 
		Tetraedro tetraRef = (Tetraedro)obj;
		Face facesRef[] = tetraRef.getFaces();
		 
			if(!facesRef[0].equals(faces[0]) && !facesRef[1].equals(faces[0]) && !facesRef[2].equals(faces[0]) && !facesRef[3].equals(faces[0]))
				return false;
		 
			if(!facesRef[0].equals(faces[1]) && !facesRef[1].equals(faces[1]) && !facesRef[2].equals(faces[1]) && !facesRef[3].equals(faces[1]))
				return false;
		 
			if(!facesRef[0].equals(faces[2]) && !facesRef[1].equals(faces[2]) && !facesRef[2].equals(faces[2]) && !facesRef[3].equals(faces[2]))
				return false;
		 
			if(!facesRef[0].equals(faces[3]) && !facesRef[1].equals(faces[3]) && !facesRef[2].equals(faces[3]) && !facesRef[3].equals(faces[3]))
				return false;
		return true;
	}
	
	public void move(){
		this.v1.move(velocidadeX,velocidadeY,velocidadeZ);
		this.v2.move(velocidadeX,velocidadeY,velocidadeZ);
		this.v3.move(velocidadeX,velocidadeY,velocidadeZ);
		this.v4.move(velocidadeX,velocidadeY,velocidadeZ);
		if(this.velocidade- 0.003>0)
			this.velocidade -=0.003;
		else
			this.velocidade =0;
	}
}
