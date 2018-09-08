
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>

//CONTADORES:
void conta_p_f()//ok
{
        int cont=0;
        char nome[80],*p;
        printf("Nome: ");
        gets(nome);
        for(p=nome;*p;p++)
                cont+=1;
        printf("Tamanho: %d",cont);
}

void conta_p_w()//ok
{
        int cont=0;
        char nome[80],*p;
        printf("Nome: ");
        gets(nome);
        p=nome;
        while(*p)
        {
                cont+=1;
                p++;
        }
        printf("Tamanho: %d",cont);
}

void conta_v_f()//ok
{
        int cont=0,i;
        char nome[80];
        printf("Nome: ");
        gets(nome);
        for(i=0;nome[i];i++)
                cont+=1;
        printf("Tamanho: %d",cont);
}

void conta_v_w()//ok
{
        int cont=0,i=0;
        char nome[80];
        printf("Nome: ");
        gets(nome);
        while(nome[i])
        {
                cont+=1;i++;
        }
        printf("Tamanho: %d",cont);
}

//MAIÚSCULAS-minúsculas
void maimin_p_w()//ok
{
        char nome[80],*p;
        printf("Nome: ");
        gets(nome);
        p=nome;
        while(*p)
        {
                if((*p>='A')&&(*p<='Z'))
                        *p+=32;
                p++;
        }
        *p=NULL;
        printf("cadeia: %s",nome);
}

void maimin_p_f()//ok
{
        char nome[80],*p;
        printf("Nome: ");
        gets(nome);
        for(p=nome;*p;p++)
                if((*p>='A')&&(*p<='Z'))
                        *p+=32;

        *p=NULL;
        printf("cadeia: %s",nome);
}

void maimin_v_f()//ok
{
        char nome[80];
        int i;
        printf("Nome: ");
        gets(nome);
        for(i=0;nome[i];i++)
                if((nome[i]>='A')&&(nome[i]<='Z'))
                        nome[i]+=32;
        nome[i]=NULL;
        printf("cadeia: %s",nome);
}

void maimin_v_w()//ok
{
        char nome[80];
        int i=0;
        printf("Nome: ");
        gets(nome);
        while(nome[i])
        {
                if((nome[i]>='A')&&(nome[i]<='Z'))
                        nome[i]+=32;
                i++;
        }
        nome[i]=NULL;
        printf("cadeia: %s",nome);
}

//PESQUISA CARACTERE:
void pesquisa_p_w()//ok
{
        char nome[80],*p,x;
        int cont=0;
        printf("Nome: ");
        gets(nome);
        printf("Caractere: ");
        scanf("%c",&x);
        p=nome;
        while(*p)
        {
                if(*p==x)
                        cont+=1;
                p++;
        }

        printf("Aparece %d vezes.",cont);
}

void pesquisa_p_f()
{
        char nome[80],*p,x;
        int cont=0;
        printf("Nome: ");
        gets(nome);
        printf("Caractere: ");
        scanf("%c",&x);
        for(p=nome;*p;p++)
                if(*p==x)
                        cont+=1;

        printf("Aparece %d vezes.",cont);
}

void pesquisa_v_f()
{
        char nome[80],x;
        int cont=0,i;
        printf("Nome: ");
        gets(nome);
        printf("Caractere: ");
        scanf("%c",&x);
        for(i=0;nome[i];i++)
                if(nome[i]==x)
                        cont+=1;
        printf("Aparece %d vezes.",cont);
}

void pesquisa_v_w()//ok
{
        char nome[80],x,*p;
        int cont=0;
        printf("Nome: ");
        gets(nome);
        printf("Caractere: ");
        scanf("%c",&x);
        p=nome;
        while(*p)
        {
                if(*p==x)
                        cont+=1;
                p++;
        }
        printf("Aparece %d vezes.",cont);
}

void main()
{
        conta_p_f();
        conta_p_w();
        conta_v_f();
        conta_v_w();

        maimin_p_w();
        maimin_p_f();
        maimin_v_f();
        maimin_v_w();

        pesquisa_p_w();
        pesquisa_p_f();
        pesquisa_v_f();
        pesquisa_v_w();

        getch();
}


