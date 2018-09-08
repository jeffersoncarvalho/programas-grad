package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui;


public class Ponto3D{
    
    private float x, y, z;

    public Ponto3D(){
	x = 0;
	y = 0;
	z = 0;
    }
    
    public Ponto3D(float xv, float yv, float zv){
	x = xv;
	y = yv;
	z = zv;
    }

    public void setX(float v){
	x = v;
    }

    public void setY(float v){
	y = v;
    }

    public void setZ(float v){
	z = v;
    }

    public float getX(){
	return x;
    }

    public float getY(){
	return y;
    }

    public float getZ(){
	return z;
    }

    public boolean equals(Object obj) {
    	Ponto3D vref = (Ponto3D) obj;
		if(vref.getX() == this.getX() &&
		   vref.getY() == this.getY() &&
		   vref.getZ() == this.getZ())
			return true;
		return false;
    }
}
