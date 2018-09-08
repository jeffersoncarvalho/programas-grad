
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
int i=0;
void dec_bin_int(int n,int n_bin_int[])
{


        do
        {

                if ((n%2)==0.0)
                        n_bin_int[i]=0;
                else
                        n_bin_int[i]=1;
                n/=2;
                i+=1;
        }while(n!=0);

}
void dec_bin_frac(float n,int n_bin_frac[])
{
        float calc,pff;
        int o,pif;
        for(o=0;o<=5;o++)
        {
                calc=2*n;
                n_bin_frac[o]=calc/1;
                pif=n_bin_frac[o];
                pff=calc-pif;
                n=pff;
        }
}
void main()//Principal
{
        int pi,j,vet_int[50],vet_frac[50];
        float num,pf;
        printf("Digite um numero real positivo:");
        scanf("%f",&num);

        pi=num/1;
        pf=num-pi;
        dec_bin_int(pi,vet_int);
        dec_bin_frac(pf,vet_frac);

        printf("A representacao BINARIA:");
        printf("\n");
        for (j=(i-1);j>=0;j--)
                printf("%d",vet_int[j]);
        printf(",");
        for (j=0;j<=5;j++)
                printf("%d",vet_frac[j]);
        getch();
}

