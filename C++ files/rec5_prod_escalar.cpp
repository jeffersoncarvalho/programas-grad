
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
void lervetor(int a[],int tam,int i)
{
        if (i<=tam)
        {
                printf("Digite o %d elemento: ",i);
                scanf("%d",&a[i]);
                i+=1;
                lervetor(a,tam,i);
        }

}
int prod_escalar(int v1[],int v2[],int tam,int i,int soma)
{


                soma+=(v1[i]*v2[i]);
                i+=1;
                return(i<=tam)?prod_escalar(v1,v2,tam,i,soma):soma;
}

void main()
{
        int resp,t,*vetor1,*vetor2;
        printf("Digite o tamanho dos vetores: ");
        scanf("%d",&t);
        vetor1=new int[t];
        vetor2=new int[t];
        printf("\n");
        printf("Vetor 1");
        printf("\n");
        lervetor(vetor1,t,1);
        printf("\n");
        printf("Vetor 2");
        printf("\n");
        lervetor(vetor2,t,1);
        printf("\n");

         resp=prod_escalar(vetor1,vetor2,t,1,0);
         printf("O valor do Produto Escalar e %d.",resp);
         getch();
}

