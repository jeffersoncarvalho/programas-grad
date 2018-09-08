
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
void lermatriz(int a[][10],int n,int m)
{
        int i,j;
        for(i=0;i<n;i++)
         for(j=0;j<m;j++)
         {
                printf("Elemento A[%d,%d]: ",i+1,j+1);
                scanf("%d",&a[i][j]);
         }
}
void exibematriz(int b[][10],int n,int m)
{
        int i,j;
        for(i=0;i<n;i++)
        {
         for(j=0;j<m;j++)
                printf(" %d ",b[i][j]);
         printf("\n");
        }
}
void calculo(int a[][10],int b[][10],float x[],int n)
{
        int i,j;
        float soma;

        for(i=0;i<n;i++)
        {
         for(j=0;j<=i-1;j++)
                soma+=a[i][j]*x[j];
          x[i]=(b[i][0]-soma)/a[i][i];
          soma=0;
        }
}
void exibevetor(float x[],int n)
{
        int i;
        for(i=0;i<n;i++)
                printf("X[%d]= %2.2f, ",i+1,x[i]);
}

void main()
{
        int mat_1_termo[10][10],mat_2_termo[10][10],dim;
        float vet_resp[10];
        printf("Digite o numero de equacoes: ");
        scanf("%d",&dim);
        printf("\n***Matriz primeiro termo***");
        printf("\n");
        lermatriz(mat_1_termo,dim,dim);
        printf("\n***Matriz segundo termo***");
        printf("\n");
        lermatriz(mat_2_termo,dim,1);
        clrscr();
        printf("\n***Matriz primeiro termo***");
        printf("\n");
        exibematriz(mat_1_termo,dim,dim);
        printf("\n***Matriz segundo termo***");
        printf("\n");
        exibematriz(mat_2_termo,dim,1);
        calculo(mat_1_termo,mat_2_termo,vet_resp,dim);
        printf("\n***Vetor Resposta***");
        printf("\n");
        exibevetor(vet_resp,dim);
        getch();
}




