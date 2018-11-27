package data;

public class Difficulty {
	private int level;
	private int numberOfBoxes;
	
	
	
	public Difficulty(int level) {
		this.level = level;
		this.numberOfBoxes = 0;
	}



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	public int getNumberOfBoxes() {
		return numberOfBoxes;
	}	
	

}
