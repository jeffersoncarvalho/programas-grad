import javax.swing.*;

public class TwelveDaysOfXmas
{
	public static void main(String args[])
	{
		String versoPrin1="On the ";
    String versoPrinc2=" day of Christmas, my true love gave to me ";
    String day1="and a partridge in a pear tree",
           day2="two turtles doves",
           day3="three french hens",
           day4="four calling birds",
           day5="five gold rings",
           day6="six geese a-laying",
           day7="seven swans a swin",
           day8="eigth maids a-milking",
           day9="nine ladies waiting",
           day10="ten lords a leaping",
           day11="eleven pipes piping",
           day12="twelve drummers drumming";
   //String output="";
    JTextArea areaTexto=new JTextArea(25,30);
    JScrollPane barraRolagem=new JScrollPane(areaTexto);

    for(int day=1;day<=12;day++)
    {
      switch(day)
      {
        case 1:
          areaTexto.append(versoPrin1 + "first" + versoPrinc2 + day1 +"\n\n");
          break;
        case 2:
           areaTexto.append(versoPrin1 + "second" + versoPrinc2 + day2 +"\n"
          +day1+"\n\n");
          break;
        case 3:
          areaTexto.append(versoPrin1 + "third" + versoPrinc2 + day3 +"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 4:
           areaTexto.append(versoPrin1 + "fourth" + versoPrinc2 + day4 +"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 5:
           areaTexto.append(versoPrin1 + "fifth" + versoPrinc2 + day5 +"\n"
          +day4+"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 6:
           areaTexto.append(versoPrin1 + "sixth" + versoPrinc2 + day6 +"\n"
          +day5+"\n"
          +day4+"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 7:
           areaTexto.append(versoPrin1 + "seventh" + versoPrinc2 + day7 +"\n"
          +day6+"\n"
          +day5+"\n"
          +day4+"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 8:
           areaTexto.append(versoPrin1 + "eigth" + versoPrinc2 + day8 +"\n"
          +day7+"\n"
          +day6+"\n"
          +day5+"\n"
          +day4+"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 9:
           areaTexto.append(versoPrin1 + "nineth" + versoPrinc2 + day9 +"\n"
          +day8+"\n"
          +day7+"\n"
          +day6+"\n"
          +day5+"\n"
          +day4+"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 10:
           areaTexto.append(versoPrin1 + "tenth" + versoPrinc2 + day10 +"\n"
          +day9+"\n"
          +day8+"\n"
          +day7+"\n"
          +day6+"\n"
          +day5+"\n"
          +day4+"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 11:
           areaTexto.append(versoPrin1 + "eleventh" + versoPrinc2 + day11 +"\n"
          +day10+"\n"
          +day9+"\n"
          +day8+"\n"
          +day7+"\n"
          +day6+"\n"
          +day5+"\n"
          +day4+"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        case 12:
           areaTexto.append(versoPrin1 + "twelveth" + versoPrinc2 + day12 +"\n"
          +day11+"\n"
          +day10+"\n"
          +day9+"\n"
          +day8+"\n"
          +day7+"\n"
          +day6+"\n"
          +day5+"\n"
          +day4+"\n"
          +day3+"\n"
          +day2+"\n"
          +day1+"\n\n");
          break;
        }//Fim switch;
      }//Fim for

     // areaTexto.setText(output);
      JOptionPane.showMessageDialog(null,barraRolagem,"The Twelve Days Of Xmas",JOptionPane.PLAIN_MESSAGE);
      System.exit(0);
  }//Fim méstodo
}//Fim classe









