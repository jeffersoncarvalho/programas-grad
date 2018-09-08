package model;

public abstract class AbstractForroPort implements ForroPort{

	/**
	 * The port's name.
	 * @uml.property  name="name"
	 */
	private String name;

	public AbstractForroPort(String name) {
		super();
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see session.Port#getName()
	 */
	/**
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
}
