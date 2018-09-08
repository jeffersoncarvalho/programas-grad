/**
 *|:::::::::::::;;::::::::::::::::::|
  |:::::::::::'~||~~~``:::::::::::::|
  |::::::::'   .':     o`:::::::::::|
  |:::::::' oo | |o  o    ::::::::::|
  |::::::: 8  .'.'    8 o  :::::::::|
  |::::::: 8  | |     8    :::::::::|
  |::::::: _._| |_,...8    :::::::::|
  |::::::'~--.   .--. `.   `::::::::|
  |:::::'     =8     ~  \ o ::::::::|
  |::::'       8._ 88.   \ o::::::::|
  |:::'   __. ,.ooo~~.    \ o`::::::|
  |:::   . -. 88`78o/:     \  `:::::|
  |::'     /. o o \ ::      \88`::::|   "He will join us or die."
  |:;     o|| 8 8 |d.        `8 `:::|
  |:.       - ^ ^ -'           `-`::|
  |::.                          .:::|
  |:::::.....           ::'     ``::|
  |::::::::-'`-        88          `|
  |:::::-'.          -       ::     |
  |:-~. . .                   :     |
  | .. .   ..:   o:8      88o       |
  |. .     :::   8:P     d888. . .  |
  |.   .   :88   88      888'  . .  |
  |   o8  d88P . 88   ' d88P   ..   |
  |  88P  888   d8P   ' 888         |
  |   8  d88P.'d:8  .- dP~ o8       |   
  |      888   888    d~ o888    LS |
  |_________________________________|
 */
package br.ufc.lia.jeffersoncarvalho.primivasgeometricas;

import java.awt.Point;
import java.util.ArrayList;

public class PrimitivasGeometricas {

	private final static int SOMA = 0;
	private final static int DIFERENCA = 1;
	private static double[] operacao(double[] x, double[] y, int operador){

		double res[] = null; 
		if(x.length==y.length)
		{
			res = new double[x.length];
			for(int i=0;i<x.length;i++){
				
				double temp = 0;
				if(operador==PrimitivasGeometricas.SOMA)
					temp = x[i] + y[i];
				else if(operador==PrimitivasGeometricas.DIFERENCA)
					temp = x[i] - y[i];
				res[i] = temp;
			}
		}
		return res;
	}
	
	public static ArrayList createVector(Point p1, Point p2){
		ArrayList res = new ArrayList();
		res.add(new Double(p2.getX()-p1.getX()));
		res.add(new Double(p2.getY()-p1.getY()));
		return res;
	}
	
	 
	
	
	public static double[] soma(double[] x, double[] y){
	
		return operacao(x,y,PrimitivasGeometricas.SOMA);
	}
	
	public static double[] diferenca(double[] x, double[] y){
		
		return operacao(x,y,PrimitivasGeometricas.DIFERENCA);
	}
	
	public static double produtoEscalar(double[] x, double[] y){

		double res = 0;
		if(x.length==y.length)
		{
			double temp = 0;
			for(int i=0;i<x.length;i++){
				temp += x[i]*y[i];	 
			}
			return temp;
		}
		return res;
	}
	
 
	public static double norma(double[] x){

		
		 
		double temp = 0;
		for(int i=0;i<x.length;i++){
			temp += x[i]*x[i] ;	 
		}
		temp = Math.sqrt(temp);
		
		 
		return temp;
	}
	
	public static double dist(double[] x, double[] y){
		double[] v = PrimitivasGeometricas.diferenca(x,y);
		return PrimitivasGeometricas.norma(v);
		 
	}
	 
 
	/**
	 * Angulo não orientado entre dois vetores no R2
	 * @param x
	 * @param y
	 * @return
	 */
	public static double ang(double[] x, double[] y){
		
		double prodEscalarXY = PrimitivasGeometricas.produtoEscalar(x,y);
		double normaX = PrimitivasGeometricas.norma(x);
		double normaY = PrimitivasGeometricas.norma(y);
		double ang = Math.acos(prodEscalarXY
								/ 
								 (normaX*normaY)  );
		
		return ang;
	}
	 
	/*
	 * Tomando em relação vetor unitario (1,0)
	 */
	public static double angOrientado(double[] x){
		
		double x2 =  x[1];
		double u[] = {1,0};
		 
		if(x2>=0)
			return PrimitivasGeometricas.ang(x,u);
		else
			return  2*Math.PI - PrimitivasGeometricas.ang(x,u) ;
		 
	}

	 
	
	public static double[] produtoVetorialR3(double[] x, double[] y){ 
		double res[] = new double[3];
		res[0] = x[1]*y[2] - x[2]*y[1];
		res[1] = x[2]*y[0] - x[0]*y[2];
		res[2] = x[0]*y[1] - x[1]*y[0];
		return res;
	}
   
}
