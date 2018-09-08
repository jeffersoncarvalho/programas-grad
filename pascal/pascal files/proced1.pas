program ordena;
uses wincrt;
type vetor=array[1..100] of string;
var a:vetor;
    n:integer;



procedure ordem(var b:vetor;o:integer);
var  i,j: integer;
      aux:string;
begin
     write('Digite nº de funcionários: ');
     readln(o);
      
     for i:=1 to o do
      begin
           write('Digite o nome do ',i,'º funcionário: ');
           readln(b[i])
      end;

      for i:=1 to o-1 do
          for j:=o downto i+1 do
              if b[j]<b[j-1] then
               begin
                    aux:=b[j-1];
                    b[j-1]:=b[j];
                    b[j]:=aux;
               end;
     for i:=1 to o do
      writeln(b[i]);

end;

begin    
  
ordem(a,n);

end.