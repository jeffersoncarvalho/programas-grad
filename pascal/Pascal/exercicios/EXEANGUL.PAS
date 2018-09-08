PROGRAM ANGULOS;
USES CRT;
VAR A,B,C : INTEGER;
    R: STRING[1];

BEGIN
CLRSCR;
REPEAT
     WRITE('DIGITE LADOS DO TRIANGULO: ');
     READLN(A,B,C);
     IF (A+B>C) AND (B+C>A) AND (A+C>B) THEN
        IF (A>B) AND (A>C) THEN
           IF (SQR(A)= SQR(B) + SQR(C)) THEN
              WRITELN('RET¶NGULO')
           ELSE
               IF(SQR(A)> SQR(B) + SQR(C)) THEN
                  WRITELN('OBTUS¶NGULO')
               ELSE
                  WRITELN('ACUTANGULO')
        ELSE
             IF (B>A) AND (B>C) THEN
                IF (SQR(B)= SQR(A) + SQR(C)) THEN
                   WRITELN('RET¶NGULO')
                ELSE
                    IF(SQR(B)> SQR(A) + SQR(C)) THEN
                       WRITELN('OBTUS¶NGULO')
                    ELSE
                       WRITELN('ACUTANGULO')
        ELSE
             IF (SQR(C)= SQR(A) + SQR(B)) THEN
                   WRITELN('RET¶NGULO')
                ELSE
                    IF(SQR(C)> SQR(A) + SQR(B)) THEN
                       WRITELN('OBTUS¶NGULO')
                    ELSE
                       WRITELN('ACUTANGULO')
     ELSE
         WRITELN('NAUM EXISTE TRIANGULO');
         WRITE('CONTINUA?: ');
         READLN(R);
UNTIL R <> 'S'
END.

