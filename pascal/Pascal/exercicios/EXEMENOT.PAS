program mediadenotas;
uses crt;
var n,i : integer;
    media,soma,nota: real;


begin
     clrscr;
     write('Digite n§ de notas: ');
     readln(n);

     while n > 0 do
     begin
         soma:=0;
         for i:=1 to n do
         begin
         write('Digite a ',i,' nota: ');
         readln(nota);
         soma:=soma + nota;
         end;
         media:= soma/n;
         writeln('A m‚dia ser :',media:5:2);
         write('Digite n§ de notas: ');
         readln(n);
     end;
end.