Program calculos;
Uses wincrt;    
Var A,I : INTEGER;
    POT:REAL;

Begin           
Writeln('Digite o número desejado');
Readln(a);
If (A<0) then
   Writeln('Não é possivel efetuar a operacão')
Else

   Begin
   I:=1;
   FOR I:=1 TO 10 DO

	  POT:=exp(ln(a)*I);
	  writeln(A,'ELEVADO A',I,'É: ','exp(ln(a)*I) );

end;
End.


