
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
float pesquisa(float x);
class lista_encadeada
{

        protected:
                struct lista
                {float dado;lista *next;}*l;
        public:
                lista_encadeada()
                {l=NULL;}
                void exibir()
                {


                        if(l!=NULL)
                        {
                                printf("%5.2f",l->dado);
                                l=l->next;
                                exibir();
                        }

                }
                void inserir(float x)
                {
                        lista *aux,*ant;
                        if((l==NULL)||(l->dado>x))
                        {
                                aux=new lista;
                                aux->dado=x;
                                aux->next=l;
                                l=aux;
                        }
                        else
                        {
                                ant=l;
                                aux=l->next;
                                while((aux)&&(aux->dado)<x)
                                {
                                        ant=aux;aux=aux->next;
                                }
                                aux=new lista;
                                aux->dado=x;aux->next=ant->next;
                                ant->next=aux;
                        }
                }
                void eliminar(float x)
                {
                 if(pesquisa(x)==1)
                 {
                        lista *aux,*ant;
                        if((l==NULL)||(l->dado==x))
                        {
                              aux=l;
                              l=l->next;
                              delete aux;
                        }
                        else
                        {
                                ant=l;
                                aux=l->next;
                                while((aux)&&(aux->dado)!=x)
                                {
                                        ant=aux;aux=aux->next;
                                }

                                aux=ant->next;
                                ant->next=aux->next;
                                delete aux;
                        }
                 }
                 else
                        printf("\nValor não encontrado");
                }
                float pesquisa(float x)
                {
                        lista *aux;
                        aux=l;
                        float achou;
                        while((aux)&&(aux->dado!=x))
                                aux=aux->next;
                        achou=(aux!=NULL);
                        return achou;
                }
};
void main()
{
        lista_encadeada c;
        int i,tam,opcao;
        float x;
        bool alimentou;
do{

    do{
       printf("***MENU***");
       printf("\n1.Alimenta Lista");
       printf("\n2.Exibe Lista");
       printf("\n3.Exclui Elemento");
       printf("\n4.Pesquisa ");
       printf("\n5.SAIR");
       printf("\n");
       printf("Opcao: ");
       gotoxy(9,8);
       scanf("%d",&opcao);
       }while((opcao>5)||(opcao<1));

       switch(opcao)
       {
        case 1://ALIMENTAR
                printf("Qtd de Elementos: ");
                scanf("%d",&tam);
                for(i=1;i<=tam;i++)
                {
                        printf("Valor: ");
                        scanf("%f",&x);
                        c.inserir(x);
                }
                getch();
                alimentou=true;
        break;

        case 2://EXIBIR
         if(alimentou==true)
         {
             c.exibir();
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
                        printf(" \nValor: ");
                        scanf("%f",&x);
                        c.eliminar(x);
                        getch();
         }
         else
         {
                printf("\nAlimente a lista primeiro!");
                getch();
         }
        break;

        case 4://PESQUISAR
        if(alimentou==true)
        {
                printf("\nValor a Pesquisar: ");
                scanf("%f",&x);
                if (c.pesquisa(x)==1)
                        printf("\nValor Encontrado!");
                else
                        printf("\nValor não Encontrado.");
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
 }while((opcao>0)&&(opcao<5));

}
