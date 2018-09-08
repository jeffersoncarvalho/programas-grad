Program exerc15;
{Ler um inteiro e informar se o mesmo é ou não um número primo (usar comando goto)}
Uses wincrt;
Label primo, teste;
Var
   n, i, cont : integer;
Begin
     clrscr;
     Write ('Digite o número (0 para finalizar):  ');
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
             writeln ('O número é primo.')
          else
              writeln ('O número não é primo.');
          Write ('Digite outro número (0 para finalizar):  ');
          Readln (n);
          if n<>0 then goto primo;
     donewincrt;
end.