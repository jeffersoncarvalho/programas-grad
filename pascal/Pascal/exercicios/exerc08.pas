Program exerc08;
{Ler N notas, calcular e exibir a m�dia aritm�tica das mesmas.}
Uses wincrt;
Var
   n, i : integer;
   soma, nota, media : real;
Begin
     clrscr;
     write ('Digite a quantidade de notas: ');
     Readln (n);
     Soma := 0;
     for i := 1 to n do
     Begin
          Write ('Digite a ', i,'a. nota: ');
          Readln (nota);
          soma := soma + nota;
     End;
     media := soma / n;
     writeln ('A m�dia aritm�tica das notas � ', media:2:2,'.');
     readln;
     donewincrt;
End.