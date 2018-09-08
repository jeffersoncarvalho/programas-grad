package br.ufc.lia.es.solar.model;

import arcademis.MarshalException;
import arcademis.Marshalable;
import arcademis.Stream;

public abstract class Model implements Marshalable{
	
	protected String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void marshal(Stream stream) throws MarshalException {
		try {
			stream.write(this.code);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void unmarshal(Stream stream) throws MarshalException {
		try {
			this.code = (String)stream.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
