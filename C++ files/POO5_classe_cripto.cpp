
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include<conio.h>
#include<stdio.h>
#include<string.h>
class cripto
{
        protected:
                char cad[80];
                bool cript;
        public:
                void atribuir(char *c)
                {
                        strcpy(cad,c);
                        cript=false;
                }
                char *recupera()
                {
                        return cad;
                }
                bool estado()
                {
                        return cript;
                }
                void alfabeto()
                {
                        int i;
                        for(i=0;cad[i];i++)
                        {
                                switch(cad[i])
                                {
                                        case 'A':
                                                cad[i]='Z';
                                                break;
                                        case 'B':
                                                cad[i]='Y';
                                                break;
                                        case 'C':
                                                cad[i]='X';
                                                break;
                                        case 'D':
                                                cad[i]='W';
                                                break;
                                        case 'E':
                                                cad[i]='V';
                                                break;
                                        case 'F':
                                                cad[i]='U';
                                                break;
                                        case 'G':
                                                cad[i]='T';
                                                break;
                                        case 'H':
                                                cad[i]='S';
                                                break;
                                        case 'I':
                                                cad[i]='R';
                                                break;
                                        case 'J':
                                                cad[i]='Q';
                                                break;
                                        case 'K':
                                                cad[i]='P';
                                                break;
                                        case 'L':
                                                cad[i]='O';
                                                break;
                                        case 'M':
                                                cad[i]='N';
                                                break;
                                        case 'N':
                                                cad[i]='M';
                                                break;
                                        case 'O':
                                                cad[i]='L';
                                                break;
                                        case 'P':
                                                cad[i]='K';
                                                break;
                                        case 'Q':
                                                cad[i]='J';
                                                break;
                                        case 'R':
                                                cad[i]='I';
                                                break;
                                        case 'S':
                                                cad[i]='H';
                                                break;
                                        case 'T':
                                                cad[i]='G';
                                                break;
                                        case 'U':
                                                cad[i]='F';
                                                break;
                                        case 'V':
                                                cad[i]='E';
                                                break;
                                        case 'W':
                                                cad[i]='D';
                                                break;
                                        case 'X':
                                                cad[i]='C';
                                                break;
                                        case 'Y':
                                                cad[i]='B';
                                                break;
                                        case 'Z':
                                                cad[i]='A';
                                                break;
                                }
                        }
                }
                void criptografar()
                {
                        int i;
                        //alfabeto(); //Tirar este comentario caso queira outro tipo de codificação...

                       for(i=0;cad[i];i++) //Caso tire o comentário acima(alfabeto()), colocar em comentário daqui(for...) até o (...NULL).
                                cad[i]+='[';
                        cad[i]=NULL;

                        cript=true;
                }
                void descriptografar()
                {
                        int i;
                       // alfabeto(); //IDEM.

                        for(i=0;cad[i];i++)//IDEM.
                                cad[i]-='[';
                        cad[i]=NULL;

                        cript=false;
                }



};
int main()
{
        cripto c;
        char cad[80];
        int op;

        do{
                printf("\n1.Entra com mensagem;");
                printf("\n2.Codificar;");
                printf("\n3.Decodificar;");
                printf("\n4.Ver mensagem;");
                printf("\n5.Sair;");
                printf("\nOpcao: ");
                scanf("%d",&op);

                switch(op)
                {
                        case 1:
                                fflush(stdin);
                                printf("Digite: ");
                                gets(cad);
                                c.atribuir(cad);
                                clrscr();
                                break;
                        case 2:
                                c.criptografar();
                                printf("Ok!");
                                getch();
                                clrscr();
                                break;
                        case 3:
                                c.descriptografar();
                                printf("Ok!");
                                getch();
                                clrscr();
                                break;
                        case 4:
                                printf("\n%s",c.recupera());
                                if(c.estado()==true)
                                        printf("\nCodificado!");
                                else
                                        printf("\nNao Codificado");
                                getch();
                                clrscr();
                                break;
                        }
        }while(op!=5);
        return 0;

}

