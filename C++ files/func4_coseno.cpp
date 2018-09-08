
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
#include<math.h>
float valor_coseno(float xis)//Função
{
        int p,i,j,sinal;
        unsigned long int fat;
        float soma,coseno,tot,xe;
        soma=0;
        sinal=1;
        xe=1;
        fat=1;
        for(i=2;i<=32;i+=2)//Laço principal cujo i inicializa como 1 e assume valores impares ate max ;
        {
                for(j=1;j<=i;j++)//Laço que eleva o numero dado a uma potencia impar;
                        xe*=xis;
                for(p=1;p<=i;p++)//Laço que calcula o fatorial do numero referente a potencia impar;
                        fat*=p;
                tot=(xe)/(fat);//Calculo da divisao entre os resultados do 1º e 2º Laços;

                tot*=sinal;
                soma+=tot;
                xe=1;
                fat=1;
                sinal*=-1;
         }

         coseno=1-soma;
         return coseno;
}

void main()//Programa Principal;
{


         float coseno,g,rad;
         char resp;
         do
         {
         printf("\nDigite um angulo em graus: ");
         scanf("%f",&g);
         rad=(M_PI*g)/180;
         coseno=valor_coseno(rad);
         printf("O valor do coseno de %5.5f e %1.10f",g,coseno);
         fflush(stdin);
         printf("\nDeseja continuar?(s|n):" );
         scanf("%c",&resp);
         clrscr();
         }while(resp=='s');
}


