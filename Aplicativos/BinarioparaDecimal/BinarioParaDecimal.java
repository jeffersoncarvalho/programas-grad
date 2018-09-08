import javax.swing.JOptionPane;
public class BinarioParaDecimal
{
	public static void main(String args[])
	{

    int numero=100000000;
    String cadeia;

    while(numero>99999999)
    {
      cadeia=JOptionPane.showInputDialog("Entre com binario de no máximo 8 bits");
      numero=Integer.parseInt(cadeia);
      if(numero>99999999)
        JOptionPane.showMessageDialog(null,"Máximo 8 bits!","Aviso",JOptionPane.WARNING_MESSAGE);
    }

    int x1,x2,x3,x4,x5,x6,x7,x8;
        x1=x2=x3=x4=x5=x6=x7=x8=0;

    if(numero>=10000000)
    {x1=numero/10000000;
     numero=numero-x1*10000000;}

     if(numero>=1000000)
     {x2=numero/1000000;
      numero=numero-x2*1000000;}

      if(numero>=100000)
      {x3=numero/100000;
       numero=numero-x3*100000;}

       if(numero>=10000)
       {x4=numero/10000;
        numero=numero-x4*10000;}

        if(numero>=1000)
        {x5=numero/1000;
         numero=numero-x5*1000;}

         if(numero>=100)
         {x6=numero/100;
          numero=numero-x6*100;}

          if(numero>=10)
          {x7=numero/10;
           numero=numero-x7*10;}

           if(numero>=0)
            x8=numero;

         int total=0;
         total+=x8*1+x7*2+x6*4+x5*8+x4*16+x3*32+x2*64+x1*128;
         JOptionPane.showMessageDialog(null,"O valor em decimal é: "+total);
         System.exit(0);
	}
}
