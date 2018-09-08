
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
#include<math.h>
float valor_seno(float xis)//Função
{
        int p,i,j,sinal;
        unsigned long int fat;
        float soma,seno,tot,xe;
        soma=0;
        sinal=1;
        xe=1;
        fat=1;
        for(i=1;i<=33;i+=2)//Laço principal cujo i inicializa como 1 e assume valores impares ate 51 ;
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

         seno=soma;
         return seno;
}

void main()//Programa Principal;
{


         float seno,g,rad;
         char resp;
         do
         {
         printf("\nDigite um angulo em graus: ");
         scanf("%f",&g);
         rad=(M_PI*g)/180;
         seno=valor_seno(rad);
         printf("O valor do seno de %5.2f e %2.8f",g,seno);
         fflush(stdin);
         printf("\nDeseja continuar?(s|n):" );
         scanf("%c",&resp);
         clrscr();
         }while(resp=='s');
}


