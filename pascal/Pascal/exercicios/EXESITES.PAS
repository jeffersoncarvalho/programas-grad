program situacao;
uses crt;
var np1,np2,nf:real;
     med12,f:real;


begin

     write('Digite sua NP1: ');
     readln(np1);
     while np1 >=0 do
     begin
            write('Digite sua NP2: ');
            readln(np2);
            med12:= (np1+np2)/2;
            writeln('Sua m‚dia ser : ',med12:5:2);
            write('Digite sua nota da np final: ');
            readln(nf);
            f:=(med12 + nf)/2;

            if (f >=4) and (nf>=4)  then
               writeln('Vocˆ est  aprovado')
            else
               writeln('Vocˆ est  reprovado');

            write('Digite sua NP1: ');
            readln(np1);
     end;
end.



