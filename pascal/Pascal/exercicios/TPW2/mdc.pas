Program MDC;
uses wincrt;

Var a,b,c,: integer; 

BEGIN
	Writeln ('Entre com os dois n�meros:');
	Read (a,b);
	

	c:= a mod b;
	while c <> 0 do
	begin

		a:=b;
		b:=c;
		c:=a mod b;
	end
		writeln('O mdc �:',b);
end.