Program exerc15;
{Ler um inteiro e informar se o mesmo � ou n�o um n�mero primo (usar comando goto)}
Uses wincrt;
Label primo, teste;
Var
   n, i, cont : integer;
Begin
     clrscr;
     Write ('Digite o n�mero (0 para finalizar):  ');
     Readln (n);
     primo:
          cont := 0;
          i:=2;
          teste:
                if (n mod i) = 0 then
                   cont := cont + 1;
                i := i+1;
                if i<n-1 then goto teste;
          if (cont = 0) and (n <> 1) then
             writeln ('O n�mero � primo.')
          else
              writeln ('O n�mero n�o � primo.');
          Write ('Digite outro n�mero (0 para finalizar):  ');
          Readln (n);
          if n<>0 then goto primo;
     donewincrt;
end.