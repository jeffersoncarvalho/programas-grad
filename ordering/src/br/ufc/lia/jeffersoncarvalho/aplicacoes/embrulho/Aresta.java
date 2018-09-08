package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho;


public class Aresta {

	private Vertice v1;
	private Vertice v2;
	//private Face faceDeGiro;
	//caso eu use we
	private Face fcw;
	private Face fccw;
	private String rep;
	private String repInversa;
	
	public Face getFccw() {
		return fccw;
	}
	public void setFccw(Face fccw) {
		this.fccw = fccw;
	}
	public Face getFcw() {
		return fcw;
	}
	public void setFcw(Face fcw) {
		this.fcw = fcw;
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
	public Aresta(Vertice v1, Vertice v2) {
		super();
		// TODO Auto-generated constructor stub
		if(v1.equals(v2))
		{
			System.out.println("Erro. Arestas de tamanho zero.");
			
		}
		this.v1 = v1;
		this.v2 = v2;
		this.setRep(this.geraRepresentacao());
		this.setRepInversa(this.geraRepresentacaoInversa());
	}
	
	public boolean equals(Object obj) {
		Aresta arestaRef = (Aresta)obj;
		if( (arestaRef.getV1().equals(this.getV1()) && 
		     arestaRef.getV2().equals(this.getV2())) 
		     ||
		     (arestaRef.getV1().equals(this.getV2()) && 
			  arestaRef.getV2().equals(this.getV1()))
		   )
			  return true;
		return false;
	}
	
	 
	public String toString() {
		String res = "";
		res+="V1: " + this.getV1();
		res+="\nV2: " + this.getV2();
		return res;
		
		 
	}
	
	private String geraRepresentacao(){
		 
		String s = 
		    v1.getX() + "&" + 
		    v1.getY() + "&" + 
		    v1.getZ() + "->" + 
		    v2.getX() + "&" + 
		    v2.getY() + "&" +  
		    v2.getZ();
		return s;
	}
	
	private String geraRepresentacaoInversa(){
		 
		String s = 
		    v2.getX() + "&" + 
		    v2.getY() + "&" + 
		    v2.getZ() + "->" + 
		    v1.getX() + "&" + 
		    v1.getY() + "&" +  
		    v1.getZ();
		return s;
	}
	
	public boolean isLivre(){
		if(this.fccw==null || this.fcw==null)
			return true;
		return false;
	}
	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	public String getRepInversa() {
		return repInversa;
	}
	public void setRepInversa(String repInversa) {
		this.repInversa = repInversa;
	}
	
	/*public Face getFaceDeGiro() {
		return faceDeGiro;
	}
	public void setFaceDeGiro(Face faceDeGiro) {
		this.faceDeGiro = faceDeGiro;
	}*/
}
