program triangulo;
uses crt;
var a,b,c: integer;
    resposta: string[1];

begin
repeat
clrscr;
       write('Digite os lados do tri�ngulo: ');
       readln(a,b,c);

       if (a+b>c) and (b+c>a) and (a+c>b) then

          if (a=b) and (b=c) then
             writeln('O triangulo � equil�tero')
          else
              if (a<>b) and (b<>c) and (c<>a) then
                 writeln('O tri�ngulo � escaleno')
              else
                 writeln('O tri�ngulo � is�sceles')
       else
           writeln('N�O EXISTE TAL TRI�NGULO');
           write('Deseja continuar?(s/n): ');
           readln(resposta);
until resposta <> 's';
end.
