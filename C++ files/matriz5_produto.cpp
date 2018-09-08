
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
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
void exibe(int b[][10],int n,int m)
{
        int i,j;
        for(i=0;i<n;i++)
        {
         for(j=0;j<m;j++)
                printf(" %d ",b[i][j]);
         printf("\n");
        }
}
void produto(int m1[][10],int m2[][10],int mp[][10],int n,int m,int t)
{
        int i,j,k;
        for(i=0;i<n;i++)
         for(j=0;j<m;j++)
                mp[i][j]=0;

         for(i=0;i<n;i++)
          for(j=0;j<m;j++)
           for(k=0;k<t;k++)
                mp[i][j]+=m1[i][k]*m2[k][j];
}

void main()
{
        int matriz1[10][10],matriz2[10][10],matprod[10][10],lin1,lin2,col1,col2;
        char cara;
        printf("Digite as dimensoes da primeira matriz: ");
        scanf("%d%d",&lin1,&col1);

        printf("\n***MATRIZ 1***");
        printf("\n");
        cara='A';
        lermatriz(matriz1,lin1,col1,cara);

        printf("Digite o numero de colunas da segunda matriz: ");
        scanf("%d",&col2);
        printf("\n***MATRIZ 2***");
        printf("\n");
        lin2=col1;
        cara='B';
        lermatriz(matriz2,lin2,col2,cara);

        getch();
        clrscr();

        printf("\n***MATRIZ A[i,j]***");
        printf("\n");
        exibe(matriz1,lin1,col1);
        printf("\n***MATRIZ B[i,j]***");
        printf("\n");
        exibe(matriz2,lin2,col2);

        printf("\n***MATRIZ PRODUTO***");
        printf("\n");
        produto(matriz1,matriz2,matprod,lin1,col2,col1);
        exibe(matprod,lin1,col2);
        getch();
}
