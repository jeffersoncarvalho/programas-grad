program fatorial;
uses crt;
var n,i: integer;
    fat :real;

begin
     clrscr;
     write('Digite um nº inteiro positivo: ');
     readln(n);

     while n >0 do   
     begin
          fat:=1;
          for i:=1 to n do
          fat:=fat*i;

          writeln('O valor do fatorial de ',n,' é: ',fat:7:0);

          write('Digite novo nº inteiro positivo: ');
          readln(n);
     end;
end.

