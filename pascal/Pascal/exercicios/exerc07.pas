Program exerc07;
{Ler um inteiro e informar se o mesmo � ou n�o um n�mero primo}
Uses wincrt;
Var
   n, i, cont : integer;
Begin
     clrscr;
     Write ('Digite o n�mero (0 para finalizar):  ');
     Readln (n);
     while n<>0 do
     begin
          cont := 0;
          for i := 2 to n - 1 do
              if (n mod i) = 0 then
                 cont := cont + 1;
          if (cont = 0) and (n <> 1) then
             writeln ('O n�mero � primo.')
          else
              writeln ('O n�mero n�o � primo.');
          Write ('Digite outro n�mero (0 para finalizar):  ');
          Readln (n);
     end;
     donewincrt;
end.