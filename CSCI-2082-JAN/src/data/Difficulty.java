package data;

public class Difficulty {
	private int level;
	private int numberOfBoxes;
	public static final int MAX_LEVEL = 5;
	public static final int INITIAL = 5;
	public Difficulty() {
		this.level = 1;
		this.numberOfBoxes = INITIAL;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		numberOfBoxes = INITIAL + level - 1;
	}
	
	public void increaseByOne() {
		this.level+=1;
		numberOfBoxes += 1; 
	}

	public int getNumberOfBoxes() {
		return numberOfBoxes;
	}	
}
