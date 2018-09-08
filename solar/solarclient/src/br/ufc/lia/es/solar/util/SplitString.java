package br.ufc.lia.es.solar.util;

public class SplitString {

	public static String[] split(String str,char separator) {
		int inicio = 0;
		int pos = 0;
		int tam = 0;
		
		for(int i = 0; i < str.length(); i++) 
			if(str.charAt(i) == separator)
				tam++;
		String res[] = new String[tam];
		
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == separator)
			{
				String temp = str.substring(inicio, i);
				res[pos++] = temp;
				inicio = i + 1;
			}
			 
		} 
		 
		 return res;
	}
	
	public static void main(String[] args) {
		
		String teste = "nome-mat-idade-";
		String[] res = SplitString.split(teste,'#');
	   	for(int i=0;i<res.length;i++)
	   		System.out.println(res[i]);
		
	} 
}
