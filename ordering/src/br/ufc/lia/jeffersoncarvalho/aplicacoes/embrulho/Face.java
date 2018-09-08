package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho;

import java.util.ArrayList;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao.Tetraedro;

public class Face {

	private Vertice v1;
	private Vertice v2;
	private Vertice v3;
	public int orientacao = 0; //1 Verde pra dentro
							   //-1 Verde pra frente
	Tetraedro tcw;
	Tetraedro tccw;
	ArrayList arestas = new ArrayList(3);
	String res = "";
	
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
	public Face(Vertice v1, Vertice v2, Vertice v3) {
		super();
		// TODO Auto-generated constructor stub
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		 
		Aresta a1 = new Aresta(this.getV1(),this.getV2());
		Aresta a2 = new Aresta(this.getV2(),this.getV3());
		Aresta a3 =new Aresta(this.getV3(),this.getV1());
		 
		arestas.add(a1);
		arestas.add(a2);
		arestas.add(a3);
	}
	
	public boolean equals(Object obj) {
		
		Face ref = (Face)obj;
		if( (ref.getV1().equals(this.v1) || ref.getV1().equals(this.v2) ||ref.getV1().equals(this.v3) )
				&&
			(ref.getV2().equals(this.v1) || ref.getV2().equals(this.v2) ||ref.getV2().equals(this.v3))
			    &&
			(ref.getV3().equals(this.v1) || ref.getV3().equals(this.v2) ||ref.getV3().equals(this.v3)) 
			
		)
			return true;
		return false;
	}
	
	public ArrayList gerarArestas()
	{
		
		return arestas;
	}
	
	public boolean temAresta(Aresta a){
		boolean res = false;
		if((a.getV1().equals(this.v1) || a.getV1().equals(this.v2) || a.getV1().equals(this.v3))
			&&
			(a.getV2().equals(this.v1) || a.getV2().equals(this.v2) || a.getV2().equals(this.v3))
		  )
			return true;
		return res;
	}
	 
	public String toString() {
		
		return "v1: " +v1 + "\nv2: " + v2 + "\nv3: " + v3;
	}
	
	public String test(){
		return "{{"+v1.getX()+","+v1.getY()+","+v1.getZ()+"},{"+v2.getX()+","+v2.getY()+","+v2.getZ()+"},{"+v3.getX()+","+v3.getY()+","+v3.getZ()+"}}";
	}
	
	/*public String gerarRepresentacao(){
		String res = "";
		double xV[] = {this.getV1().getX(),this.getV2().getX(),this.getV3().getX()} ;
		//System.out.println(xV[0] + " " + xV[1] + " " +xV[2]);
		double yV[] = {this.getV1().getY(),this.getV2().getY(),this.getV3().getY()} ;
		double zV[] = {this.getV1().getZ(),this.getV2().getZ(),this.getV3().getZ()} ;
		
		Face.ordenacaoSimples(xV);
		//System.out.println("-->" + xV[0] + " " + xV[1] + " " +xV[2]);
		Face.ordenacaoSimples(yV);
		Face.ordenacaoSimples(zV);
		
		
		
		res = xV[0]+"&"+xV[1]+"&"+xV[2]+"_"+yV[0]+"&"+yV[1]+"&"+yV[2]+"_"+zV[0]+"&"+zV[1]+"&"+zV[2];
		 
		return res;
	}*/
	
	public boolean isLivre(){
		
		if(tcw==null && tccw==null)
			return true;
		return false;
	}
	public Tetraedro getTccw() {
		return tccw;
	}
	public void setTccw(Tetraedro tccw) {
		this.tccw = tccw;
	}
	public Tetraedro getTcw() {
		return tcw;
	}
	public void setTcw(Tetraedro tcw) {
		this.tcw = tcw;
	}
	
	public static void ordenacaoSimples(double[] vertices){
		for(int out=vertices.length-1; out>0; out--)  // outer loop (backward)
		     for(int in=0; in<out; in++)    // inner loop (forward)
		    	 if( vertices[in] > vertices[in+1] )    // out of order?
		    	 {
		    		 double aux = vertices[in];
		    		 vertices[in] = vertices[in+1];
		    		 vertices[in+1] = aux;                        
		    	 }
		    		  
	}
	
	public static void main(String[] args) {
		
		double[] t = {0,0,-8};
		Face.ordenacaoSimples(t);
		System.out.println(t[0]+ " " + t[1] + " " + t[2] );
	}
	
	public Vertice getCentro(){
		double x = (v1.getX() + v2.getX() + v3.getX())/3;
		double y = (v1.getY() + v2.getY() + v3.getY())/3;
		double z = (v1.getZ() + v2.getZ() + v3.getZ())/3;
		return new Vertice(x,y,z);
	}
	 
}
