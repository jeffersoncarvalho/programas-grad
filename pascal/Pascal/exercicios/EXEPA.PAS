program cpa;
uses crt;
var r,an,a1,n,sn: real;
    sair: string;

begin
clrscr;
      writeln('Digite primeiro termo,n� de termos e raz�o: ');
      readln(a1,n,r);
      while (a1) <>  0  do
      begin
      an:= a1 + (n-1)*r;
      writeln('O �ltimo termo �: ',an:10:0);
      sn:=(a1 + an)*n/2;
      writeln('A soma dos termos da PA �: ',sn:10:0);
      write('Digite primeiro termo,n� de termos e raz�o: ');
      readln(a1,n,r);
      end;
      end.
