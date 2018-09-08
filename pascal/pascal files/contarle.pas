Program contarletras;
uses wincrt;
var a: string;
    i,s:integer;
    j,r:char;

begin
clrscr;
  repeat
      write('Digite uma cadeia(numerica ou de letras): ');
      readln(a);
      write('Digite a letra ou nº desejad(o)a: ');
      readln(j);
      s:=0;
        for i:=1 to length(a) do
          if a[i]=j then
          s:=s+1;
      writeln('A qtd da letra ou nº "',j,'" na cadeia e de: ',s);
      write('Deseja continuar?(s\n): ');
      readln(r);
  until r <> 's'
end.