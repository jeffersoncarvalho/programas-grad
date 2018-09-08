program matrizidentidade;
uses wincrt;
var a:array[1..10,1..10] of integer;
    m,n,i,j:integer;
    r:char;
    s,z:boolean;

begin
clrscr;
repeat
      write('Digite a ordem da matriz: ');
      readln(m);
      for i:=1 to m do
          for j:=1 to m do
           begin
                write('Digite o [',i,',',j,'] elemento da matriz: ');
                readln(a[i,j]);
           end;
           writeln('*****MATRIZ A[i,j]*****');
      for i:=1 to m do
       begin
          for j:=1 to m do
           begin
                write('      ');
                write(a[i,j]);
                write('  ');
           end;
                writeln;
       end;
      for i:=1 to m do
          for j:=1 to m do
           begin
                if j=i then
                   if a[i,j] = 1 then
                      s:=true;
                if j<>i then
                   if a[i,j]=0 then
                      z:=true
                   else
                    begin
                      s:=false;
                      z:=false;
                    end;
           end;
      if (s=true) and (z=true) then
         writeln('*****IDENTIDADE*****')
      else
         writeln('*****NAO IDENTIDADE*****');
       write('Deseja continuar(s\n)?: ');
       readln(r);
until r <> 's'
end.
