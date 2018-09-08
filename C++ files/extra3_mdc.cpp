
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include <conio.h>
int mdc(int n1,int n2)
{
        int menor,maior,i,j,mdec;
        if (n1<n2)
        {
                menor=n1;
                maior=n2;
        }
        else
        {
                menor=n2;
                maior=n1;
        }
        for(i=1;i<=menor;i++)
         if (((menor%i)==0)&&((maior%i)==0))
                mdec=i;
        return mdec;
}

void main()
{
        int nu1,nu2,mdec;
        printf("\nDigite dois numeros inteiros: ");
        scanf("%d%d",&nu1,&nu2);
        mdec=mdc(nu1,nu2);

        printf("\nO mdc entre %d e %d e: %d",nu1,nu2,mdec);
        getch();

}
