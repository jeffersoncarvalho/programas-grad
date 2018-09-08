package exceptions;


public class CCAException extends Exception {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Throwable detail;
  private int type;

  
  public CCAException() {
    super();
  }
  
  public CCAException(int type) {
	super();
	this.type = type;
}

public CCAException(String exceptionMessage) {
    super(exceptionMessage);
  }

  public CCAException(String exceptionMessage, Throwable ex) {
    super(exceptionMessage);
    detail = ex;
  }

  public Throwable getDetail() { return detail; }

  public String getMessage() {
    if(detail == null)
      return super.getMessage();
    else
      return super.getMessage() + "; nested exception is: \n\t" 
        + detail.toString();
      
  }

  public void printStackTrace(java.io.PrintStream ps) {
    if (detail == null) {
        super.printStackTrace(ps);
    } else {
        synchronized(ps) {
          //ps.println(this);
          ps.println(super.getMessage() + "; nested exception is:");
          detail.printStackTrace(ps);
        }
    }
  }

  public void printStackTrace() {
    printStackTrace(System.err);
  }

  public void printStackTrace(java.io.PrintWriter pw){
    if (detail == null) {
        super.printStackTrace(pw);
    } else {
      synchronized(pw) {
        //pw.println(this);
        pw.println(super.getMessage() + "; nested exception is:");
        detail.printStackTrace(pw);
      }
    }
  }

/**
 * @return the type
 */
public int getType() {
	return type;
}

/**
 * @param type the type to set
 */
public void setType(int type) {
	this.type = type;
}
  
}

