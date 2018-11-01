package display;


import java.awt.Button;


public class BoxButton extends Button{
	private int X;
	private int Y;
	
	public BoxButton() {
		super();
	}
	
	public BoxButton(String label) {
		super(label);
	}
	
	public BoxButton(String label,int x,int y) {
		super(label);
		X=x;Y=y;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
	
	
	
}
