import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Sml extends JApplet implements ActionListener
{
  final int READ=10,WRITE=11,
            LOAD=20,STORE=21,
            ADD=30,SUBTRACT=31,DIVIDE=32,MULTIPLY=33,
            BRANCH=40,BRANCHNEG=41,BRANCHZERO=42,
            HALT=43;

  boolean endProgram;
  String outputDump,outputProgram;

  int instructionRegister,
      instructionCounter,
      accumulator,
      operand,
      operationCode;

  String memory[];
  String getInput;


  int x;

  JTextField dataField;
  JTextArea dumpArea,programArea;
  JScrollPane dumpScroll,programScroll;

  public void init()
  {
    Container c=getContentPane();
    c.setLayout(new FlowLayout());

    dataField=new JTextField(10);
    dataField.addActionListener(this);
    c.add(dataField);

    programArea=new JTextArea(15,15);
    programArea.setEditable(false);
    programScroll=new JScrollPane(programArea);
    c.add(programScroll);

    dumpArea=new JTextArea(30,45);
    dumpArea.setEditable(false);
    dumpScroll=new JScrollPane(dumpArea);
    c.add(dumpScroll);

    accumulator=0;
    endProgram=false;
    instructionCounter=0;

    memory=new String[100];
    outputDump="";
    outputProgram="";
    x=0;


  }//init

  public void actionPerformed(ActionEvent e)
  {

    getInput=dataField.getText();

    if(Integer.parseInt(getInput)!=-1)
    {
      outputProgram+="\n"+getInput;
      memory[x++]=getInput;
      programArea.setText(outputProgram);
    }
    else
    {

      analyzeCode();
      dump();
    }

  }//acao

	public void analyzeInstruction()
  {
    instructionRegister=Integer.parseInt(memory[instructionCounter]);
    operationCode=instructionRegister/100;
    operand=instructionRegister%100;

    switch(operationCode)
    {
      case READ:
        String tempString;
        //int tempInt;
        tempString=JOptionPane.showInputDialog("Please, enter data.");
        //tempInt=Integer.parseInt(tempString);
        memory[operand]=tempString;
        instructionCounter++;
        break;
      case WRITE:
        outputProgram+="\n"+memory[operand];
        instructionCounter++;
        break;

      case LOAD:
        accumulator=Integer.parseInt(memory[operand]);
        instructionCounter++;
        break;
      case STORE:
        memory[operand]=Integer.toString(accumulator);
        instructionCounter++;
        break;

      case ADD:
        accumulator+=Integer.parseInt(memory[operand]);
        instructionCounter++;
        break;
      case SUBTRACT:
        accumulator-=Integer.parseInt(memory[operand]);
        instructionCounter++;
        break;
      case DIVIDE:
        accumulator/=Integer.parseInt(memory[operand]);
        instructionCounter++;
        break;
      case MULTIPLY:
        accumulator*=Integer.parseInt(memory[operand]);
        instructionCounter++;
        break;

      case BRANCH:
        instructionCounter=operand;
        break;
      case BRANCHNEG:
        if(accumulator<0)
          instructionCounter=operand;
        else
          instructionCounter++;
        break;
      case BRANCHZERO:
        if(accumulator==0)
          instructionCounter=operand;
        else
          instructionCounter++;
        break;
      case HALT:
        endProgram=true;
        outputProgram+="\n***Program terminated***";
        break;

      default:
        outputProgram+="\nERROR!";
        endProgram=true;
        break;
    }//switch

    registerDump();

  }//analyzeInstruction

  public void registerDump()
  {
    outputDump+="\n\nREGISTERS:"+
                "\naccumulator\t\t"+accumulator+
                "\ninstructionCounter\t"+instructionCounter+
                "\ninstructionRegister\t"+instructionRegister+
                "\noperationCode\t\t"+operationCode+
                "\noperand\t"+operand;
  }//RegisterDump

  public void memoryDump()
  {
    outputDump+="\n\n\n***MEMORY***\n\n";
    outputDump+="\t";
    for(int i=0;i<10;i++)
     outputDump+=i+"\t";
    outputDump+="\n";

    for(int i=0;i<10;i++)
    {
      outputDump+=i*10;
      for(int j=0;j<10;j++)
       outputDump+="\t"+memory[i*10+j];
      outputDump+="\n";
    }
  }//memoryDump

  public void dump()
  {
    memoryDump();
    dumpArea.setText(outputDump);
  }//dump

  public void analyzeCode()
  {
    outputProgram+="\n***Program Started***\n";
    do
    {
      analyzeInstruction();
      programArea.setText(outputProgram);
    }while(endProgram==false);
  }//analyzeCode

  public boolean testInstruction()
  {
    if(getInput.length()!=4)
     return false;
    if(Integer.parseInt(getImput)<1000 || Integer.parseInt(getInput)>9999 )
     return false;
    return true;
  }//test instruction


}//end ADT
