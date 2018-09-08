Program exerc14;
{Ler N, calcular e exibir N! (usar comando goto.}
Uses wincrt;
label fatorial, fator;
Var
   n, a : integer;
   fat  : real;
Begin
     clrscr;
     Write ('Digite o número (0 para finalizar): ');
     Readln (n);
     fatorial:
       if n<>0 then
       begin
          fat := n;
          a := 1;
          fator:
               fat := fat * (n - a);
               a   := a+1;
               if a<>n then goto fator;
          Writeln ('O fatorial de ', n, ' é ', fat:10:0, '.');
          Write ('Digite outro número (0 para finalizar): ');
          Readln (n);
          goto fatorial;
       end;
     donewincrt;
end.
