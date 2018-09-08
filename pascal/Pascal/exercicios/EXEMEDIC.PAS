program medicao;
uses crt;
var cm,pol:real;

begin

     write('Digite um valor positivo qualquer em polegadas: ');
     readln(pol);

     while pol >= 0 do
     begin
          cm:=pol*2.54;
          writeln('O valor convertido ser  de: ',cm:5:2,'cm');
          write('Digite um novo valor em polegadas agora: ');
          readln(pol);
     end;
end.