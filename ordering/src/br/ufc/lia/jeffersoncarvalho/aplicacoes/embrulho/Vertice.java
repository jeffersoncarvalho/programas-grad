package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.Ponto3D;

public class Vertice {

	private double x,y,z;
	private Vertice vPerturbado;
	private Vertice vAllPositive; 
	private int sinX;
	private int sinY;
	private int sinZ;
	
	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Vertice() {
		 
		vPerturbado = null;
		 
	}
	public Vertice(double x, double y, double z) {
		 
		this.x = x;
		this.y = y;
		this.z = z;
		
    	vAllPositive = verticeAllPositive();
		vPerturbado = perturbarVertice();
		 
		 
	}

	public Ponto3D toPonto3D()
	{
		return new Ponto3D((float)this.getX(),(float)this.getY(),(float)this.getZ());
	}
	public boolean equals(Object obj) {
		
		Vertice vref = (Vertice) obj;
		if(vref.getX() == this.getX() &&
		   vref.getY() == this.getY() &&
		   vref.getZ() == this.getZ())
			return true;
		return false;
	}
	
	private Vertice perturbarVertice(){
		 
		 
		
		double x = this.getX()+ Math.random()/40.8;
		double y = this.getY()+ Math.random()/40.8;
		double z = this.getZ()+ Math.random()/40.8;
		Vertice v =new Vertice();
		v.setX(x);
		v.setY(y);
		v.setZ(z);
		return v;
		 
	 
	}
	 
	private Vertice verticeAllPositive(){
		 
		 
		
		double x = this.getX()+ 1000; 
		double y = this.getY()+ 1000; 
		double z = this.getZ()+ 1000; 
		Vertice v =new Vertice();
		v.setX(x);
		v.setY(y);
		v.setZ(z);
		return v;
		 
	 
	}
	
	public String toString() {
		return "X = " + this.x + " Y = " + this.getY() + " Z = " + this.getZ(); 
	}

	public Vertice getVPerturbado() {
		return vPerturbado;
	}

	public void setVPerturbado(Vertice perturbado) {
		vPerturbado = perturbado;
	}
	
	public void move(double velocidadeX,double velocidadeY,double velocidadeZ){
		if(this.sinX<0)
			this.x-=(velocidadeX);
		else
			this.x+=(velocidadeX);
		
		if(this.sinY<0)
			this.y-=(velocidadeY);
		else
			this.y+=(velocidadeY);
		
		if(this.sinZ<0)
			this.z-=(velocidadeZ);
		else
			this.z+=(velocidadeZ);
		 
		
		 
	}
	
	public void setSins(int x, int y, int z){
		
		this.sinX = x;
		this.sinY = y;
		this.sinZ = z;
	}

	public Vertice getVAllPositive() {
		return vAllPositive;
	}
}
