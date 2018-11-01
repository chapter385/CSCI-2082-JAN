package boxstack;

public class Box<T> {
	private Box<T> link;
	private T x;
	private T y;
	
	public Box(T x, T y, Box<T> link) {
		this.x = x;
		this.y = y;
		this.link = link;
	}

	public Box<T> getLink() {
		return link;
	}

	public void setLink(Box<T> link) {
		this.link = link;
	}

	public T getY() {
		return y;
	}

	public T getX() {
		return x;
	}
}
