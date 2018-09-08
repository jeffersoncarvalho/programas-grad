package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui;


public class Triangulo {

	 private Ponto3D p1;
	 private Ponto3D p2;
	 private Ponto3D p3;
	 
	 
	public Triangulo(Ponto3D p1, Ponto3D p2, Ponto3D p3) {
		super();
		// TODO Auto-generated constructor stub
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		 
	}
	public Ponto3D getP1() {
		return p1;
	}
	public void setP1(Ponto3D p1) {
		this.p1 = p1;
	}
	public Ponto3D getP2() {
		return p2;
	}
	public void setP2(Ponto3D p2) {
		this.p2 = p2;
	}
	public Ponto3D getP3() {
		return p3;
	}
	public void setP3(Ponto3D p3) {
		this.p3 = p3;
	}
	 
	public boolean equals(Object obj) {
		
		Triangulo ref = (Triangulo)obj;
		if( (ref.getP1().equals(this.p1) || ref.getP1().equals(this.p2) ||ref.getP1().equals(this.p3) )
				&&
			(ref.getP2().equals(this.p1) || ref.getP2().equals(this.p2) ||ref.getP2().equals(this.p3))
			    &&
			(ref.getP3().equals(this.p1) || ref.getP3().equals(this.p2) ||ref.getP3().equals(this.p3)) 
			
		)
			return true;
		return false;
	}

}
