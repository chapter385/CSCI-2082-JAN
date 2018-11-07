package data;

public class Box implements Cloneable{
	private Box link;
	private int x;
	private int y;
	
	public Box(int x, int y, Box link) {
		this.x = x;
		this.y = y;
		this.link = link;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Box) {
			Box temp = (Box)obj;
			if(x == temp.getX() && y == temp.getY())
				return true;
			else 
				return false;
		}
		else
			return false;
	}

	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Box clone = (Box) super.clone();
		clone.setX(x);
		clone.setY(y);
		return clone;
	}

	public Box getLink() {
		return link;
	}

	public void setLink(Box link) {
		this.link = link;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
}