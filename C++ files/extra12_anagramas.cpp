
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
#include<string.h>
#include<vcl.h>
int fat(int n)
{
        int i,f=1;
        for(i=1;i<=n;i++)
            f*=i;
        return f;
}
void main()
{

        char cadeia[80],aux;
        int n,i,j=0;
         printf("Digite cadeia: ");
         gets(cadeia);
         n=strlen(cadeia);

         for(i=1;i<=(fat(n));i++)
         {
                aux=cadeia[j+1];
                cadeia[j+1]=cadeia[j];
                cadeia[j]=aux;
                printf("\n%s",cadeia);
                //Sleep(100);
                //clrscr();
                j++;
                if(j==n-1)
                        j=0;
         }

         getch();
}
