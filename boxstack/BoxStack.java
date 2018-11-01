package boxstack;

import java.util.EmptyStackException;

public class BoxStack<T> {
	private Box<T> head;
	private int count;
	
	private String coords = "" + (String)head.getX() + ", " +  head.getY();	
	
	public BoxStack() {
		count = 0;
	}
	
	public void push(T x, T y) {
		head = new Box(x, y, head);
	}
	
	public T peek() {
		if(head == null)
			throw new EmptyStackException();

		return (T) coords;
	}
	
	public T pop() {
		if(head == null)
			throw new EmptyStackException();
		
		T top = (T) coords;
		
		head = head.getLink();
		
		return top;
	}
}
