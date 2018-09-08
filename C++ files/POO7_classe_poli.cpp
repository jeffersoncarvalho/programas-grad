
#include <condefs.h>
#pragma hdrstop


//---------------------------------------------------------------------------
#pragma argsused
#include <conio.h>
#include <stdio.h>
#include <math.h>
class polinomio
{
        private:
                        int grau;
                        float a[30];
        public:
                void lepol()
                {
                        int i;
                        printf("\nEntre com o grau: ");
                        scanf("%d",&grau);
                        for(i=0;i<=grau;i++)
                        {
                                printf("\nCoef. de grau %d: ",i);
                                scanf("%f",&a[i]);
                        }
                }
                float px(float x)
                {
                        int i;
                        float soma=0;
                        for(i=0;i<=grau;i++)
                                soma+=pow(x,i)*a[i];
                        return soma;
                }
};
int main()
{
        polinomio p;
        float x;

        p.lepol();
        printf("\nValor para x: ");
        scanf("%f",&x);
        printf("\nP(x)=%10.2f",p.px(x));
        getch();
        return 0;
}
