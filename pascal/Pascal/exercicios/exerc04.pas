Program exerc04;
{Ler um inteiro e informar se ele � par ou �mpar.}
Uses wincrt;
Var
   n : integer;
Begin
   clrscr;
   Write ('Digite o n�mero (0 para finalizar): ');
   Readln(n);
   while n<>0 do
   begin
        If (n mod 2) = 0 then
           Writeln('O n�mero � par')
        else
            Writeln('O n�mero � �mpar');
        Write ('Digite outro n�mero (0 para finalizar): ');
        Readln(n);
   end;
   donewincrt;
End.