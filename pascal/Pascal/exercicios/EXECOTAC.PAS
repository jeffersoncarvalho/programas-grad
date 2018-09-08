program cotacao;
uses crt;
var dolar,rea,cot :real;

begin
     clrscr;
     write('Entre com a cota‡Æo do d¢lar ou 0 para finalizar: ');
     readln(cot);

     while cot > 0 do
     begin
          write('Entre com algum valor em reais: ');
          readln(rea);
          dolar:=rea/cot;
          writeln('O valor em dol res ser  de: ',dolar:5:2);

          write('Digite a cota‡ao anterior ou a atualizada ou 0 para finalizar: ');
          readln(cot);
     end;
end.

