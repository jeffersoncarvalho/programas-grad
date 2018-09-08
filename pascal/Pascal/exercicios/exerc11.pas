Program exerc11;
{O salário de um funcionário é arredondado de forma a não conter centavos.
Construa um programa para ler o valor do salário de qualquer funcionário e exibir a
quantidade de cédulas de 100, 50, 5 e 1 de forma otimizada (menor número de cédulas)}
Uses wincrt;
Var s,cent,cinq,dez,five,unid:integer;
Begin
     clrscr;
     write('Digite a quantia do salário ou zero para finalizar:  ');
     readln(s);
     while s<>0 do
     begin
           if s>=100 then
           begin
                cent:= s div 100;
                s:= s mod 100;
           end;
           if s>=50 then
           begin
                cinq:= s div 50;
                s:= s mod 50;
           end;
           if s>=10 then
           begin
                dez:= s div 10;
                s:= s mod 10;
           end;
           if s>=5 then
           begin
                five:= s div 5;
                s:= s mod 5;
           end;
           if s>=1 then
           unid:=s;       
           writeln ('O funcionário receberá ',cent,' notas de 100 reais, ',dez, ' notas de dez reais, ');
           writeln (five,' nota de cinco reais e ',unid,' notas de um real.');
           repeat until keypressed;
           clrscr;
           write('Digite a quantia do salário ou zero para finalizar:  ');
           readln(s);
     end;
     donewincrt;
end.