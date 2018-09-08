package br.ufc.lia.sd.morphufc.util;

import java.io.Serializable;
import java.text.DecimalFormat;

public class MetaFile implements Serializable{

	private String name;
	private double size;
	private DecimalFormat twoDigts = new DecimalFormat("0.00");
	
	public boolean equals(Object obj) {
		 MetaFile ref = (MetaFile)obj;
		 if(ref.name.equals(this.name))
			 return true;
		 return false;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getSize() {
		return size;
	}


	public void setSize(double size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return this.name + "   " +twoDigts.format(this.size)+ "MB";
	}
}
