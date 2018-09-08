Program exerc04;
{Ler um inteiro e informar se ele é par ou ímpar.}
Uses wincrt;
Var
   n : integer;
Begin
   clrscr;
   Write ('Digite o número (0 para finalizar): ');
   Readln(n);
   while n<>0 do
   begin
        If (n mod 2) = 0 then
           Writeln('O número é par')
        else
            Writeln('O número é ímpar');
        Write ('Digite outro número (0 para finalizar): ');
        Readln(n);
   end;
   donewincrt;
End.