/*
 NESTA CLASSE ESTÃO LISTADAS EM UM VETOR PRINCIPAL, TODAS AS PALAVRAS RESERVADAS DA GRAMÁTICA
 PASCALE. A PARTIR DO MÉTODO pertenceReservada(algumaString) É FEITO UM TESTE SOBRE A CADEIA PASSADA
 PARA SABER SE ELA PERTENCE AO CONJUNTO DE RESERVADAS.
*/
import java.awt.*;
import javax.swing.*;

public class PalavrasReservadas
{
    
    
    public PalavrasReservadas()
    {
    
                  
    }//construtor
    
    public boolean pertenceReservada(String cad)
    {
    	String vetStringsReservadas[]={"program","if","then","else","begin","end","while","do","array","function","procedure","integer","real","E","var","of","not"};
    	
        for(int i=0;i<vetStringsReservadas.length;i++)
            if(cad.equals(vetStringsReservadas[i]))
                return true;
        return false;
    }
    
    public boolean pertenceOpMultiplicacao(String cad)
    {
    	String vetOpMult[]={"div","mod","and"};
    	
    	for(int i=0;i<vetOpMult.length;i++)
            if(cad.equals(vetOpMult[i]))
                return true;
        return false;
    }
}

