package br.ufc.lia.es.solar.util;

import java.util.Enumeration;
import java.util.Vector;

import arcademis.MarshalException;
import arcademis.Marshalable;
import arcademis.Stream;

public class MarshalableVector extends Vector implements Marshalable {

	       private static final long serialVersionUID = 1L;

	       public MarshalableVector() {
	               super();
	       }

	       public MarshalableVector(Enumeration enumeration){
	               while (enumeration.hasMoreElements()) {
	                       this.addElement(enumeration.nextElement());
	               }
	       }

	       public void marshal(Stream b) throws MarshalException {
	               b.write(this.size());

	               Enumeration elements = this.elements();
	               while (elements.hasMoreElements()) {
	                       Object obj = elements.nextElement();

	                       if (obj instanceof Marshalable) {
	                               b.write((Marshalable) obj);

	                       } else if (obj instanceof String) {
	                               b.write((String) obj);
	                       }
	               }

	       }

	       public void unmarshal(Stream b) throws MarshalException {
	               int size = b.readInt();
	               for (int i = 0; i < size; i++) {
	                       this.addElement(b.readObject());
	               }
	       }
}

