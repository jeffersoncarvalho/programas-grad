program matrizsimetrica;
uses wincrt;
var a:array[1..10,1..10] of integer;
    m,n,i,j,sim:integer;
    r:char;

begin
clrscr;
repeat
      write('Digite n§ de linhas e colunas da matriz: ');
      readln(m,n);
      for i:=1 to m do
          for j:=1 to n do
           begin
                write('Digite o [',i,',',j,'] elemento da matriz: ');
                readln(a[i,j]);
           end;
           writeln('*****MATRIZ A[i,j]*****');
      for i:=1 to m do
       begin
          for j:=1 to n do
           begin
                write('      ');
                write(a[i,j]);
                write('  ');
           end;
                writeln;
       end;

      for i:=1 to m do
          for j:=1 to n do
              if i <> j then
                 if a[i,j]=a[j,i] then
                    sim:=1
                 else
                    sim:=0;


      if (sim=1) then
         writeln('****MATRIZ SIMETRICA****')
      else
         writeln('****MATRIZ NAO SIMETRICA****');




       write('Deseja continuar(s\n)?: ');
       readln(r);
until r <> 's'
end.
