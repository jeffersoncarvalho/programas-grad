
#pragma hdrstop
#include <condefs.h>


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include <conio.h>
#include <math.h>
int main(int argc, char* argv[])
{
        float delta,a,b,c,r1,r2;
        printf("\nEntre com os coeficientes:");
        scanf("%f%f%f",&a,&b,&c);
        delta = pow(b,2)-4*a*c;
        if (delta<0)
                printf("\nO numero nao possui raizes reais.");
        else
        {
                r1=(-b+sqrt(delta))/(2*a);
                r2=(-b-sqrt(delta))/(2*a);
                printf("\nAs raizes sao %f e %f.",r1,r2);
        }
        getch();

        return 0;
}
