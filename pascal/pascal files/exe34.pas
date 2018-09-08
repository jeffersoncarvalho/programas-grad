program exe34;
uses wincrt;
type T_aluno = record
             matr:longint;
             nome:string[30];
             nota:real;
     end;
     T_sala = record
            n_alunos:0..50;
            Dado : Array [1..50] of T_Aluno;
     end;

var b:t_sala;
    i,j:integer;
    aux:t_aluno;
    
    

begin
     write('Digite nº de alunos: ');
     readln(b.n_alunos);

     for i:=1 to b.n_alunos do
      begin
         write('Digite o nome do ',i,'º aluno: ');
         readln(b.dado[i].nome);
         write('Digite sua matricula: ');
         readln(b.dado[i].matr);
         write('Digite sua nota: ');
         readln(b.dado[i].nota);
      end;


      for i:=1 to b.n_alunos-1 do
          for j:=b.n_alunos downto i+1 do
              if b.dado[j].nome < b.dado[j-1].nome then
               begin
                    aux:=b.dado[j-1];
                    b.dado[j-1]:=b.dado[j];
                    b.dado[j]:=aux;
               end;

      writeln('ALUNOS APROVADOS');
      for i:=1 to b.n_alunos do
        if b.dado[i].nota >= 7 then
           writeln(b.dado[i].nome);

end.
      