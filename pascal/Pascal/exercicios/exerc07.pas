Program exerc07;
{Ler um inteiro e informar se o mesmo é ou não um número primo}
Uses wincrt;
Var
   n, i, cont : integer;
Begin
     clrscr;
     Write ('Digite o número (0 para finalizar):  ');
     Readln (n);
     while n<>0 do
     begin
          cont := 0;
          for i := 2 to n - 1 do
              if (n mod i) = 0 then
                 cont := cont + 1;
          if (cont = 0) and (n <> 1) then
             writeln ('O número é primo.')
          else
              writeln ('O número não é primo.');
          Write ('Digite outro número (0 para finalizar):  ');
          Readln (n);
     end;
     donewincrt;
end.