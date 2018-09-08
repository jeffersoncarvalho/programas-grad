
#include <condefs.h>
#include <conio.h>
#include <stdio.h>
#include <stdlib.h>

struct lista
        {int tam; float valor[100];}l;

void orden_cres(lista &l)
{
        int i,j;
        float aux;

        for(i=0;i<l.tam-1;i++)
         for(j=i+1;j<l.tam;j++)
          if(l.valor[i] > l.valor[j])
          {
                aux=l.valor[i];
                l.valor[i]=l.valor[j];
                l.valor[j]=aux;
          }
}

void alimenta(lista &l)
{
        int i;
        for(i=0;i<l.tam;i++)
        {
                printf("Elemento %d: ",i+1);
                scanf("%f",&l.valor[i]);
        }
}

void incluir(lista &l,float x)
{
        int i=0,p;

       if(x<=l.valor[0])
         p=0;
       else
         if(x>=l.valor[l.tam-1])
                p=l.tam;
         else
                for(i=0;i<l.tam;i++)
                        if((x>=l.valor[i])&&(x<=l.valor[i+1]))
                                p=i+1;

        for(i=l.tam-1;i>=p;i--)
                l.valor[i+1]=l.valor[i];
        l.valor[p]=x;

        l.tam+=1;
}

void excluir(lista &l,int p)
{
        p=p-1;
        int i;
        for(i=p;i<l.tam-1;i++)
                l.valor[i]=l.valor[i+1];
        l.tam-=1;
}

void exibir(lista &l)
{
        int i;
        for(i=0;i<l.tam;i++)
         printf("%5.2f,",l.valor[i]);
}

/*int localizar(lista &l,int ini,int fim,float x)
{
        int meio=(ini+fim)/2;

        if(ini>fim)
                return -1;
        else
                if(x==l.valor[meio])
                                return meio+1;
                else
                        if(x>l.valor[meio])
                                return localizar(l,ini,fim,x);
                        else
                                return localizar(l,ini,meio-1,x);

}*/

int localizar(lista &l,float x)
{
        int ini=0,fim=l.tam-1,achou=0,meio;

        while((ini<=fim)&&(achou==0))
        {
                meio=(ini+fim)/2;
                if(x==l.valor[meio])
                        achou=1;
                else
                        if(x>l.valor[meio])
                                ini=meio+1;
                        else
                                fim=meio-1;
        }

        if(achou==0)
                return -1;
        else
                return meio+1;
}

void main()
{

        int opcao,r,pos;
        bool alimentou=false;
        float xis;
 do{

    do{
       printf("***MENU***");
       printf("\n1.Alimenta Lista");
       printf("\n2.Exibe Lista");
       printf("\n3.Exclui Elemento");
       printf("\n4.Inclui Elemento");
       printf("\n5.Pesquisa ");
       printf("\n6.SAIR");
       printf("\n");
       printf("Opcao: ");
       gotoxy(9,8);
       scanf("%d",&opcao);
       }while((opcao>6)||(opcao<1));

       switch(opcao)
       {
        case 1://ALIMENTAR
                printf("Qtd de Elementos: ");
                scanf("%d",&l.tam);
                alimenta(l);
                getch();
                alimentou=true;
                orden_cres(l);
        break;

        case 2://EXIBIR
         if(alimentou==true)
         {
             exibir(l);
             getch();
         }
         else
         {
                printf("\nAlimente a lista primeiro!");
                getch();
         }
        break;

        case 3://EXCLUIR
         if(alimentou==true)
         {
                exibir(l);
                do{
                        printf(" \nPosicao: ");
                        scanf("%d",&pos);

                        if((pos<=0) || (pos>l.tam))
                        {
                                printf("Posicao Incorreta!");
                                getch();
                                fflush(stdin);
                        }
                }while((pos<=0) || (pos>l.tam));
                excluir(l,pos);
                printf("\n");
                exibir(l);
                getch();
         }
         else
         {
                printf("\nAlimente a lista primeiro!");
                getch();
         }
        break;

         case 4://INCLUIR
          if(alimentou==true)
          {
                exibir(l);
                printf(" \nElemento a incluir: ");
                scanf("%f",&xis);
                incluir(l,xis);
                printf("\n");
                exibir(l);
                getch();
          }
          else
         {
                printf("\nAlimente a lista primeiro!");
                getch();
         }
        break;

        case 5://PESQUISAR
        if(alimentou==true)
        {
                printf("Elemento a pesquisar: ");
                scanf("%f",&xis);
                //r=localizar(l,0,l.tam-1,xis);
                r=localizar(l,xis);
                if(r<0)
                        printf("Not Found");
                else
                        printf("O elemento esta na posicao %d",r);
                getch();
        }
        else
         {
                printf("\nAlimente a lista primeiro!");
                getch();
         }
        break;
       }

        fflush(stdin);
        clrscr();
 }while((opcao>0)&&(opcao<6));
}
