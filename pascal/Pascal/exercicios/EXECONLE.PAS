{RECEBE UMA PALAVRA,CONTA SUAS LETRAS E CONTA A LETRA DESEJADA}



PROGRAM CONTARLETRAS;
USES CRT;
VAR I,S: INTEGER;
    J,RESP: CHAR;
    A:STRING;

BEGIN
CLRSCR;
REPEAT

      WRITE('DIGITE A CADEIA: ');
      READLN(A);

      WRITE('DIGITE A LETRA PROCURADA: ');
      READLN(J);
           S:=0;
           FOR I:=1 TO LENGTH(A) DO
           BEGIN
                IF J=A[I] THEN
                S:=S+1
                ELSE
                S:=S;
           END;
               WRITELN('A QTD DE LETRAS "',J,'" NA CADEIA E DE: ',S);

               WRITE('DESEJA CONTINUAR?(S\N): ');
               READLN(RESP);
UNTIL RESP <> 'S'
END.


