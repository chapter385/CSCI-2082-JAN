package data;

public class Difficulty {
	private int level;
	private int numberOfBoxes;
	
	public Difficulty() {
		this.level = 1;
		this.numberOfBoxes = 5;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		numberOfBoxes = 5 + level - 1;
	}

	public int getNumberOfBoxes() {
		return numberOfBoxes;
	}	
}
