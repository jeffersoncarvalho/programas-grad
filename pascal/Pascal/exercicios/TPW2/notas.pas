Program Notas;
uses wincrt;
Var      P : Array [1..100] of Real;
	G : Array [1..100] of Real;
	M : Array [1..100] of Real;
	I,N:integer;

Begin
Repeat
         begin
	Write ('Entre com o n�mero de alunos (Maximo 100):');
         Read (N);
         end
Until N <= 100;
For I := 1 to N do
	Begin
	Write ('1� nota do aluno n�mero ',I,':');
	Read (P[I]);
	Write ('2� nota do aluno numero ',I,':');
         Read (G[I]);
         End;
For I := 1 to N do
	Begin
         M[I] := (P[I] + G[I]) / 2;
	Writeln ('A m�dia do aluno numero ',I,' �: ',M[I]:5:2);
         End;
End.