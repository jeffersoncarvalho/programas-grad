package br.ufc.lia.jeffersoncarvalho.aplicacoes.teste;

import java.util.ArrayList;

import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Face;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Util3D;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.Vertice;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao.Tetraedralizacao;
import br.ufc.lia.jeffersoncarvalho.aplicacoes.tetraedralizacao.Tetraedro;
import br.ufc.lia.jeffersoncarvalho.primivasgeometricas.PrimitivasGeometricas;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	 
		
		/*Vertice v1 = new Vertice(-20,20,-20);
		
		Vertice v2 = new Vertice(20,20,-20);
		Vertice v3 = new Vertice(-20,-20,-20);
		Vertice v4 = new Vertice(20,-20,-20);
		
		
		
		//frente de f1
		Vertice v5 = new Vertice(-20,20,20);
		Vertice v6 = new Vertice(20,20,20);
		Vertice v7 = new Vertice(20,-20,20);
		Vertice v8 = new Vertice(-20,-20,20);
		
		ArrayList vertices = new ArrayList();
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
		vertices.add(v5);
		vertices.add(v6);
		vertices.add(v7);
		vertices.add(v8);
		 
		
		Tetraedro t = new Tetraedro(v3,v4,v8,v2);
		Tetraedro t1 = new Tetraedro(v8,v4,v3,v2);
		 System.out.println(t1.equals(t));
		 int r =  (int)(Math.random()*2);
		 System.out.println(r);
		 
		 double a[] = {0,0,5};
		 double c[] = {-50,-10,5};
		 double b[] = {0,0,0};
		 System.out.println(PrimitivasGeometricas.dist(b,c));*/
		
		double[] tv = {6,-8,-9};
		double[] origem = {0,0,0};
		System.out.println(PrimitivasGeometricas.dist(tv,origem));
		 
	 
	}

}
