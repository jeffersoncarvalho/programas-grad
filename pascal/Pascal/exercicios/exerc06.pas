Program exerc06;
{Ler N, calcular e exibir N!.}
Uses wincrt;
Var
   n, a : integer;
   fat  : real;
Begin
     clrscr;
     Write ('Digite o n�mero (0 para finalizar): ');
     Readln (n);
     while n<>0 do
     begin
          fat := n;
          a := 1;
          While a <> n do
          Begin
               fat := fat * (n - a);
               a := a+1;
          End;
          Writeln ('O fatorial de ', n, ' � ', fat:10:0, '.');
          Write ('Digite outro n�mero (0 para finalizar): ');
          Readln (n);
     end;
     donewincrt;
end.
