package locadora.produto;

import java.util.ArrayList;
import locadora.gui.AlimentaObjetos;
import locadora.produto.*;

public class Teste {
	
	public static void main(String args[])
	{
		
		//VHS vhs = AlimentaObjetos.alimentaVHS();
		//DVD dvd = AlimentaObjetos.alimentaDVD();
		//LP lp = AlimentaObjetos.alimentaLP();
		CD cd = AlimentaObjetos.alimentaCD();
		System.out.println (cd.toString());
		
	}
}
