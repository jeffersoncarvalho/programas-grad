program irpf;
uses crt;
var i,sb,sl,desc: real;
    cpf,dep: integer;


begin

     write('Digite o seu CPF: ');
     readln(cpf);
     while cpf <> 0 do
     begin
            write('Digite n§ de dependentes: ');
            readln(dep);
            desc:=dep*5;
            writeln('Seu deconto ser  de: ',desc:5:2,' reais');
            write('Digite o seu sal rio bruto: ');
            readln(sb);
            sl:=sb-desc;
            writeln('Seu sal rio l¡quido ser  de: ',sl:5:2,' reais');

            if sl <= 1000 then
               writeln('Contribuinte ISENTO do IRPF')
            else
                if sl <= 2000 then
                begin
                     i:=sl*0.05;
                     writeln('Seu imposto ser  de: ',i:5:2,' reais');
                end
                else
                    if sl <= 4000 then
                    begin
                         i:=sl*0.10;
                         write('Seu imposto ser  de: ',i:5:2,' reais');
                    end
                    else
                        begin
                             i:=sl*0.15;
                             write('Seu imposto ‚ de: ',i:5:2,' reais');
                        end;

            write('Digite novo CPF: ');
            readln(cpf);
     end;
end.