package br.ufc.lia.jeffersoncarvalho.ordenacao;

public class Element {
	
	private double index;
	private Object content;
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public double getIndex() {
		return index;
	}
	public void setIndex(double index) {
		this.index = index;
	}
	
	public Element(Object content, double index) {
		super();
		// TODO Auto-generated constructor stub
		this.content = content;
		this.index = index;
	}

}
