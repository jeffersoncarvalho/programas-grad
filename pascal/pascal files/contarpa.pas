program contapalavras;
uses wincrt;
var a:string;
    i,s:integer;
    r:char;
    cp:integer;

begin
clrscr;
repeat

     write('Digite uma cadeia: ');
     readln(a);
     s:=0;
     cp:=1;
      for i:=1 to length(a) do
       begin
          if ((a[i]) <> ' ') and (cp=1) then
           begin
             s:=s+1;
             cp:=0;
           end;
          if (a[i]) = ' ' then
             cp:=1
       end;
     writeln('O nº de palavras e de: ',s);
     write('Deseja Continuar?(s\n): ');
     readln(r);
 until r <> 's'
end.
