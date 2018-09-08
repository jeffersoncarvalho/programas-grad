package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho;

import java.awt.Point;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.Ponto3D;

public class VerticeProjetadoEmR2 {

	 
	private double xR2;
	private double yR2;
	private Vertice vOriginal;
	 
	
	public Vertice getVOriginal() {
		return vOriginal;
	}



	public void setVOriginal(Vertice original) {
		vOriginal = original;
	}



	public VerticeProjetadoEmR2(double xr2, double yr2) {
		super();
		// TODO Auto-generated constructor stub
		xR2 = xr2;
		yR2 = yr2;
	}



	public VerticeProjetadoEmR2(Vertice vr3) {
		 
		
		
		 this.setVOriginal(vr3);
		 
		 double x = vr3.getX()+1.28;
		 double y = vr3.getY()+1.28;
		 double z = vr3.getZ()+1.28;
		 
		 //System.out.println("merda, fia da puta!");
		 double r = Math.sqrt(x*x + y*y + z*z );
		 //System.out.println(r);
		 double teta = Math.atan(y/x);
		 //System.out.println(teta);
		 double fi = Math.acos(z/r);
		// System.out.println(fi);
		// System.out.println(r*Math.sin(fi)*Math.cos(teta));
		// System.out.println(r*Math.sin(fi)*Math.sin(teta));
		 this.xR2 = r*Math.sin(fi)*Math.cos(teta);
		 this.yR2 = r*Math.sin(fi)*Math.sin(teta);
		 
		 
		 
	}
	
	 
 
	public double getX() {
		return xR2;
	}
	public void setX(double xr2) {
		xR2 = xr2;
	}
	public double getY() {
		return yR2;
	}
	public void setY(double yr2) {
		yR2 = yr2;
	}

	public Point toPoint(){
		 Point p = new Point();
		 p.setLocation(this.getX(),this.getY());
		 return p;
	}
	
	public Ponto3D toPonto3D(){
		return new Ponto3D((float)this.xR2,(float)this.yR2,0);
	}
	
	public boolean equals(Object obj) {
		
		VerticeProjetadoEmR2 vpr2 = (VerticeProjetadoEmR2)obj;
		if(vpr2.getX()==this.getX() && vpr2.getY()==this.getY())
			return true;
		return false;
	}
	
	public String toString() {
		
		return "x: " + this.xR2 + " y: " + this.yR2;
	}
}
