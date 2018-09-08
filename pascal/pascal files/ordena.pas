program ordena;
uses wincrt;
var a:array[1..10] of string;
    n,i,me,j:integer;
    aux:string;



begin
     write('Digite a qtd de funcionários: ');
     readln(n);

     for i:=1 to n do
      begin
           write('Digite o nome do ',i,'º funcionário: ');
           readln(a[i])
      end;


      for i:=1 to n-1 do
          for j:=n downto i+1 do
              if a[j]<a[j-1] then
               begin
                    aux:=a[j-1];
                    a[j-1]:=a[j];
                    a[j]:=aux;
               end;


     {for i:=1 to n do
      begin
           me:=i;
           for j:=i+1 to n do
               if (a[j]) < (a[me]) then
                  me:=j;
           aux:=a[i];
           a[i]:=a[me];
           a[me]:=aux;
      end;}

     for i:=1 to n do
      writeln(a[i]);

end.