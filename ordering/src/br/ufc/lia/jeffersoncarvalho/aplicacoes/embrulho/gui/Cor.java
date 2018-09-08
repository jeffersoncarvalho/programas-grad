package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui;
public class Cor{
    
    private float r, g, b;
    private Cor anterior;

    public Cor(){
	r = 0;
	g = 1;
	b = 1;
	anterior = null;
    }
    
    public Cor(float rv, float gv, float bv){
	r = rv;
	g = gv;
	b = bv;
	anterior = null;
    }

    public void setR(float v){
	r = v;
    }

    public void setG(float v){
	g = v;
    }

    public void setB(float v){
	b = v;
    }

    public void setAnterior(Cor c){
	anterior = c;
    }

    public float getR(){
	return r;
    }

    public float getG(){
	return g;
    }

    public float getB(){
	return b;
    }

    public Cor getAnterior(){
	return anterior;
    }
}
