package br.ufc.lia.jeffersoncarvalho.aplicacoes.embrulho.gui.applet;
import java.util.*;
/** This class stores the edges that still need to be processed.
  It works like a regular stack except that putting AB on the stack when
  BA is already there causes both edges to be eliminated. 
  */
public class EdgeStack {
  private Stack data; // contents of the stack
  	
  public EdgeStack() {
    data = new Stack();
  }
  	
  public boolean isEmpty() {
    return data.isEmpty();
  }
  	
  public Edge3d get() {
    return (Edge3d) data.pop();
  }
  	
  public void put(Edge3d e) {
    data.push(e);
  }

  public void put(Point3d a, Point3d b) {
    put(new Edge3d(a,b));
  }
  	
  public void putp(Edge3d e) {
    int ind = data.indexOf(e);
    if (ind == -1) {
      data.push(e);
    } else {
      data.removeElementAt(ind);
    }
  }

  public void putp(Point3d a, Point3d b) {
    putp(new Edge3d(a,b));
  }
  	
  public void dump() {
    Enumeration e = data.elements();
    System.out.println(data.size());
    while (e.hasMoreElements()) {
      System.out.println((Edge3d)e.nextElement());
    }
    System.out.println();
  }

}
  			
