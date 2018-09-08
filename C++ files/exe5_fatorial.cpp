
#pragma hdrstop
#include <condefs.h>


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include <conio.h>
int main(int argc, char* argv[])
{
        int n,i,fat;
        printf("\nDigite um numero:");
        scanf("%d",&n);
        fat=1;
        for(i=1;i<=n;i++)
                fat*=i;
        printf("\nO fatorial de %d e igual a %d.",n,fat);
        getch();
        return 0;
}
 