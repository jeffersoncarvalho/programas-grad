program decrescente;
uses wincrt;
var x,y,z,aux:integer;
begin
    writeln('entre com 3numeros');
    readln(x,y,z);
    if(x<y) or (x<z) then
    begin
        if (y<z) then
        begin
        aux:=x;
        x:=z;
        z:=aux;
        end
        else
        begin
	   aux:=x;
	   x:=y;
	   y:=aux;
        end;
    end;
    if (y<z) then
    begin
        aux:=y;
        y:=z;
        z:=aux;
    end;
    writeln('os numeros em ordem decrescente s�o: ');
    writeln(x,',',y,',',z);
end.