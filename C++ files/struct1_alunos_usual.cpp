
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
#include<malloc.h>
#include<string.h>
struct aluno
{
        char *nome;
        int mat;
        float nota;
};

void alimenta(aluno a[],int n)
{
        int i;
        for(i=0;i<n;i++)
        {
                printf("\nAluno %d",i+1);
                printf("\n");
                fflush(stdin);
                printf("Nome: ");
                a[i].nome=new char(50);
                gets(a[i].nome);
                printf("Matricula: ");
                scanf("%d",&a[i].mat);
                printf("Nota: ");
                scanf("%f",&a[i].nota);
        }
}

void ordena(aluno a[],int n)
{
        int i,j;
        struct aluno aux;

        for(i=0;i<n-1;i++)
         for(j=i+1;j<n;j++)
           if(strcmp(a[i].nome,a[j].nome)>0)
           {
                aux=a[i];
                a[i]=a[j];
                a[j]=aux;
           }
}

void exibe(aluno a[], int n)
{
        int i;
        for(i=0;i<n;i++)
        {
                printf("\nAluno %d: ", i+1);
                printf("\n%s",a[i].nome);
                printf("\n%d",a[i].mat);
                printf("\n%5.2f",a[i].nota);
                printf("\n");
        }
}

void media(aluno a[], int n)
{
        float media,soma=0;
        int i;
        for(i=0;i<n;i++)
                soma+=a[i].nota;
        media=soma/n;

        printf(" \nAlunos abaixo da media %5.2f:",media);
        printf("\n");
                for(i=0;i<n;i++)
                        if(a[i].nota<media)
                        {
                                printf(" %s ",a[i].nome);
                                printf("\n");
                        }
}

void main()
{
        struct aluno vetor[80];
        int tam;

        printf("Qtd alunos: ");
        scanf("%d",&tam);
        alimenta(vetor,tam);
        clrscr();
        ordena(vetor,tam);
        exibe(vetor,tam);
        media(vetor,tam);
        getch();
}


