
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
#include<malloc.h>
void lermatriz(int *a,int t)
{
        int i;
        for(i=0;i<t;i++)
        {
                printf("Digite elemento A[%d,%d]: ",i+1,i+1);
                scanf("%d",a+i);
        }
}
void exibematriz(int *b,int t)
{
        int i,j;
        for(i=0;i<t;i++)
        {
         for(j=0;j<t;j++)
          if(i==j)
                printf(" %d ",*(b+i));
          else
                printf(" 0 ");
          printf("\n");
         }
}
int procura(int c[],int t,int a,int b)
{
        int resp,i,j;
        for(i=0;i<t;i++)
         for(j=0;j<t;j++)
         {
          if(i==j)
           if((a==i)&&(b==j))
                 resp=*(c+i);
          if(i!=j)
           if((a==i)&&(b==j))
                 resp=0;
          }
        return resp;
}
void main()
{
        int *vetor,x,y,ordem,resp;
        printf("Digite a ordem da matriz diagonal: ");
        scanf("%d",&ordem);
        printf("\n");
        vetor=(int*) malloc(ordem*sizeof(int));
        lermatriz(vetor,ordem);
        //clrscr();
        printf("\n***MATRIZ DIAGONAL***");
        printf("\n");
        exibematriz(vetor,ordem);
        printf("\nDigite coordenada i j do elemento procurado: ");
        scanf("%d%d",&x,&y);
        if((x>ordem)||(y>ordem))
                printf("\nCOORDENADA INEXISTENTE!");
        if((x<=ordem)&&(y<=ordem))
        {
                resp=procura(vetor,ordem,x-1,y-1);
                printf("\nO numero A[%d,%d] e %d: ",x,y,resp);
        }
        getch();
}



