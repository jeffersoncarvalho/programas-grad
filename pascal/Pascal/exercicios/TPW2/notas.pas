Program Notas;
uses wincrt;
Var      P : Array [1..100] of Real;
	G : Array [1..100] of Real;
	M : Array [1..100] of Real;
	I,N:integer;

Begin
Repeat
         begin
	Write ('Entre com o número de alunos (Maximo 100):');
         Read (N);
         end
Until N <= 100;
For I := 1 to N do
	Begin
	Write ('1ª nota do aluno número ',I,':');
	Read (P[I]);
	Write ('2ª nota do aluno numero ',I,':');
         Read (G[I]);
         End;
For I := 1 to N do
	Begin
         M[I] := (P[I] + G[I]) / 2;
	Writeln ('A média do aluno numero ',I,' é: ',M[I]:5:2);
         End;
End.