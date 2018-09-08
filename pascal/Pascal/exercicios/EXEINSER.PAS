PROGRAM INSERORD;
USES CRT;
VAR I,J,N,AUX,IM: INTEGER;
    A:ARRAY[1..100] OF INTEGER;

BEGIN
CLRSCR;
     WRITE('DIGITE QTD DE N§: ');
     READLN(N);
     WHILE N > 0 DO
     BEGIN
          FOR I:=1 TO N DO
          BEGIN
               WRITE('DIGITE O ',I,' N§: ');
               READLN(A[I]);
          END;
          FOR I:=1 TO N-1 DO
          BEGIN
               IM:=I;
               FOR J:=I+1 TO N DO
                IF A[J] < A[IM] THEN
                   IM:=J;
               AUX:=A[I];
               A[I]:=A[IM];
               A[IM]:=AUX;
          END;
              WRITE('"');
          FOR I:=1 TO N DO
              WRITE(A[I],',');
              WRITELN('"');
              WRITE('DIGITE NOVA QTD DE N§: ');
              READLN(N);
     END;
END.