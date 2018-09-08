program salario;
uses crt;
var cem,cinq,dez,cinc,um,s,cent:longint;
    resposta: string[1];

begin
clrscr;
       repeat
       write('Digite seu sal rio: ');
       readln(s);


       cem:=s div 100;
       s:= s - (cem*100);
       cinq:=s div 50;
       s:= s - (cinq*50);
       dez:= s div 10;
       s:= s - (dez*10);
       cinc:= s div 5;
       s:= s - (cinc*5);
       um:= s;
               writeln('Notas de cem: ',cem);
               writeln('Notas de cinquenta: ',cinq);
               writeln('Notas de dez: ',dez);
               writeln('Notas de cinco: ',cinc);
               writeln('Notas de um: ',um);

               write('Deseja continuar?(s/n): ');
               readln(resposta);

       until resposta <> 's'

end.
