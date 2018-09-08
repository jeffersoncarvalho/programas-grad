
#pragma hdrstop
#include <condefs.h>


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include <conio.h>
int main(int argc, char* argv[])
{
        int n,i,primo;
        printf("\nDigite um numero:");
        scanf("%d",&n);
        for(i=2;i<n;i++)
         if ((n%i)==0)
               primo=0;

        if(primo==0 || n==1)
          printf("O numero nao e primo");
        else
          printf("O numero e primo");
          getch();
        return 0;
}
 