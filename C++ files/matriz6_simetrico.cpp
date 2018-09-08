
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
void lermatriz(int a[][10],int n,int m,char car)
{

        int i,j;
        for(i=0;i<n;i++)
         for(j=0;j<m;j++)
          {
                printf("Elemento %c[%d,%d]: ",car,i+1,j+1);
                scanf("%d",&a[i][j]);
          }
}
void exibe(int b[][10],int n, int m)
{
        int i,j;
        for(i=0;i<n;i++)
        {
         for(j=0;j<m;j++)
                printf(" %d ",b[i][j]);
          printf("\n");
         }
}
int teste(int c[][10],int n,int m)
{

        int i,j,simetrico=1;
         for(i=0;i<n;i++)
          for(j=0;j<m;j++)
           if(i!=j)
            if((c[i][j]!=c[j][i]))
                simetrico=0;

        return simetrico;

}
void main()
{
        int matriz[10][10],dim,resp;
        printf("Digite dimensoes: ");
        scanf("%d",&dim);
        printf("\n");
        lermatriz(matriz,dim,dim,'A');
        printf("\n");
        exibe(matriz,dim,dim);
        resp=teste(matriz,dim,dim);
        if (resp==0)
                printf("\nNAO E SIMETRICO");
        else
                printf("\nE SIMETRICO");

        getch();
}

