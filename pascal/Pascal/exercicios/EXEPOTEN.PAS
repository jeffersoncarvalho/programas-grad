PROGRAM QUADRADO;
USES CRT;
VAR N,I: INTEGER;
    POT: REAL;


BEGIN
CLRSCR;
     WRITE('DIGITE Nß: ');
     READLN(N);

     WHILE N >0 DO
     BEGIN
         I:=1;
         FOR I:= 1 TO 10 DO
         WRITELN(N,' ELEVADO A ',I,' ê IGUAL A: ',EXP(LN(N)*I):10:0);
         WRITE('DIGITE NOVO Nß: ');
         READLN(N)

     END;
END.