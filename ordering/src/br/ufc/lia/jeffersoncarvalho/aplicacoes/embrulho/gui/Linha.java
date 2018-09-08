package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui;
public class Linha{
    
    private Ponto3D p1, p2;
    private Cor cor;

    public Linha(){
	p1 = new Ponto3D();
	p2 = new Ponto3D();
	cor = new Cor();
    }
    
    public Linha(Ponto3D pp1, Ponto3D pp2, Cor c){
	p1 = pp1;
	p2 = pp2;
	cor = c;
    }

    public void setP1(Ponto3D p){
	p1 = p;
    }

    public void setP2(Ponto3D p){
	p2 = p;
    }

    public void setCor(Cor c){
	cor = c;
    }

    public Ponto3D getP1(){
	return p1;
    }

    public Ponto3D getP2(){
	return p2;
    }

    public Cor getCor(){
	return cor;
    }
}
