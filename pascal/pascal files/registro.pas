program exeregistro;
uses wincrt;
type registro = record
     mat:longint;
     nome:string[30];
     salario:longint;
     end;

     campo= array[1..50] of registro;
var a:campo;
    aux:registro;
    med:real;
    i,j,n:integer;



begin
     write('Digite a qtd de funcionários: ');
     readln(n);

     for i:=1 to n do
      begin
         write('Digite o nome do ',i,'º funcionário: ');
         readln(a[i].nome);
         write('Digite seu salário: ');
         readln(a[i].salario);
         write('Digite sua matricula: ');
         readln(a[i].mat);
      end;


      for i:=1 to n-1 do
          for j:=n downto i+1 do
              if a[j].salario <a[j-1].salario then
               begin
                    aux:=a[j-1];
                    a[j-1]:=a[j];
                    a[j]:=aux;
               end;

      for i:=1 to n do
        writeln(a[i].nome);

      if n mod 2 = 0 then
         med:=(a[n div 2].salario + a[(n+2) div 2].salario)/2
      else
         med:= a[(n+1) div 2].salario;

      writeln('A mediana dos salarios é: ',med:5:2);
end.