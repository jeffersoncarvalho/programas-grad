Program exerc10;
{Ler um valor em Real e a cotação do Dólar, calcular e exibir o valor em dólares.}
uses wincrt;
var dolar, rea : real;
begin
     write('Entre com a cotação do dólar: ');
     readln(dolar);
     write ('Entre com o valor em reais ou zero para finalizar: ');
     readln(rea);
     while rea <>0 do
     begin
           rea:=rea*dolar;
           writeln('O valor em dólares é: ',rea:7:2);
           write ('Entre com o valor em reais ou zero para finalizar: ');
           readln(rea);
     end;
     donewincrt;
end.