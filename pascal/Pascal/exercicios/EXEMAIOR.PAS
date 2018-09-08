PROGRAM MAIOR;
USES CRT;
VAR X,Y,Z : INTEGER;
    RESPOSTA :STRING[1];
BEGIN
CLRSCR;
REPEAT
     WRITE('DIGITE 1§ N§: ');
     READLN(X);
     WRITE('DIGITE 2§ N§: ');
     READLN(Y);
     WRITE('DIGITE 3§ N§: ');
     READLN(Z);

     IF (X>Y) AND (X>Z) THEN
        WRITELN('MAIOR: ',X)
     ELSE
         IF (Y>X) AND (Y>Z) THEN
            WRITELN('MAIOR: ',Y)
         ELSE
            WRITELN ('MAIOR: ',Z);
     IF (X<Y) AND (X<Z) THEN
        WRITELN('MENOR: ',X)
     ELSE
         IF(Y<X) AND (Y<Z) THEN
            WRITELN('MENOR: ',Y)
         ELSE
             WRITELN('MENOR: ',Z);

     WRITE('DESEJA CONTINUAR(S/N)?: ');
     READLN(RESPOSTA);
UNTIL RESPOSTA<> 'S';
END.







