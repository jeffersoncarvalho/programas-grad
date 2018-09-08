//---------------------------------------------------------------------------

#pragma hdrstop
#include <string.h>
#include <conio.h>
#include <stdio.h>
#include <iostream.h>


//---------------------------------------------------------------------------

#pragma argsused
typedef char referencia[20];


//está função transforma qualquer cadeia em minúsculo.
char* toLowerCase(char cad[])
{
        int n=strlen(cad);
        for(int i=0;i<n;i++)
         if(int(cad[i])>=65 && int(cad[i])<=90)
          cad[i]=char(int(cad[i])+32);

       return cad;
}

//ordenando tudo em relação ao tabref. Questão 3b.
void ordenaVetor(referencia vetor[],int Vprec[] ,int Vsuc[], int tam)
{
 char aux[20],cad1[20],cad2[20];
 int auxP,auxS;

        for(int i=0;i<tam-1;i++)
         for(int j=i+1;j<tam;j++)
         {
           strcpy(cad1,vetor[i]);
           strcpy(cad2,vetor[j]);

           if(strcmp(toLowerCase(cad1),toLowerCase(cad2))>0)//Note que eu chamei toLowerCase para transformar tudo em minúsculo.
                                                            //Desta forma, eu ordeno corretamente.
           {
                //Ordeno o TabRef
                strcpy(aux,vetor[i]);
                strcpy(vetor[i],vetor[j]);
                strcpy(vetor[j],aux);
                //Ordeno o vetor precedente
                auxP=Vprec[i];
                Vprec[i]=Vprec[j];
                Vprec[j]=auxP;
                //Ordeno o vetor seguinte
                auxS=Vsuc[i];
                Vsuc[i]=Vsuc[j];
                Vsuc[j]=auxS;
           }
         }
}

//alimentando o vetor tabref
void alimenta(referencia vetor[])
{
        strcpy(vetor[4],"XC23a");
        strcpy(vetor[3],"Rcj2");
        strcpy(vetor[2],"HP4m");
        strcpy(vetor[1],"XXY-1");
        strcpy(vetor[0],"C4y");
}

//alimentando vetores prec e suc
void alimentaPrecSuc(int vPrec[],int vSuc[],int tam)
{
 for(int i=tam;i>0;i--)
  vPrec[i-1]=i-2;

 vSuc[tam-1]=-1;
 for(int i=tam-1;i>=0;i--)
  vSuc[i-1]=i;
}

//as dus funções abaixo são apenas para visualizar o resultados na tela
void mostraTabRef(referencia vetor[],int tam)
{
         printf("TabRef\n");
        for(int i=tam-1;i>=0;i--)
         printf("%s \n",vetor[i]);
}

void mostraPrecSuc(int vPrec[],int vSuc[],int tam)
{
        printf("\n Prec  Suc");
        for(int i=tam-1;i>=0;i--)
         printf("\n%d    %d",vPrec[i],vSuc[i]);
}

//Função referente à questão 3c
void exi(int n, referencia Vtabref[], int Vprec[], int Vsuc[])
{
        if(Vprec[n]>=0)
         exi(Vprec[n],Vtabref,Vprec,Vsuc);
        printf("%s\n",Vtabref[n]);
        if(Vsuc[n]>=0)
         exi(Vsuc[n],Vtabref,Vprec,Vsuc);
}

void main(void)
{
referencia tabref[30];
int nb=5;
int *prec=new int[nb];//aloco dinamicamente este vetor, com isto respondo a questão 3a
int *suc=new int[nb];//aloco dinamicamente este vetor, com isto respondo a questão 3a

//alimenta 3 vetores
alimenta(tabref);
alimentaPrecSuc(prec,suc,nb);
//antes de ordenar
mostraTabRef(tabref,nb);
ordenaVetor(tabref,prec,suc,nb);
printf("\n\n")   ;

//após a ordenação, com este procedimento respondo a questão 3b
printf("\n Apos a ordenacao. Ver folha do Nivando\n\n") ;
mostraTabRef(tabref,nb);
mostraPrecSuc(prec,suc,nb);

//Questao 3c.Um erro ocorre caso eu chame a função "exi()". Basta tirar os comentários...
//note que a minha função tem mais parametros do
//que a dada pelo professor. Isto se deve porque estou
//usando tabref,prec e suc como variáveis LOCAIS ;)
//exi(nb/2,tabref,prec,suc);



getch();

}
//---------------------------------------------------------------------------
