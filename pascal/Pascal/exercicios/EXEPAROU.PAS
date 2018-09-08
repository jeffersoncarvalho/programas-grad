program parouimpar;
uses crt;
var n :integer;

begin
     write('Digite um n§ inteiro ou 0 para sair: ');
     readln(n);

     while n <> 0 do
     begin
          if n mod 2 = 0 then
             writeln('O n§ ',n,' ‚ par')
          else
             writeln('O n§ ',n,' ‚ ¡mpar');

     write('Digite novo n§ inteiro ou 0 para sair: ');
     readln(n);
     end;
end.