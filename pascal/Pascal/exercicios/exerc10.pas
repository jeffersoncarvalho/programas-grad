Program exerc10;
{Ler um valor em Real e a cota��o do D�lar, calcular e exibir o valor em d�lares.}
uses wincrt;
var dolar, rea : real;
begin
     write('Entre com a cota��o do d�lar: ');
     readln(dolar);
     write ('Entre com o valor em reais ou zero para finalizar: ');
     readln(rea);
     while rea <>0 do
     begin
           rea:=rea*dolar;
           writeln('O valor em d�lares �: ',rea:7:2);
           write ('Entre com o valor em reais ou zero para finalizar: ');
           readln(rea);
     end;
     donewincrt;
end.