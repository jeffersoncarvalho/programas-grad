Program calculos;
Uses wincrt;    
Var A,I : INTEGER;
    POT:REAL;

Begin           
Writeln('Digite o n�mero desejado');
Readln(a);
If (A<0) then
   Writeln('N�o � possivel efetuar a operac�o')
Else

   Begin
   I:=1;
   FOR I:=1 TO 10 DO

	  POT:=exp(ln(a)*I);
	  writeln(A,'ELEVADO A',I,'�: ','exp(ln(a)*I) );

end;
End.


