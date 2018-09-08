
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdlib.h>
#include<conio.h>
#include<stdio.h>
int main(int argc, char* argv[])
{
int *p,n,i;
        printf("Digite o tamanho do vetor: ");
        scanf("%d",&n);
        p=(int*) malloc(n*sizeof(int));
        for(i=0;i<n;i++)
        {
                printf("%d elemento: ",i+1);
                scanf("%d",p+i);
        }
        for(i=0;i<n;i++)
        {
                printf("\nO elemento %d e %d; ",i+1,*(p+i));
getch();
}


        return 0;
}
 