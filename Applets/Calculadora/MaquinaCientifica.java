public class MaquinaCientifica extends MaquinaSoma
{
  public MaquinaCientifica()
  {
    super();
  }

  public MaquinaCientifica(double tot)
  {
    super(tot);
  }

  public void div(double val)
  {
    total/=val;
  }

  public void mult(double val)
  {
    total*=val;
  }

  public void setTotal(double val)
  {
    total=val;
  }


}
