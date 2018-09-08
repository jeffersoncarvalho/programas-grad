
#pragma hdrstop
#include <condefs.h>


//---------------------------------------------------------------------------
#pragma argsused
#include <stdio.h>
#include <conio.h>
int main(int argc, char* argv[])
{
        int n,i,primo;
        primo=1;
        for(n=2;n<=200;n++)
        {
          for(i=2;i<n;i++)
           if ((n%i)==0)
               primo=0;
           if (primo==1)
               printf("%d,",n);
          primo=1;
        }

          getch();
        return 0;
}
 