
#pragma hdrstop
#include <condefs.h>


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include <conio.h>
int main(int argc, char* argv[])
{
        int num1;
        printf("\nEntre com um valor inteiro: ");
        scanf("%d",&num1);
        if (num1%2==0)
                printf("\nO numero e par.");
        else
                printf("\nO numero e impar.");
        getch();
        return 0;
}
 