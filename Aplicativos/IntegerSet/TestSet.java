import javax.swing.JOptionPane;
import br.unifor.jefferson.IntegerSet;

public class TestSet
{
  public static void main(String args[])
  {
	IntegerSet set1,set2;
        set1=new IntegerSet();
	set2=new IntegerSet();
        

	String output="";
        
        set1.insertElement(1);
	set1.insertElement(2);
	set1.insertElement(3);
	set1.insertElement(4);

	output+="\nSet1:"+set1.setPrint();

	set2.insertElement(4);
	set2.insertElement(5);
	set2.insertElement(6);
	
	output+="\nSet2:"+set2.setPrint();
	
	set1.unionOfIntegerSets(set2);
	
	output+="\n\nSet1=(Set1 U Set2): "+set1.setPrint();

	set1.deleteElement(1);
	set1.deleteElement(2);

	output+="\nSet1(after deleting 1 and 2):"+set1.setPrint();

	set1.intersectionOfIntegerSets(set2);
	
	output+="\nSet1=(Set1 I Set2):"+set1.setPrint();

	JOptionPane.showMessageDialog(null,output,"Set Results",JOptionPane.PLAIN_MESSAGE);
	System.exit(0);
  }
}