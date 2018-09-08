program fato;
uses crt;
var n,a: integer;
    fat: real;
    resposta:string[1];

begin
     repeat;
     clrscr;
     write('Digite n§: ');
     readln(n);
     if n <> 0 then
     begin
          fat:=1;
          for a:=1 to n do
          fat:=fat*a;
          writeln('O valor do fatorial ser  de: ',fat:10:0);
          write('Repetir processo?(s/n): ');
          readln(resposta);
     end;
     until resposta <> 's';
end.
