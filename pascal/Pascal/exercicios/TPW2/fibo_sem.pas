Program Fibonacci_sem_Vetor;
uses wincrt;

Var N,I,a,b,c : integer; 

BEGIN
	Repeat
		Write ('Entre com N(Máximo 100): ');
          Read (N);
	Until (N <= 100) and (N <> 0);
	a:=0;
	b:=1;
	For I := 3 to N do
          Begin
		c := a + b;
		a := b;
          b := c;
		End;
	If N < 3
	then
		If N = 2
		then
			Write ('O ',N,'º termo de Fibonacci é: ',b)
		Else
			Write ('O ',N,'º termo de Fibonacci é: ',a)
     Else
	Write ('O ',N,'º termo de Fibonacci é: ',c);
END.