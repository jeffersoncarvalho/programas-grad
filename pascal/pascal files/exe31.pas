program exe31;
uses wincrt;
type tempo=record
     h,min,s:longint;
     end;
var a:array[1..3] of tempo;
    t1,t2,total:longint;

begin
     write('Digite o primeiro tempo(hh:mm:ss): ');
     readln(a[1].h,a[1].min,a[1].s);
     write('Digite o sgundo tempo(hh:mm:ss): ');
     readln(a[2].h,a[2].min,a[2].s);

     t1:=a[1].h*3600 + a[1].min*60 + a[1].s;
     t2:=a[2].h*3600 + a[2].min*60 + a[2].s;

     total:=abs(t1-t2);

     a[3].h:=total div 3600;
     a[3].min:= (total - 3600*a[3].h) div 60;
     a[3].s:= total -(3600*a[3].h + 60*a[3].min);

     write('A diferença é: ',a[3].h,':',a[3].min,':',a[3].s);

end.