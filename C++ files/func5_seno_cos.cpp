
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
#include<math.h>
float seno_coseno(float xis,int troc)//Função
{
        int p,i,j,sinal,max,min;
        unsigned long int fat;
        float soma,valorfinal,tot,xe;

        soma=0;
        sinal=1;
        xe=1;
        fat=1;

        if (troc==1)//seno
        {
                min=1;
                max=33;
        }
        if (troc==0)//coseno
        {
                min=2;
                max=30;
        }


        for(i=min;i<=max;i+=2)
        {
                for(j=1;j<=i;j++)
                        xe*=xis;
                for(p=1;p<=i;p++)
                        fat*=p;
                tot=(xe)/(fat);

                tot*=sinal;
                soma+=tot;
                xe=1;
                fat=1;
                sinal*=-1;
         }


         if (troc==1)
                valorfinal=soma;
         if (troc==0)
                valorfinal=1-soma;

         return valorfinal;
}

void main()
{
        int troca;
        float x,g,rad,valorfinal;
        char opcao;

     do
     {
        do
        {
        clrscr();
        printf("ESCOLHA UMA OPCAO: ");
        printf("\n0:Achar o coseno;");
        printf("\n1:Achar o seno;");
        printf("\n");
        scanf("%d",&troca);
        }while((troca!=1)&&(troca!=0));
        printf("\nDigite um angulo em graus (0º a 90º): ");
        scanf("%f",&g);

        rad=(M_PI*g)/180;
        x=rad;

        valorfinal=seno_coseno(x,troca);

        if (troca==0)
                printf("\nO coseno de %4.2f e aprox. igaul a %1.20f",g,valorfinal);
        if (troca==1)
                printf("\nO seno de %4.2f e aprox. igual a %1.20f",g,valorfinal);
        printf("\nDeseja continuar(s|n)?: ");
        opcao=getche();
     }while(opcao!='n');
        getch();

}


