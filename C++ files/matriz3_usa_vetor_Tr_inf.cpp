
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<stdio.h>
#include<conio.h>
#include<malloc.h>
void lermatriz(int *a,int t)
{
        int i,j,o=0;
        for(i=0;i<t;i++)
         for(j=0;j<t;j++)
                if(i>=j)
                {
                        printf("Elemento A[%d,%d] :",i+1,j+1);
                        scanf("%d",a+o);
                        o+=1;
                }
}
void exibematriz(int *b,int t)
{
        int i,j,o=0;
        for(i=0;i<t;i++)
        {
         for(j=0;j<t;j++)
                if(i>=j)
                {
                        printf(" %d ",*(b+o));
                        o+=1;
                }
                else
                        printf(" 0 ");
         printf("\n");
        }
}
int procura(int *c,int t,int a,int b)
{
        int i,j,o=0,resp;
        for(i=0;i<t;i++)
         for(j=0;j<t;j++)
                if(i>=j)
                {
                  if((a==i)&&(b==j))
                         resp=*(c+o);
                  o+=1;
                }
                else
                  if((a==i)&&(b==j))
                         resp=0;
         return resp;
}
void main()
{
        int *vetor,x,y,resp,ordem,n;
        printf("Digite a ordem da matriz tringular inferior: ");
        scanf("%d",&ordem);
        n=(ordem*(ordem-1))/2;
        vetor=(int*) malloc(n*sizeof(int));//Alocação do vetor no Heap
        printf("\n");
        lermatriz(vetor,ordem);
        //clrscr();
        printf("\n");
        printf("***MATRIZ TRIANGULAR INFERIOR***");
        printf("\n");
        exibematriz(vetor,ordem);
        printf("\nDigite coordenada i j a ser procurada: ");
        scanf("%d%d",&x,&y);
        if ((x>ordem)||(y>ordem))
         printf("\nCOORDENADA INEXISTENTE!");
        if ((x<=ordem)&&(y<=ordem))
         {
                resp=procura(vetor,ordem,x-1,y-1);
                printf("\nO valor A[%d,%d] e %d.",x,y,resp);
         }
         getch();
}


