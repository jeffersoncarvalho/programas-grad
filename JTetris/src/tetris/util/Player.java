package tetris.util;


public class Player {
	
	private String name;
	private int points;
	private int level;


	public Player(String name, int points, int level) {
		this.name = name;
		this.points = points;
		this.level = level;
	}
	
	public void setName(String name) {
		this.name = name; 
	}

	public void setPoints(int points) {
		this.points = points; 
	}

	public void setLevel(int level) {
		this.level = level; 
	}

	public String getName() {
		return (this.name); 
	}

	public int getPoints() {
		return (this.points); 
	}

	public int getLevel() {
		return (this.level); 
	}

	public String toString() {

		String sep = System.getProperty("line.separator");

		StringBuffer buffer = new StringBuffer();
		buffer.append(sep);
		buffer.append("name = ");
		buffer.append(name);
		buffer.append(sep);
		buffer.append("points = ");
		buffer.append(points);
		buffer.append(sep);
		buffer.append("level = ");
		buffer.append(level);
		buffer.append(sep);
		
		return buffer.toString();
	}
}
