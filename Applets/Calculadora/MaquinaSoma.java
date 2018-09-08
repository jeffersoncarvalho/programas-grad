
public class MaquinaSoma
{
  protected double total;

  public MaquinaSoma()
  {
	total=0;
  }

  public MaquinaSoma(double tot)
  {
	total=tot;
  }

  public void somar(double val)
  {
	total+=val;
  }

  public void subtrair(double val)
  {
	total-=val;
  }

  public double retTotal()
  {
  	return total;
  }

}