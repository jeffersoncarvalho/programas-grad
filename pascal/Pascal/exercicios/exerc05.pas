Program exerc05;
{Ler as notas NP1, NP2 e NF de um aluno e informar se o mesmo está aprovado ou reprovado.}
Uses wincrt;
Var
   NP1, NP2, NF    : Real;
   Media1, Media2  : Real;
   Resposta        : string[1];
begin
     repeat
           clrscr;
           Write ('Digite a nota da NP1:  ');
           Readln (NP1);
           Write ('Digite a nota da NP2:  ');
           Readln (NP2);
           Write ('Digite a nota da NF:  ');
           Readln (NF);
           Media1 := (NP1 + NP2) / 2;
           Media2 := (Media1 + NF) / 2;
           If (Media1 >= 4) and (Media2 >= 5) and (NF >= 4) then
              writeln ('aprovado(a)')
           else
              writeln ('reprovado(a)');
           write('Continua (s/n): ');
           readln(resposta);
     until resposta<>'s';
     donewincrt;
end.